package it.uniba.query;

class QueryTaglike {

	private String taglike;

	QueryTaglike(final String tag) {
		taglike = tag;
	}

	String getTaglike() {
		return taglike;
	}

	@Override
	public String toString() {
		return "REGEXP_CONTAINS (tags, r\"" + taglike + "\")";
	}
}