package it.uniba.query;

import it.uniba.parsing.Arguments;

/**
 * The builder for a query that doesn't use edges, weigth and taglike.
 * 
 * <i>&#60;Entity&#62;</i>
 */
class QueryBuilderUserID implements IQueryBuilder {

	private QueryTable query;
	private Arguments args;
	private QueryDate date;

	QueryBuilderUserID(final Arguments arguments) {
		query = new QueryTable();
		this.args = arguments;
		date = new QueryDate(args.getDay(), args.getMonth(), args.getYear());

		if (date.toString().equals("")) {
			date = null;
		}
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


		if (args.getType().equals("question") && args.getUser() == 0) {
			query.setTable("`bigquery-public-data.stackoverflow.posts_questions`");

		} else if (args.getType().equals("answer") && args.getUser() == 0) {
			query.setTable("`bigquery-public-data.stackoverflow.posts_answers`");

		} else if (args.getType().equals("post") && args.getUser() == 0) {
			QueryTable firstTable = new QueryTable(
					new QuerySelect(new String[] {"owner_user_id" }, new String[] {}, true),
					"`bigquery-public-data.stackoverflow.posts_answers`");
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			QueryTable secondTable = new QueryTable(
					new QuerySelect(new String[] {"owner_user_id" }, new String[] {}, true),
					"`bigquery-public-data.stackoverflow.posts_questions`");
			secondTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			query.setTable(QueryUtils.queryUnionAll(firstTable.toString(),
					secondTable.toString()));
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

			query.setWhere(new QueryWhere("owner_user_id is not null", date));
		} else if (args.getType().equals("answer") && args.getUser() == 0) {

			query.setWhere(new QueryWhere("owner_user_id is not null", date));
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
