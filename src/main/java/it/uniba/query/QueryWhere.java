package it.uniba.query;

/**
 * The SQL syntax 'where' class. This class generates the 'where' segment of the
 * query.
 * 
 * <<Entity>>
 */
class QueryWhere {

	/**
	 * The basic check string.
	 */
	private String check;

	/**
	 * The eventual date check.
	 */
	private QueryDate date;

	/**
	 * The eventual taglike check.
	 */
	private QueryTaglike taglike;

	/**
	 * Construct a QueryWhere.
	 * 
	 * @param ch The basic check string
	 */
	QueryWhere(final String ch) {
		check = ch;
	}

	/**
	 * Construct a QueryWhere
	 * 
	 * @param ch The basic check string
	 * @param dt The query date
	 */
	QueryWhere(final String ch, final QueryDate dt) {
		check = ch;
		date = dt;
	}

	/**
	 * Construct a QueryWhere
	 * 
	 * @param ch  The basic check string
	 * @param tag The query taglike
	 */
	QueryWhere(final String ch, final QueryTaglike tag) {
		check = ch;
		taglike = tag;
	}

	/**
	 * Construct a QueryWhere
	 * 
	 * @param ch  The basic check string
	 * @param dt  The query date
	 * @param tag The query taglike
	 */
	QueryWhere(final String ch, final QueryDate dt, final QueryTaglike tag) {
		check = ch;
		date = dt;
		taglike = tag;
	}

	@Override
	public String toString() {
		String str = "WHERE " + "(" + check + ")";

		if (date != null) {
			str += " AND " + date;
		}

		if (taglike != null) {
			str += " AND " + taglike;
		}

		return str;
	}
}
