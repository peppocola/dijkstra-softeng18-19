package it.uniba.query;

/**
 * The SQL syntax 'group by' class. This class generates the 'group by' segment
 * of the query.
 * 
 * <<Entity>>
 */
class QueryGroupBy {

	/**
	 * The attributes strings.
	 */
	private String[] attributes;

	/**
	 * Construct a new QueryGroupBy.
	 * 
	 * @param attr The attributes strings.
	 */
	QueryGroupBy(final String[] attr) {
		attributes = attr;
	}

	@Override
	public String toString() {
		String str = "group by ";

		int i = 0;

		for (; i < attributes.length - 1; i++) {
			str += attributes[i] + ", ";
		}

		str += attributes[i];

		return str;
	}
}
