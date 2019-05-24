package it.uniba.query;

/**
 * The SQL syntax 'select' class. This class generates the 'select' segment of
 * the query.
 * 
 * <<Entity>>
 */
class QuerySelect {

	/**
	 * The name of the columns of the table to select.
	 */
	private String[] attributes;

	/**
	 * The aliases of the name of the columns to select.
	 */
	private String[] aliases;

	/**
	 * Specify an alias for count(*). Can be null if count(*) is not specified.
	 */
	private String countAlias;

	/**
	 * Specify if a 'distinct' must be added to the query.
	 */
	private boolean distinct;

	/**
	 * Construct a QuerySelect.
	 * 
	 * @param attr The name of the columns to select
	 * @param als  The aliases of the name of the columns to select
	 */
	QuerySelect(final String[] attr, final String[] als) {
		attributes = attr;
		aliases = als;
	}

	/**
	 * Construct a QuerySelect.
	 * 
	 * @param attr  The name of the columns to select
	 * @param als   The aliases of the name of the columns to select
	 * @param count The count(*) alias
	 */
	QuerySelect(final String[] attr, final String[] als, final String count) {
		attributes = attr;
		aliases = als;
		countAlias = count;
	}

	/**
	 * Construct a QuerySelect.
	 * 
	 * @param attr The name of the columns to select.
	 * @param als  The aliases of the name of the columns to select.
	 * @param dist The distinct flag.
	 */
	QuerySelect(final String[] attr, final String[] als, final boolean dist) {
		attributes = attr;
		aliases = als;
		distinct = dist;
	}

	/**
	 * Construct a QuerySelect.
	 * 
	 * @param attr  The name of the columns to select.
	 * @param als   The aliases of the name of the columns to select.
	 * @param count The count(*) alias
	 * @param dist  The distinct flag.
	 */
	QuerySelect(final String[] attr, final String[] als, final String count, final boolean dist) {
		attributes = attr;
		aliases = als;
		countAlias = count;
		distinct = dist;
	}

	@Override
	public String toString() {
		String str = "SELECT ";

		if (distinct) {
			str += "distinct ";
		}

		int i = 0;

		for (; i < attributes.length - 1; i++) {
			if (i >= aliases.length) {
				str += attributes[i] + ", ";
			} else {
				str += attributes[i] + " as " + aliases[i] + ", ";
			}
		}

		if (i >= aliases.length) {
			str += attributes[i];
		} else {
			str += attributes[i] + " as " + aliases[i];
		}

		if (countAlias != null) {
			str += ", count(*) as " + countAlias;
		}

		return str;
	}
}
