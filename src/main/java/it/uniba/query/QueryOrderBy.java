package it.uniba.query;

class QueryOrderBy {

	private String[] attributes;

	QueryOrderBy(String[] attr) {
		attributes = attr;
	}

	@Override
	public String toString() {
		String str = "order by ";

		int i = 0;

		for (; i < attributes.length - 1; i++) {
			str += attributes[i] + ", ";
		}

		str += attributes[i];

		return str;
	}
}
