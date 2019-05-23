package it.uniba.query;

class QueryLimit {

	private long limit;

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
