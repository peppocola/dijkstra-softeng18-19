package it.uniba.sotorrent;

import com.google.cloud.bigquery.JobException;

import java.util.Map;

import com.google.cloud.bigquery.Job;

/**
 * Interface for running a query on Stack Overflow via Google's BigQuery service.
 */
public interface ISOQuery {

	/**
	 * Starts the query.
	 * @return The job for the query.
	 * @throws InterruptedException Raised on timeouts.
	 */
	Job runQuery() throws InterruptedException;

	/**
	 * Returns the results from the query job.
	 * @param job The job associated to the query.
	 * @return Results as a hash map, with URL as key and view count as value.
	 * @throws JobException Generic error occurred.
	 * @throws InterruptedException Raised on timeouts.
	 */
	Map<String, Long> getResults(Job job) throws JobException, InterruptedException;

}
