package it.uniba.query;

/**
 * The SQL syntax 'order by' class. This class generates the 'order by' segment
 * of the query.
 * 
 * Entity
 */
class QueryOrderBy {

	/**
	 * The attributes strings to order by.
	 */
	private String[] attributes;

	/**
	 * Construct a QueryOrderBy
	 * 
	 * @param attr The attributes strings
	 */
	QueryOrderBy(final String[] attr) {
		attributes = attr;
	}

	@Override
	public String toString() {
		String str = "order by ";

		int i = 0;

		for (; i < attributes.length - 1; i++) {
			str += attributes[i] + ", ";
		}

		str += attributes[i];

		return str;
	}
}
