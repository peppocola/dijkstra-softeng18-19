package it.uniba.query;

class QueryGroupBy {

	private String[] attributes;

	QueryGroupBy(final String[] attr) {
		attributes = attr;
	}

	@Override
	public String toString() {
		String str = "group by ";

		int i = 0;

		for (; i < attributes.length - 1; i++) {
			str += attributes[i] + ", ";
		}

		str += attributes[i];

		return str;
	}
}
