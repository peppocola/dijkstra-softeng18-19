package it.uniba.query;

/**
 * The SQL syntax of a table class. This class generates the table segment of
 * the query.
 * 
 * <i>&#60;Entity&#62;</i>
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
	 */
	QueryTable() {

	}

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

	public QuerySelect getSelect() {
		return select;
	}

	public void setSelect(final QuerySelect s) {
		this.select = s;
	}

	public String getTable() {
		return table;
	}

	public void setTable(final String t) {
		this.table = t;
	}

	public QueryWhere getWhere() {
		return where;
	}

	public void setWhere(final QueryWhere w) {
		this.where = w;
	}

	public QueryOrderBy getOrder() {
		return order;
	}

	public void setOrder(final QueryOrderBy o) {
		this.order = o;
	}

	public QueryGroupBy getGroup() {
		return group;
	}

	public void setGroup(final QueryGroupBy g) {
		this.group = g;
	}

	public QueryLimit getLimit() {
		return limit;
	}

	public void setLimit(final QueryLimit l) {
		this.limit = l;
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
