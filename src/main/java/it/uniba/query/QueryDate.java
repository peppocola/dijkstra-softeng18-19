package it.uniba.query;

/**
 * The QueryDate class. This class creates the date via toString generate a
 * piece of the query.
 * 
 * <<Entity>>
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
	 * 
	 * @param dd   The day
	 * @param mm   The month
	 * @param yyyy The year
	 */
	QueryDate(final int dd, final int mm, final int yyyy) {

		this.day = dd;
		this.month = mm;
		this.year = yyyy;

	}

	/**
	 * converts the QueryDate to a String.
	 * 
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
