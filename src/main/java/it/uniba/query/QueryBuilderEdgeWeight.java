package it.uniba.query;

import it.uniba.parsing.Arguments;

/**
 * The builder for a query that uses edges and weight.
 * 
 * <i>&#60;Entity&#62;</i>
 */
class QueryBuilderEdgeWeight implements IQueryBuilder {

	private QueryTable query;
	private Arguments args;
	private QueryDate date;

	QueryBuilderEdgeWeight(final Arguments arguments) {
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
		query.setSelect(new QuerySelect(new String[] {"`from`", "`to`" }, new String[] {}, "weight"));
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
				new QuerySelect(new String[] {"owner_user_id", "id" }, new String[] {"`to`" }),
				"`bigquery-public-data.stackoverflow.posts_questions`");
		QueryTable secondTable = new QueryTable(
				new QuerySelect(new String[] {"owner_user_id", "parent_id" }, new String[] {"`from`" }),
				"`bigquery-public-data.stackoverflow.posts_answers`");

		if (args.getType().equals("answer") && args.getUser() != 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			secondTable.setWhere(new QueryWhere("owner_user_id is not null AND "
					+ "parent_id is not null AND " + "owner_user_id=" + args.getUser()));
		} else if (args.getType().equals("question") && args.getUser() != 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id=" + args.getUser(), date));

			secondTable.setWhere(
					new QueryWhere("owner_user_id is not null AND " + "parent_id is not null"));
		} else if (args.getType().equals("question") && args.getUser() == 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			secondTable.setWhere(
					new QueryWhere("owner_user_id is not null AND " + "parent_id is not null"));
		}

		query.setTable(QueryUtils.getInstance().queryJoin(firstTable.toString(), secondTable.toString(),
				"id=parent_id"));
		return this;
	}

	/**
	 * Builds the where.
	 * 
	 * @return
	 */
	@Override
	public IQueryBuilder buildWhere() {
		return this;
	}

	/**
	 * Builds the group by.
	 * 
	 * @return
	 */
	@Override
	public IQueryBuilder buildGroupBy() {
		query.setGroup(new QueryGroupBy(new String[] {"`from`", "`to`" }));
		return this;
	}

	/**
	 * Builds the order by.
	 * 
	 * @return
	 */
	@Override
	public IQueryBuilder buildOrderBy() {
		query.setOrder(new QueryOrderBy(new String[] {"`from`", "`to`" }));
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
