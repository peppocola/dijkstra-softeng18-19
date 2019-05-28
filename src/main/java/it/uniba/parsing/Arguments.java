
package it.uniba.parsing;

/**
 * The input parameters class. This class contains the input parameters. Entity
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
	 * set the year.
	 *
	 * @return The yearr
	 */

	public void setYear(final int yyyy) {
		year = yyyy;
	}

	/**
	 * set the month.
	 *
	 * @return The month;
	 */
	public void setMonth(final int mm) {
		month = mm;
	}

	/**
	 * set the day.
	 *
	 * @return The day
	 */
	public void setDay(final int dd) {
		day = dd;
	}

	/**
	 * set the type.
	 *
	 * @return The type
	 */
	public void setType(final String ty) {
		type = ty;
	}

	/**
	 * set the taglike.
	 *
	 * @return The taglike
	 */
	public void setTaglike(final String tgl) {
		taglike = tgl;
	}

	/**
	 * set the limit.
	 *
	 * @return The limit
	 */
	public void setLimit(final long lim) {
		limit = lim;
	}

	/**
	 * Get the edge.
	 *
	 * @return The edge
	 */
	public void setEdge(final boolean ed) {
		edge = ed;
	}

	/**
	 * Get the weight.
	 *
	 * @return The weight
	 */
	public void setWeight(final boolean wgt) {
		weight = wgt;
	}

	/**
	 * set the user.
	 *
	 * @return The user
	 */
	public void setUser(final long usr) {
		user = usr;
	}

	/**
	 * Converts the arguments to a String.
	 *
	 * @return The string that rappresents the arguments
	 */
	@Override
	public String toString() {
		String str = "";

		str = "yyyy=" + year + " mm=" + month + " dd=" + day + " type=" + type + " taglike=" + taglike
				+ " limit=" + limit + " edge=" + edge + " weight=" + weight + " user=" + user;

		return str;
	}
}

