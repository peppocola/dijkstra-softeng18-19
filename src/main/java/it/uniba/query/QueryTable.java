package it.uniba.query;

class QueryTable {

	private QuerySelect select;

	private String table;

	private QueryWhere where;

	private QueryOrderBy order;

	private QueryGroupBy group;

	private QueryLimit limit;

	QueryTable(final QuerySelect s, final String t) {
		select = s;
		table = t;
	}

	QueryTable(final QuerySelect s, final String t, final QueryWhere w) {
		select = s;
		table = t;
		where = w;
	}

	QueryTable(final QuerySelect s, final String t, final QueryWhere w, final QueryOrderBy o) {
		select = s;
		table = t;
		where = w;
		order = o;
	}

	QueryTable(final QuerySelect s, final String t, final QueryWhere w, final QueryOrderBy o, final QueryLimit l) {
		select = s;
		table = t;
		where = w;
		order = o;
		limit = l;
	}

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
