package it.uniba.query;

/**
 * The SQL syntax of a table class. This class generates the table segment of
 * the query.
 * 
 * Entity
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

	public QuerySelect getSelect() {
		return select;
	}

	public void setSelect(final QuerySelect select) {
		this.select = select;
	}

	public String getTable() {
		return table;
	}

	public void setTable(final String table) {
		this.table = table;
	}

	public QueryWhere getWhere() {
		return where;
	}

	public void setWhere(final QueryWhere where) {
		this.where = where;
	}

	public QueryOrderBy getOrder() {
		return order;
	}

	public void setOrder(final QueryOrderBy order) {
		this.order = order;
	}

	public QueryGroupBy getGroup() {
		return group;
	}

	public void setGroup(final QueryGroupBy group) {
		this.group = group;
	}

	public QueryLimit getLimit() {
		return limit;
	}

	public void setLimit(final QueryLimit limit) {
		this.limit = limit;
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
