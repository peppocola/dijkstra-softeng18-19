
package it.uniba.main;

/**
 * The input parameters class.
 */
public class Arguments {

	/**
	 * The year.
	 */
	private int year;

	/**
	 * The month.
	 */
	private int month;

	/**
	 * The day.
	 */
	private int day;

	/**
	 * The type (can be 'question', 'post' and 'answer').
	 */
	private String type;

	/**
	 * The tag string.
	 */
	private String taglike;

	/**
	 * The limit number to the query output.
	 */
	private long limit;

	/**
	 * The edge.
	 * If true, enables edges in the query.
	 * Default option is false.
	 */
	private boolean edge;

	/**
	 * The weight.
	 * The option to show weights in the edges' output.
	 * Default option is false.
	 */
	private boolean weight;

	/**
	 * The user.
	 * The user id of the user whom created the post.
	 */
	private long user;

	/**
	 * The regular expression used to parse the arguments.
	 */
	private static final String REGEX =
			  "("
			+ "(yyyy=(\\d){4})|"
			+ "(mm=(\\d){1,2})|"
			+ "(dd=(\\d){1,2})|"
			+ "(type=(question|post|answer))|"
			+ "(taglike=(\\w)+)|"
			+ "(limit=(\\d)+)"
			+ ")";

	/**
	 * The Arguments constructor.
	 * @param args An array of argument strings
	 * @throws ParseException A parse exception
	 */
	public Arguments(final String[] args)
		throws ParseException {

		for (String str : args) {
			if (!str.matches(REGEX)) {
				throw new ParseException("unknown pattern: " + str);
			}

			String[] values = str.split("=");

			switch (values[0]) {
			case "yyyy":
				year = Integer.parseInt(values[1]);
				break;
			case "mm":
				month = Integer.parseInt(values[1]);
				break;
			case "dd":
				day = Integer.parseInt(values[1]);
				break;
			case "type":
				type = values[1];
				break;
			case "taglike":
				taglike = values[1];
				break;
			case "limit":
				limit = Integer.parseInt(values[1]);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Generates the query string.
	 * @return The query string
	 * @throws ArgumentException An invalid argument exception
	 */
	public String getQuery()
			throws ArgumentException {

		String query = "SELECT distinct owner_user_id\r\n";
		String date = "";

		if (year != 0) {
			date += " EXTRACT(YEAR FROM creation_date)="
					+ year;
		}

		if (month != 0) {
		    if (year != 0) {
		        date += " and";
		    }
		    date += " EXTRACT(MONTH FROM creation_date)="
					+ month;
		}

		if (day != 0) {
		    if (year != 0 || month != 0) {
		    	date += " and";
		    }
		    date += " EXTRACT(DAY FROM creation_date)="
					+ day;
		}

		if (type == null) {

			throw new ArgumentException("invalid argument " + type);

		} else if (type.equals("question") && taglike == null) {
			query += 	" FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
						+ "	WHERE "
						+ date
						+ " and owner_user_id is not null\r\n";
		} else if (type.equals("answer") && taglike == null) {
			query += 	" FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n"
						+ " WHERE "
						+ date
						+ " and owner_user_id is not null\r\n";
		} else if (type.equals("post") && taglike == null) {
			query += 	"FROM\r\n"
						+ "((SELECT distinct owner_user_id\r\n"
						+ " FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n"
						+ " WHERE "
						+ date
						+ " and owner_user_id is not null)\r\n"
						+ "UNION ALL\r\n"
						+ "(SELECT distinct owner_user_id\r\n"
						+ " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
						+ " WHERE "
						+ date
						+ " and owner_user_id is not null))\r\n";
		} else if (type.equals("answer") && taglike != null) {
			query +=	"FROM\r\n"
						+ "(SELECT distinct parent_id, owner_user_id\r\n"
						+ "FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n"
						+ " WHERE "
						+ date
						+ " and owner_user_id is not null)\r\n"
						+ "JOIN\r\n"
						+ "(SELECT distinct id\r\n"
						+ "FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
						+ " WHERE REGEXP_CONTAINS (tags, r\"" + taglike + "\"))\r\n"
						+ "ON parent_id = id\r\n";
		} else if (type.equals("post") && taglike != null) {
			query +=	" FROM (SELECT distinct owner_user_id"
						+ " FROM\r\n"
						+ "(SELECT distinct parent_id, owner_user_id\r\n"
						+ " FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n"
						+ " WHERE "
						+ date
						+ " and owner_user_id is not null)\r\n"
						+ " JOIN\r\n"
						+ " (SELECT distinct id\r\n"
						+ " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
						+ " WHERE REGEXP_CONTAINS (tags, r\"" + taglike + "\"))\r\n"
						+ " ON parent_id = id\r\n"
						+ " UNION ALL "
						+ "(SELECT owner_user_id\r\n"
						+ " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
						+ " WHERE "
						+ date
						+ " and REGEXP_CONTAINS(tags, r\"" + taglike + "\") \r\n"
						+ " and owner_user_id is not null))\r\n";
		} else if (type.equals("question") && taglike != null) {
			query += 	" FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
						+ " WHERE "
						+ date
						+ " and REGEXP_CONTAINS(tags, r\"" + taglike + "\") \r\n"
						+ " and owner_user_id is not null\r\n";
		}

		query += 		" order by owner_user_id\r\n"
				+ 		" LIMIT "
				+ limit;

		return query;
	}

	/**
	 * Get the year.
	 * @return The year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Get the month.
	 * @return The month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Get the day.
	 * @return The day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Get the type.
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Get the tag.
	 * @return The tag
	 */
	public String getTaglike() {
		return taglike;
	}

	/**
	 * Get the limit.
	 * @return The limit
	 */
	public long getLimit() {
		return limit;
	}

	/**
	 * Converts the arguments to a String.
	 * @return The string that rappresents the arguments
	 */
	public String toString() {
		String str = "";

		str = 	"yyyy=" + year + " mm=" + month + " dd=" + day
				+ " type=" + type + " taglike=" + taglike + " limit=" + limit;

		return str;
	}
}

