package it.uniba.sotorrent;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;

import it.uniba.query.QueryResults;

/**
 * Interface for running a query on Stack Overflow via Google's BigQuery
 * service.
 * 
 * <i>&#60;Bound&#62;</i>
 */
public interface ISOQuery {

	/**
	 * Starts the query.
	 * 
	 * @param query The query string.
	 * @return The job for the query.
	 * @throws InterruptedException Raised on timeouts.
	 */

	Job runQuery(String query) throws InterruptedException;

	/**
	 * Returns the results from the query job.
	 * 
	 * @param queryJob The job associated to the query.
	 * @return Results as QueryResults.
	 * @throws JobException         Generic error occurred.
	 * @throws InterruptedException Raised on timeouts.
	 */
	QueryResults getResults(Job queryJob) throws JobException, InterruptedException;
}
