package it.uniba.query;

import it.uniba.parsing.Arguments;

/**
 * The builder for a query that doesn't use edges, weigth but uses taglike.
 * 
 * <i>&#60;Entity&#62;</i>
 */
class QueryBuilderUserIDTaglike implements IQueryBuilder {
	private QueryTable query;
	private Arguments args;
	private QueryDate date;
	private QueryTaglike taglike;

	QueryBuilderUserIDTaglike(final Arguments arguments) {
		query = new QueryTable();
		this.args = arguments;
		date = new QueryDate(args.getDay(), args.getMonth(), args.getYear());

		if (date.toString().equals("")) {
			date = null;
		}
		taglike = new QueryTaglike(args.getTaglike());
	}

	/**
	 * Builds the select.
	 * 
	 * @return
	 */
	@Override
	public IQueryBuilder buildSelect() {
		query.setSelect(new QuerySelect(new String[] {"owner_user_id" }, new String[] {}, true));
		return this;
	}

	/**
	 * Builds the table.
	 * 
	 * @return
	 */
	@Override
	public IQueryBuilder buildTable() {

		QueryTable firstTable = new QueryTable(
				new QuerySelect(new String[] {"parent_id", "owner_user_id" }, new String[] {}, true),
				"`bigquery-public-data.stackoverflow.posts_answers`");

		QueryTable secondTable = new QueryTable(new QuerySelect(new String[] {"id" }, new String[] {}, true),
				"`bigquery-public-data.stackoverflow.posts_questions`");
		QueryTable thirdTable = new QueryTable(
				new QuerySelect(new String[] {"owner_user_id" }, new String[] {}),
				"`bigquery-public-data.stackoverflow.posts_questions`");
		if (args.getType().equals("answer") && args.getUser() == 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));
			secondTable.setWhere(new QueryWhere("id is not null", taglike));

			query.setTable(QueryUtils.queryJoin(firstTable.toString(), secondTable.toString(),
					"parent_id=id"));
		} else if (args.getType().equals("post") && args.getUser() == 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			secondTable.setWhere(new QueryWhere("id is not null", taglike));

			thirdTable.setWhere(new QueryWhere("owner_user_id is not null", date, taglike));
			String joinTable = QueryUtils.queryJoin(firstTable.toString(),
					secondTable.toString(), "parent_id=id");

			query.setTable(QueryUtils.queryUnionAll(joinTable, thirdTable.toString()));

		} else if (args.getType().equals("question") && args.getUser() == 0) {
			query.setTable("`bigquery-public-data.stackoverflow.posts_questions`");
		}
		return this;
	}

	/**
	 * Builds the where.
	 * 
	 * @return
	 */
	@Override
	public IQueryBuilder buildWhere() {

		if (args.getType().equals("question") && args.getUser() == 0) {
			query.setWhere(new QueryWhere("owner_user_id is not null", date, taglike));
		}
		return this;
	}

	/**
	 * Builds the group by.
	 * 
	 * @return
	 */
	@Override
	public IQueryBuilder buildGroupBy() {

		return this;
	}

	/**
	 * Builds the order by.
	 * 
	 * @return
	 */
	@Override
	public IQueryBuilder buildOrderBy() {
		query.setOrder(new QueryOrderBy(new String[] {"owner_user_id" }));
		return this;
	}

	/**
	 * Builds the limit.
	 * 
	 * @return
	 */
	@Override
	public IQueryBuilder buildLimit() {
		query.setLimit(new QueryLimit(args.getLimit()));
		return this;
	}

	/**
	 * Returns the queryTable.
	 * 
	 * @return the QueryTable
	 */
	@Override
	public QueryTable getResult() {
		return query;
	}

}
