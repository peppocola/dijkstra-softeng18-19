
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
	 * The edge. If true, enables edges in the query. Default option is false.
	 */
	private boolean edge;

	/**
	 * The weight. The option to show weights in the edges' output. Default option
	 * is false.
	 */
	private boolean weight;

	/**
	 * The user. The user id of the user whom created the post.
	 */
	private long user;

	/**
	 * The regular expression used to parse the arguments.
	 */
	private static final String REGEX = "(" + "(yyyy=(\\d){4})|" + "(mm=(\\d){1,2})|" + "(dd=(\\d){1,2})|"
			+ "(type=(question|post|answer))|" + "(taglike=(\\w)+)|" + "(limit=(\\d)+)|" + "(edge=(yes|no))|"
			+ "(weight=(yes|no))|" + "(user=(\\d)+)" + ")";

	/**
	 * The Arguments constructor.
	 * 
	 * @param args An array of argument strings
	 * @throws ParseException A parse exception
	 */
	public Arguments(final String[] args) throws ParseException {

		for (final String str : args) {
			if (!str.matches(REGEX)) {
				throw new ParseException("unknown pattern: " + str);
			}

			final String[] values = str.split("=");

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
			case "edge":
				edge = values[1].equals("yes");
				break;
			case "weight":
				weight = values[1].equals("yes");
				break;
			case "user":
				user = Integer.parseInt(values[1]);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Get the year.
	 * 
	 * @return The year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Get the month.
	 * 
	 * @return The month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Get the day.
	 * 
	 * @return The day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Get the type.
	 * 
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Get the tag.
	 * 
	 * @return The tag
	 */
	public String getTaglike() {
		return taglike;
	}

	/**
	 * Get the limit.
	 * 
	 * @return The limit
	 */
	public long getLimit() {
		return limit;
	}

	/**
	 * Get the edge.
	 * 
	 * @return The edge
	 */
	public boolean getEdge() {
		return edge;
	}

	/**
	 * Get the weight.
	 * 
	 * @return The weight
	 */
	public boolean getWeight() {
		return weight;
	}

	/**
	 * Get the user.
	 * 
	 * @return The user
	 */
	public long getUser() {
		return user;
	}

	/**
	 * Converts the arguments to a String.
	 * 
	 * @return The string that rappresents the arguments
	 */
	@Override
	public String toString() {
		String str = "";

		str = "yyyy=" + year + " mm=" + month + " dd=" + day + " type=" + type + " taglike=" + taglike + " limit="
				+ limit + " edge=" + edge + " weight=" + weight + " user=" + user;

		return str;
	}
}
