package it.uniba.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import com.google.cloud.bigquery.Job;

import it.uniba.query.Query;
import it.uniba.query.QueryResults;
import it.uniba.sotorrent.GoogleDocsUtils;
import it.uniba.sotorrent.ISOQuery;
import it.uniba.sotorrent.SOQuery;

/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 *
 * <b>DO NOT RENAME</b>
 * 
 * Control
 */
public final class AppMain {

	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {

	}

	/**
	 * * This is the main entry of the application.
	 *
	 * @param args The command-line arguments.
	 * @throws FileNotFoundException    See stack trace for proper location.
	 * @throws IOException              See stack trace for proper location.
	 * @throws InterruptedException     See stack trace for proper location.
	 * @throws GeneralSecurityException See stack trace for proper location.
	 * @throws URISyntaxException       See stack trace for proper location.
	 */
	public static void main(final String[] args) throws FileNotFoundException, IOException, InterruptedException,
			GeneralSecurityException, URISyntaxException {

		if (args.length <= 0) {
			System.out.println("missing arguments");
			return;
		}

		Arguments params;
		try {
			params = new Arguments(args);
		} catch (final ParseException e) {
			System.err.println(e);
			return;
		} catch (final NumberFormatException e) {
			System.err.println(e);
			return;
		}

		Query query;
		try {
			query = new Query(params);
		} catch (final ArgumentException e) {
			System.err.println(e);
			return;
		}

		System.out.println(query);

		final ISOQuery soq = new SOQuery();
		final Job job = soq.runQuery(query);
		final QueryResults res = soq.getResults(job);

		final GoogleDocsUtils ut = new GoogleDocsUtils();
		final String spid = ut.createSheet("Result");
		ut.shareSheet(spid);
		ut.getSheetByTitle(spid);
		ut.writeSheet(spid, res);

	}

}
