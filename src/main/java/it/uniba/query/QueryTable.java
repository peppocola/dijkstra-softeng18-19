package it.uniba.query;

class QueryTable {

	private QuerySelect select;

	private String table;

	private QueryWhere where;

	private QueryOrderBy order;

	private QueryGroupBy group;

	private QueryLimit limit;

	QueryTable(QuerySelect s, String t) {
		select = s;
		table = t;
	}

	QueryTable(QuerySelect s, String t, QueryWhere w) {
		select = s;
		table = t;
		where = w;
	}

	QueryTable(QuerySelect s, String t, QueryWhere w, QueryOrderBy o) {
		select = s;
		table = t;
		where = w;
		order = o;
	}

	QueryTable(QuerySelect s, String t, QueryWhere w, QueryOrderBy o, QueryLimit l) {
		select = s;
		table = t;
		where = w;
		order = o;
		limit = l;
	}

	QueryTable(QuerySelect s, String t, QueryWhere w, QueryOrderBy o, QueryGroupBy g, QueryLimit l) {
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
