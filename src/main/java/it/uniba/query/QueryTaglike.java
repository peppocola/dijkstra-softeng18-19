package it.uniba.query;

/**
 * The QueryTaglike class. This class creates the date via toString generate a
 * piece of the query.
 * 
 * <i>&#60;Entity&#62;</i>
 */
class QueryTaglike {

	/**
	 * The taglike string.
	 */
	private String taglike;

	/**
	 * Construct a QueryTaglike.
	 * 
	 * @param tag The taglike string
	 */
	QueryTaglike(final String tag) {
		taglike = tag;
	}

	String getTaglike() {
		return taglike;
	}

	@Override
	public String toString() {
		return "REGEXP_CONTAINS (tags, r\"" + taglike + "\")";
	}
}
