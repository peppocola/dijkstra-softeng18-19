/**
 * 
 */
package it.uniba.sotorrent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;


public final class SOQuery implements ISOQuery {
	/**
	 * Instance of BigQuery service.
	 */
	private BigQuery bigquery;
	/**
	 * URL of credentials JSON file.
	 */
	private static final String url = "http://neo.di.uniba.it/credentials/project-sna4so.json";

	/**
	 * Default constructor, instantiates BigQuery API service.
	 * @throws FileNotFoundException The remote JSON file with credential is 404.
	 * @throws IOException Malformed JSON file.
	 */
	public SOQuery() throws FileNotFoundException, IOException {
		bigquery = BigQueryOptions.newBuilder().setProjectId("sna4so")
				.setCredentials(ServiceAccountCredentials.fromStream(new URL(url).openStream())).build()
				.getService();
	}

	@Override
	public Job runQuery() throws InterruptedException {
		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT "
						+ "CONCAT('https://stackoverflow.com/questions/', "
						+ "CAST(id as STRING)) as url, "
						+ "view_count "
						+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
						+ "WHERE tags like '%google-bigquery%' "
						+ "ORDER BY favorite_count DESC LIMIT 10")
				.setUseLegacySql(false).build();

		// Create a job ID so that we can safely retry.
		JobId jobId = JobId.of(UUID.randomUUID().toString());
		Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

		// Wait for the query to complete.
		queryJob = queryJob.waitFor();

		// Check for errors
		if (queryJob == null) {
			throw new RuntimeException("Job no longer exists");
		} else if (queryJob.getStatus().getError() != null) {
			// You can also look at queryJob.getStatus().getExecutionErrors() for all
			// errors, not just the latest one.
			throw new RuntimeException(queryJob.getStatus().getError().toString());
		}
		return queryJob;
	}


	@Override
	public Map<String, Long> getResults(final Job queryJob) throws JobException, InterruptedException {
		Map<String, Long> results = new HashMap<String, Long>();

		if (queryJob != null) {
			TableResult result = queryJob.getQueryResults();
			// Print all pages of the results.
			for (FieldValueList row : result.iterateAll()) {
				String keyUrl = row.get("url").getStringValue();
				long viewCount = row.get("view_count").getLongValue();
				System.out.printf("url: %s views: %d%n", keyUrl, viewCount);
				results.put(keyUrl, viewCount);
			}
		}
		return results;
	}

}
