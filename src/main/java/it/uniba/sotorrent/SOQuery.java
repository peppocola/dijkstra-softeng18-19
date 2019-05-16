/**
 *
 */
package it.uniba.sotorrent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

import it.uniba.query.Query;
import it.uniba.query.QueryResults;

/**
 * Class which executes queries.
 */
public final class SOQuery implements ISOQuery {
	/**
	 * Instance of BigQuery service.
	 */
	private final BigQuery bigquery;
	/**
	 * URL of credentials JSON file.
	 */
	private static final String URL = "http://neo.di.uniba.it/credentials/project-dijkstra-sxc5g6.json";

	/**
	 * Default constructor, instantiates BigQuery API service.
	 *
	 * @throws FileNotFoundException The remote JSON file with credential is 404.
	 * @throws IOException           Malformed JSON file.
	 */
	public SOQuery() throws FileNotFoundException, IOException {
		bigquery = BigQueryOptions.newBuilder().setProjectId("enduring-button-237211")
				.setCredentials(ServiceAccountCredentials.fromStream(new URL(URL).openStream())).build()
				.getService();
	}

	/**
	 * Starts the query.
	 *
	 * @param query The query string.
	 * @return The job for the query.
	 * @throws InterruptedException Raised on timeouts.
	 */
	@Override
	public Job runQuery(final Query query) throws InterruptedException {
		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		final QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query.toString())
				.setUseLegacySql(false).build();

		// Create a job ID so that we can safely retry.
		final JobId jobId = JobId.of(UUID.randomUUID().toString());
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
	 *
	 * @param queryJob The job associated to the query.
	 * @return Results as QueryResults.
	 * @throws JobException         Generic error occurred.
	 * @throws InterruptedException Raised on timeouts.
	 */
	@Override
	public QueryResults getResults(final Job queryJob) throws JobException, InterruptedException {

		QueryResults results = new QueryResults();

		if (queryJob != null) {
			final TableResult result = queryJob.getQueryResults();
			int size = result.getSchema().getFields().size();
			List<String> columns = new ArrayList<String>();

			for (final Field row : result.getSchema().getFields()) {
				columns.add(row.getName());
			}

			results.setColumns(columns);

			for (final FieldValueList row : result.iterateAll()) {
				String[] tuple = new String[size];
				for (int i = 0; i < size; i++) {
					tuple[i] = row.get(i).getStringValue();
				}
				results.addTuple(tuple);
			}
		}
		return results;
	}

}
