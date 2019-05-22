package it.uniba.query;

class QueryWhere {

	private String check;

	private QueryDate date;

	private QueryTaglike taglike;

	QueryWhere(String ch) {
		check = ch;
	}

	QueryWhere(String ch, QueryDate dt) {
		check = ch;
		date = dt;
	}

	QueryWhere(String ch, QueryTaglike tag) {
		check = ch;
		taglike = tag;
	}

	QueryWhere(String ch, QueryDate dt, QueryTaglike tag) {
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
