package it.uniba.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Map;

import com.google.cloud.bigquery.Job;

import it.uniba.sotorrent.GoogleDocsUtils;
import it.uniba.sotorrent.ISOQuery;
import it.uniba.sotorrent.SOQuery;

/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 * 
 * <b>DO NOT RENAME</b>
 */
public final class AppMain {

	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {

	}

	/**
	 * 	 * This is the main entry of the application.
	 *
	 * @param args
	 *                 The command-line arguments.
	 * @throws FileNotFoundException See stack trace for proper location.
	 * @throws IOException  See stack trace for proper location.
	 * @throws InterruptedException  See stack trace for proper location.
	 * @throws GeneralSecurityException  See stack trace for proper location.
	 * @throws URISyntaxException  See stack trace for proper location.
	 */
	public static void main(final String[] args) throws FileNotFoundException,
												 IOException,
												 InterruptedException,
												 GeneralSecurityException,
												 URISyntaxException {
		System.out.println("Current working dir: " + System.getProperty("user.dir"));

		if (args.length > 0) {
			switch (args[0]) {
			case "it":
				System.out.println(
						"Seleziona i 10 post piu' 'favoriti' su Stack Overflow "
						+ "sull'argomento 'google-bigquery'");
				break;

			case "en":
				System.out.println("Select the 10 most favorited Stack Overflow posts "
								   + "on 'google-bigquery'");
				break;

			default:
				System.out.println("Specify the language. "
								   + "Languages supported: 'it' or 'en'");
				break;

			}
		} else {
			System.out.println("Using default language 'en'");
			System.out.println("Select the 10 most favorited Stack Overflow posts "
							   + "on 'google-bigquery'");
		}

		ISOQuery soq = new SOQuery();
		Job job = soq.runQuery();
		Map<String, Long> res = soq.getResults(job);

		GoogleDocsUtils ut = new GoogleDocsUtils();
		String spid = ut.createSheet("Prova sna4so");
		ut.shareSheet(spid);
		ut.getSheetByTitle(spid);
		ut.writeSheet(spid, res);

	}

}
