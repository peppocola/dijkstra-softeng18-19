package it.uniba.sotorrent;

import com.google.cloud.bigquery.JobException;

import java.util.ArrayList;

import com.google.cloud.bigquery.Job;

/**
 * Interface for running a query on Stack Overflow via Google's BigQuery service.
 */
public interface ISOQuery {

	/**
	 * Starts the query.
	 * @param query The query string.
	 * @return The job for the query.
	 * @throws InterruptedException Raised on timeouts.
	 */
	
	Job runQuery(String query) throws InterruptedException;
	 
	/**
	 * Returns the results from the query job.
	 * @param job The job associated to the query.
	 * @return Results as a array of long, with owner_user_id.
	 * @throws JobException Generic error occurred.
	 * @throws InterruptedException Raised on timeouts.
	 */
	ArrayList<Long> getResults(final Job queryJob) throws JobException, InterruptedException;
}
