package it.uniba.query;

import it.uniba.parsing.Arguments;

/**
 * Builds the query calling the right builder.
 *
 */
public class QueryDirector {

	private IQueryBuilder builder;
	private QueryTable query;

	public QueryDirector(final Arguments args) throws ArgumentException {
		if (args.getType() == null) {
			throw new ArgumentException("invalid argument " + args.getType());
		}

		if (args.getEdge()) {
			if (args.getWeight()) {
				builder = new QueryBuilderEdgeWeight(args);
			} else {
				builder = new QueryBuilderEdge(args);
			}
		} else {
			if (args.getTaglike() == null) {
				builder = new QueryBuilderUserID(args);
			} else {
				builder = new QueryBuilderUserIDTaglike(args);
			}
		}

	}

	/**
	 * Assemble the query.
	 */
	public QueryDirector construct() {
		query = builder.buildSelect().buildTable().buildWhere().buildGroupBy().buildOrderBy().buildLimit()
				.getResult();
		return this;
	}

	/**
	 * Returns the query.
	 * 
	 * @return the query or null if construct() is not used first
	 */
	public String getQuery() {

		return query.toString();
	}
}
