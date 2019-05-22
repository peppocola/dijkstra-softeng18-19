package it.uniba.query;

public class QueryGroupBy {

	private String[] attributes;

	QueryGroupBy(String[] attr) {
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
