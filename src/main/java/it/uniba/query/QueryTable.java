package it.uniba.query;

/**
 * The SQL syntax of a table class. This class generates the table segment of
 * the query.
 * 
 * <<Entity>>
 */
class QueryTable {

	/**
	 * The 'select' component.
	 */
	private QuerySelect select;

	/**
	 * The 'table' string.
	 */
	private String table;

	/**
	 * The 'where' component.
	 */
	private QueryWhere where;

	/**
	 * The 'order by' component.
	 */
	private QueryOrderBy order;

	/**
	 * The 'group by' component.
	 */
	private QueryGroupBy group;

	/**
	 * The 'limit' component.
	 */
	private QueryLimit limit;

	/**
	 * Construct a QueryTable.
	 * 
	 * @param s The 'select' component
	 * @param t The 'table' string
	 */
	QueryTable(final QuerySelect s, final String t) {
		select = s;
		table = t;
	}

	/**
	 * Construct a QueryTable.
	 * 
	 * @param s The 'select' component
	 * @param t The 'table' string
	 * @param w The 'where' component
	 */
	QueryTable(final QuerySelect s, final String t, final QueryWhere w) {
		select = s;
		table = t;
		where = w;
	}

	/**
	 * Construct a QueryTable.
	 * 
	 * @param s The 'select' component
	 * @param t The 'table' string
	 * @param w The 'where' component
	 * @param o The 'order by' component
	 */
	QueryTable(final QuerySelect s, final String t, final QueryWhere w, final QueryOrderBy o) {
		select = s;
		table = t;
		where = w;
		order = o;
	}

	/**
	 * Construct a QueryTable.
	 * 
	 * @param s The 'select' component
	 * @param t The 'table' string
	 * @param w The 'where' component
	 * @param o The 'order by' component
	 * @param l The 'limit' component
	 */
	QueryTable(final QuerySelect s, final String t, final QueryWhere w, final QueryOrderBy o, final QueryLimit l) {
		select = s;
		table = t;
		where = w;
		order = o;
		limit = l;
	}

	/**
	 * Construct a QueryTable.
	 * 
	 * @param s The 'select' component
	 * @param t The 'table' string
	 * @param w The 'where' component
	 * @param o The 'order by' component
	 * @param g The 'group by' component
	 * @param l The 'limit' component
	 */
	QueryTable(final QuerySelect s, final String t, final QueryWhere w, final QueryOrderBy o, final QueryGroupBy g,
			final QueryLimit l) {
		select = s;
		table = t;
		where = w;
		order = o;
		group = g;
		limit = l;
	}

	@Override
	public String toString() {
		String str = select + " FROM " + table;

		if (where != null) {
			str += " " + where;
		}

		if (group != null) {
			str += " " + group;
		}

		if (order != null) {
			str += " " + order;
		}

		if (limit != null) {
			str += " " + limit;
		}

		return str;
	}
}
