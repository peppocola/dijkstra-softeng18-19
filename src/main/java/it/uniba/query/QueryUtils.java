package it.uniba.query;

/**
 * This class contains some utilities for creating query.
 */
final class QueryUtils {

	/**
	 * INSTANCE constant that holds the sole instance.
	 */
	private static final QueryUtils INSTANCE = new QueryUtils();

	/**
	 * Private (hidden) constructor.
	 */
	private QueryUtils() {

	}

	/**
	 * Static operation that returns the sole instance.
	 * 
	 * @return the instance
	 */
	public static QueryUtils getInstance() {
		return INSTANCE;
	}

	public String queryJoin(final String first, final String second, final String condition) {
		return "(" + first + ")" + " JOIN " + "(" + second + ")" + " ON " + condition;
	}

	public String queryUnionAll(final String first, final String second) {
		return "(" + first + ")" + " UNION ALL " + "(" + second + ")";
	}
}
