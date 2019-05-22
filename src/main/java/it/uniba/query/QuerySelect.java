package it.uniba.query;

class QuerySelect {

	private String[] attributes;

	private String[] aliases;

	private String countAlias;

	private boolean distinct;

	QuerySelect(final String[] attr, final String[] als) {
		attributes = attr;
		aliases = als;
	}

	QuerySelect(final String[] attr, final String[] als, final String count) {
		attributes = attr;
		aliases = als;
		countAlias = count;
	}

	QuerySelect(final String[] attr, final String[] als, boolean dist) {
		attributes = attr;
		aliases = als;
		distinct = dist;
	}

	QuerySelect(final String[] attr, final String[] als, final String count, boolean dist) {
		attributes = attr;
		aliases = als;
		countAlias = count;
		distinct = dist;
	}

	@Override
	public String toString() {
		String str = "SELECT ";

		if (distinct) {
			str += "distinct ";
		}

		int i = 0;

		for (; i < attributes.length - 1; i++) {
			if (i >= aliases.length) {
				str += attributes[i] + ", ";
			} else {
				str += attributes[i] + " as " + aliases[i] + ", ";
			}
		}

		if (i >= aliases.length) {
			str += attributes[i];
		} else {
			str += attributes[i] + " as " + aliases[i];
		}

		if (countAlias != null) {
			str += ", count(*) as " + countAlias;
		}

		return str;
	}
}
