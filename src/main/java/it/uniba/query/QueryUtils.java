package it.uniba.query;

/**
 * This class contains some utilities for creating query.
 */
final class QueryUtils {
	private QueryUtils() {

	}

	public static String queryJoin(final String first, final String second, final String condition) {
		return "(" + first + ")" + " JOIN " + "(" + second + ")" + " ON " + condition;
	}

	public static String queryUnionAll(final String first, final String second) {
		return "(" + first + ")" + " UNION ALL " + "(" + second + ")";
	}
}
