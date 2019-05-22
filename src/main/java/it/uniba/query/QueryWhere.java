package it.uniba.query;

class QueryWhere {

	private String check;

	private QueryDate date;

	private QueryTaglike taglike;

	QueryWhere(final String ch) {
		check = ch;
	}

	QueryWhere(final String ch, final QueryDate dt) {
		check = ch;
		date = dt;
	}

	QueryWhere(final String ch, final QueryTaglike tag) {
		check = ch;
		taglike = tag;
	}

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
