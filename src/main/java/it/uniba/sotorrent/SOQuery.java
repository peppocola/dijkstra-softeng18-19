/**
 *
 */
package it.uniba.sotorrent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

/**
 * Class which executes queries.
 */
public final class SOQuery implements ISOQuery {
	/**
	 * Instance of BigQuery service.
	 */
	private BigQuery bigquery;
	/**
	 * URL of credentials JSON file.
	 */
	private static final String URL = "http://neo.di.uniba.it/credentials/project-dijkstra-sxc5g6.json";

	/**
	 * Default constructor, instantiates BigQuery API service.
	 * @throws FileNotFoundException The remote JSON file with credential is 404.
	 * @throws IOException Malformed JSON file.
	 */
	public SOQuery() throws FileNotFoundException, IOException {
		bigquery = BigQueryOptions.newBuilder().setProjectId("enduring-button-237211")
				.setCredentials(ServiceAccountCredentials.fromStream(new URL(URL).openStream())).build()
				.getService();
	}


	/**
	 * Starts the query.
	 * @param query The query string.
	 * @return The job for the query.
	 * @throws InterruptedException Raised on timeouts.
	 */
	public Job runQuery(final String query) throws InterruptedException {
		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		QueryJobConfiguration queryConfig = QueryJobConfiguration
				.newBuilder(query)
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

	/**
	 * Returns the results from the query job.
	 * @param queryJob The job associated to the query.
	 * @return Results as a array of long, with owner_user_id.
	 * @throws JobException Generic error occurred.
	 * @throws InterruptedException Raised on timeouts.
	 */
	public ArrayList<Long> getResults(final Job queryJob) throws JobException, InterruptedException {

		ArrayList<Long> results = new ArrayList<Long>();

		if (queryJob != null) {
			TableResult result = queryJob.getQueryResults();

			for (FieldValueList row : result.iterateAll()) {
				Long userid = row.get("owner_user_id").getLongValue();
				results.add(userid);
			}

		}
		return results;
	}

}
