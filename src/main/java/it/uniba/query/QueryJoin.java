package it.uniba.query;

class QueryJoin {

	private QueryTable first;

	private QueryTable second;

	private String condition;

	QueryJoin(QueryTable f, QueryTable s, String cond) {
		first = f;
		second = s;
		condition = cond;
	}

	@Override
	public String toString() {
		String str = "";

		str += "(" + first + ")" + " JOIN " + "(" + second + ")" + " ON " + condition;

		return str;
	}
}
