package it.uniba.sotorrent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;


/**
 * Utility class for creating, sharing, and deleting Google spreadsheets.
 * For more, refer to <a href="https://developers.google.com/sheets/api/samples/">this documentation</a>.
 */
public class GoogleDocsUtils {
	/**
	 * The app name.
	 */
	private static final String APPLICATION_NAME = "sna4so";
	/**
	 * Permissions to manage Google Drive.
	 */
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.DRIVE);
	/**
	 * The instance of the Google Spreadsheet service.
	 */
	private Sheets sheetsService;
	/**
	 * The instance of the Google Drive service.
	 */
	private Drive driveService;
	/**
	 * The object built from the JSON credential file.
	 */
	private Credential credential;
	/**
	 * The location where the SON credential file is stored on the Internet.
	 */
	private static final String url = "http://neo.di.uniba.it/credentials/project-sna4so.json";

	/**
	 * Default constructor, authenticates and instantiate services.
	 */
	public GoogleDocsUtils() {
		try {
			credential = authorize();
			sheetsService = getSheetsService();
			driveService = getDriveService();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Performs Google authentication process.
	 * @return Credential object.
	 * @throws IOException Generic I/O error
	 * @throws GeneralSecurityException Failed authentication.
	 * @throws URISyntaxException Malformed URI.
	 */
	private Credential authorize() throws IOException, GeneralSecurityException, URISyntaxException {
		GoogleCredential authCred = GoogleCredential.fromStream(new URL(url).openStream()).toBuilder()
				.setServiceAccountScopes(SCOPES).build();
		return authCred;
	}

	/**
	 * Instantiates the the Google Sheets service.
	 * @return Instance of the Google Sheets service.
	 * @throws IOException Generic I/O error.
	 * @throws GeneralSecurityException Failed authentication.
	 * @throws URISyntaxException Malformed URI.
	 */
	private Sheets getSheetsService() throws IOException, GeneralSecurityException, URISyntaxException {
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	/**
	 * Instantiates the the Google Drive service.
	 * @return Instance of the Google Drive service.
	 * @throws IOException Generic I/O error.
	 * @throws GeneralSecurityException Failed authentication.
	 * @throws URISyntaxException Malformed URI.
	 */
	private Drive getDriveService() throws IOException, GeneralSecurityException, URISyntaxException {
		return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	/**
	 * Creates a new sheet on every execution.
	 * @param title Spreadsheet title.
	 * @return The spreadsheet id.
	 * @throws IOException Generic I/O error.
	 */
	public String createSheet(final String title) throws IOException {
		Spreadsheet spreadsheet = new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle(title));
		spreadsheet = sheetsService.spreadsheets().create(spreadsheet).setFields("spreadsheetId").execute();
		String spid = spreadsheet.getSpreadsheetId();
		System.out.println("Spreadsheet ID: " + spid);
		System.out.println("Spreadhsheet URL: https://docs.google.com/spreadsheets/d/" + spid);
		return spid;

	}

	/**
	 * Returns the spreadsheet id by title.
	 * @param spid The spreadsheet id.
	 * @throws IOException Generic I/O error.
	 */
	public void getSheetByTitle(final String spid) throws IOException {
		Sheets.Spreadsheets.Get request = sheetsService.spreadsheets().get(spid);
		Spreadsheet response = request.execute();
		System.out.println(response);
	}

	/**
	 * Write results to the spreadsheet. Also, see <a href="https://developers.google.com/sheets/api/guides/values">here</a>.
	 * @param spid The spreadsheet id.
	 * @param res The hash map of the results, with URL as key and view count as value.
	 * @throws IOException Generic I/O error.
	 */
	public void writeSheet(final String spid, final Map<String, Long> res) throws IOException {
		List<Request> requests = new ArrayList<>();
		List<CellData> values = new ArrayList<>();

		values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue("URL")));
		values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue("Views")));
		requests.add(new Request().setUpdateCells(
				new UpdateCellsRequest().setStart(new GridCoordinate().setSheetId(0).setRowIndex(0)
						.setColumnIndex(0))
						.setRows(Arrays.asList(new RowData().setValues(values)))
						.setFields("userEnteredValue,userEnteredFormat.backgroundColor")));

		BatchUpdateSpreadsheetRequest batchUpdateRequest =
				new BatchUpdateSpreadsheetRequest().setRequests(requests);
		sheetsService.spreadsheets().batchUpdate(spid, batchUpdateRequest).execute();


		if (null != res) {
			int rowIndex = 1;
			for (Map.Entry<String, Long> entry : res.entrySet()) {
				requests = new ArrayList<>();
				values = new ArrayList<>();

				String keyUrl = entry.getKey();
				values.add(new CellData()
						.setUserEnteredValue(new ExtendedValue().setStringValue(keyUrl)));
				Long views = entry.getValue();
				values.add(
						new CellData().setUserEnteredValue(new ExtendedValue()
								.setStringValue(String.valueOf(views))));
				requests.add(new Request().setUpdateCells(new UpdateCellsRequest()
						.setStart(new GridCoordinate().setSheetId(0).setRowIndex(rowIndex)
								.setColumnIndex(0))
						.setRows(Arrays.asList(new RowData().setValues(values)))
						.setFields("userEnteredValue,userEnteredFormat.backgroundColor")));

				batchUpdateRequest = new BatchUpdateSpreadsheetRequest().setRequests(requests);
				sheetsService.spreadsheets().batchUpdate(spid, batchUpdateRequest).execute();

				rowIndex++;
			}
		}

	}

	/**
	 * Makes the spreadsheet readable to anyone with the link.
	 * @param spid The spreadsheet id.
	 * @throws IOException Generic I/O error.
	 * @throws GeneralSecurityException Failed authentication.
	 * @throws URISyntaxException Malformed URI.
	 */
	public void shareSheet(final String spid) throws IOException, GeneralSecurityException, URISyntaxException {
		JsonBatchCallback<Permission> callback = new JsonBatchCallback<Permission>() {
			public void onFailure(final GoogleJsonError e, final HttpHeaders responseHeaders)
										  throws IOException {
				// Handle error
				System.err.println(e.getMessage());
			}

			public void onSuccess(final Permission permission, final HttpHeaders responseHeaders)
							    		  throws IOException {
				System.out.println("Permission ID: " + permission.getId());
			}
		};
		BatchRequest batch = driveService.batch();
		Permission userPermission = new Permission().setType("anyone").setRole("reader");
		driveService.permissions().create(spid, userPermission).setFields("id").queue(batch, callback);

		batch.execute();

	}

	// Intentionally not used it. Use it to delete a sheet.
	/**
	 * Deletes a spreadsheet.
	 * @param spid The spreadsheet id.
	 * @throws IOException Generic I/O error.
	 */
	@SuppressWarnings("unused")
	private void deleteSheet(final String spid) throws IOException {
		driveService.files().delete(spid).execute();

	}

}
