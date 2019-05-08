package it.uniba.query;

/**
 *The QueryDate class. 
 */
class QueryDate {
	/**
	 * The day.
	 */
	private int day;

	/**
	 * The month.
	 */

	private int month;

	/**
	 * The year.
	 */
	private int year;

	/**
	 * The QueryDate constructor.
	 * @param day The day
	 * @param month The month
	 * @param year The year
	 */
	QueryDate(final int day, final  int month, final int year) {

		this.day = day;
		this.month = month;
		this.year = year;

	}

	/**
	 * converts the QueryDate to a String.
	 * @return the string you can append to the query
	 */

	@Override
	public String toString() {

		String date = "";

		if (year != 0) {
			date += " EXTRACT(year FROM creation_date)=" + year;
		}

		if (month != 0) {
			if (year != 0) {
				date += " and";
			}
			date += " EXTRACT(month FROM creation_date)=" + month;
		}

		if (day != 0) {
			if (year != 0 || month != 0) {
				date += " and";
			}
			date += " EXTRACT(day FROM creation_date)=" + day;
		}

		return date;

	}
}
