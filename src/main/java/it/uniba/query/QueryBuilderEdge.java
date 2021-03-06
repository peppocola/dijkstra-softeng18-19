package it.uniba.query;

import it.uniba.parsing.Arguments;

/**
 * The builder for a query that uses edges.
 * 
 * <i>&#60;Entity&#62;</i>
 */
class QueryBuilderEdge implements IQueryBuilder {
	private QueryTable query;
	private Arguments args;
	private QueryDate date;

	QueryBuilderEdge(final Arguments arguments) {
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
	 * @return the query builder
	 */
	@Override
	public IQueryBuilder buildSelect() {
		query.setSelect(new QuerySelect(new String[] {"`from`", "`to`" }, new String[] {}, true));
		return this;
	}

	/**
	 * Builds the table.
	 * 
	 * @return the query builder
	 */
	@Override
	public IQueryBuilder buildTable() {


		QueryTable firstTable = new QueryTable(
				new QuerySelect(new String[] {"owner_user_id", "id" }, new String[] {"`to`" }),
				"`bigquery-public-data.stackoverflow.posts_questions`");
		QueryTable secondTable = new QueryTable(
				new QuerySelect(new String[] {"owner_user_id", "parent_id" }, new String[] {"`from`" }),
				"`bigquery-public-data.stackoverflow.posts_answers`");

		if (args.getType().equals("answer") && args.getUser() != 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			secondTable.setWhere(new QueryWhere("owner_user_id is not null AND "
					+ "parent_id is not null AND owner_user_id=" + args.getUser(), date));
		} else if (args.getType().equals("question") && args.getUser() != 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id=" + args.getUser(), date));

			secondTable.setWhere(new QueryWhere("owner_user_id is not null AND " + "parent_id is not null",
					date));
		} else if (args.getType().equals("question") && args.getUser() == 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));
			secondTable.setWhere(
					new QueryWhere("owner_user_id is not null AND " + "parent_id is not null"));
		}

		query.setTable(QueryUtils.queryJoin(firstTable.toString(), secondTable.toString(),
				"id=parent_id"));
		return this;
	}

	/**
	 * Builds the where.
	 * 
	 * @return the query builder
	 */
	@Override
	public IQueryBuilder buildWhere() {

		return this;
	}

	/**
	 * Builds the group by.
	 * 
	 * @return the query builder
	 */
	@Override
	public IQueryBuilder buildGroupBy() {
		return this;

	}

	/**
	 * Builds the order by.
	 * 
	 * @return the query builder
	 */
	@Override
	public IQueryBuilder buildOrderBy() {
		query.setOrder(new QueryOrderBy(new String[] {"`from`", "`to`" }));
		return this;
	}

	/**
	 * Builds the limit.
	 * 
	 * @return the query builder
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
