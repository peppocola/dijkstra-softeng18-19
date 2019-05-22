package it.uniba.query;

class QueryUnionAll {
	private QueryTable first;

	private QueryTable second;

	QueryUnionAll(QueryTable f, QueryTable s) {
		first = f;
		second = s;
	}

	@Override
	public String toString() {
		String str = "";

		str += "(" + first + ")" + " UNION ALL " + "(" + second + ")";

		return str;
	}
}
