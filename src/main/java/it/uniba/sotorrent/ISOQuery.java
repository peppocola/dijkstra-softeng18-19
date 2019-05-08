package it.uniba.sotorrent;

import java.util.ArrayList;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;

import it.uniba.query.Query;

/**
 * Interface for running a query on Stack Overflow via Google's BigQuery
 * service.
 */
public interface ISOQuery {

	/**
	 * Starts the query.
	 * 
	 * @param query The query string.
	 * @return The job for the query.
	 * @throws InterruptedException Raised on timeouts.
	 */

	Job runQuery(Query query) throws InterruptedException;

	/**
	 * Returns the results from the query job.
	 * 
	 * @param queryJob The job associated to the query.
	 * @return Results as a array of long, with owner_user_id.
	 * @throws JobException         Generic error occurred.
	 * @throws InterruptedException Raised on timeouts.
	 */
	ArrayList<Long> getResults(Job queryJob) throws JobException, InterruptedException;
}
