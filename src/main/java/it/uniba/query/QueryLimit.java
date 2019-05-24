package it.uniba.query;

/**
 * The SQL syntax 'limit' class. This class generates the 'limit' segment of the
 * query.
 * 
 * <<Entity>>
 */
class QueryLimit {

	/**
	 * The maximum number of tuples of the query result.
	 */
	private long limit;

	/**
	 * Construct a new QueryLimit.
	 * 
	 * @param lim The maximum number of tuples
	 */
	QueryLimit(final long lim) {
		limit = lim;
	}

	long getLimit() {
		return limit;
	}

	@Override
	public String toString() {
		return "LIMIT " + limit;
	}
}
