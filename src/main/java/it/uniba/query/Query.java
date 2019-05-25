package it.uniba.query;

import it.uniba.main.ArgumentException;
import it.uniba.main.Arguments;

/**
 * The Query class. This class creates the query using an instance of the class
 * Arguments.
 * 
 * Entity
 */
public class Query {

	/**
	 * The query string.
	 */
	private String query;

	/**
	 * The Query constructor.
	 *
	 * @param args The arguments
	 * @throws ArgumentException thrown when the type is null
	 */
	public Query(final Arguments args) throws ArgumentException {
		if (args.getType() == null) {
			throw new ArgumentException("invalid argument " + args.getType());
		}

		if (args.getEdge()) {
			if (args.getWeight()) {
				query = buildEdgeWeightQuery(args);
			} else {
				query = buildEdgeQuery(args);
			}
		} else {
			if (args.getTaglike() == null) {
				query = buildUserIDQuery(args);
			} else {
				query = buildUserIDTaglikeQuery(args);
			}
		}

	}

	private static String buildUserIDQuery(final Arguments args) {
		QueryDate date = new QueryDate(args.getDay(), args.getMonth(), args.getYear());

		if (date.toString() == "") {
			date = null;
		}

		QuerySelect select = new QuerySelect(new String[] {"owner_user_id" }, new String[] {}, true);


		QueryTable queryTable = new QueryTable(select, null);

		if (args.getType().equals("question") && args.getUser() == 0) {
			queryTable.setTable("`bigquery-public-data.stackoverflow.posts_questions`");
			queryTable.setWhere(new QueryWhere("owner_user_id is not null", date));
		} else if (args.getType().equals("answer") && args.getUser() == 0) {
			queryTable.setTable("`bigquery-public-data.stackoverflow.posts_answers`");
			queryTable.setWhere(new QueryWhere("owner_user_id is not null", date));
		} else if (args.getType().equals("post") && args.getUser() == 0) {
			QueryTable firstTable = new QueryTable(
					new QuerySelect(new String[] {"owner_user_id" }, new String[] {}, true),
					"`bigquery-public-data.stackoverflow.posts_answers`");
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			QueryTable secondTable = new QueryTable(
					new QuerySelect(new String[] {"owner_user_id" }, new String[] {}, true),
					"`bigquery-public-data.stackoverflow.posts_questions`");
			secondTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			queryTable.setTable(queryUnionAll(firstTable.toString(), secondTable.toString()));
		}
		QueryOrderBy order = new QueryOrderBy(new String[] { "owner_user_id" });
		QueryLimit limit = new QueryLimit(args.getLimit());

		queryTable.setOrder(order);
		queryTable.setLimit(limit);

		return queryTable.toString();
	}

	private static String buildUserIDTaglikeQuery(final Arguments args) {
		QueryDate date = new QueryDate(args.getDay(), args.getMonth(), args.getYear());

		if (date.toString() == "") {
			date = null;
		}

		QueryTaglike taglike = new QueryTaglike(args.getTaglike());
		QuerySelect select = new QuerySelect(new String[] {"owner_user_id" }, new String[] {}, true);
		QueryOrderBy order = new QueryOrderBy(new String[] {"owner_user_id" });
		QueryLimit limit = new QueryLimit(args.getLimit());

		String table = null;
		QueryWhere where = null;

		QueryTable firstTable = new QueryTable(
				new QuerySelect(new String[] { "parent_id", "owner_user_id" }, new String[] {}, true),
				"`bigquery-public-data.stackoverflow.posts_answers`");

		QueryTable secondTable = new QueryTable(new QuerySelect(new String[] { "id" }, new String[] {}, true),
				"`bigquery-public-data.stackoverflow.posts_questions`");
		QueryTable thirdTable = new QueryTable(new QuerySelect(new String[] { "owner_user_id" }, new String[] {}),
				"`bigquery-public-data.stackoverflow.posts_questions`");
		if (args.getType().equals("answer") && args.getUser() == 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));
			secondTable.setWhere(new QueryWhere("id is not null", taglike));

			table = queryJoin(firstTable.toString(), secondTable.toString(), "parent_id=id");
		} else if (args.getType().equals("post") && args.getUser() == 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			secondTable.setWhere(new QueryWhere("id is not null", taglike));

			thirdTable.setWhere(new QueryWhere("owner_user_id is not null", date, taglike));
			String joinTable = queryJoin(firstTable.toString(), secondTable.toString(), "parent_id=id");

			table = queryUnionAll(joinTable, thirdTable.toString());

		} else if (args.getType().equals("question") && args.getUser() == 0) {
			table = "`bigquery-public-data.stackoverflow.posts_questions`";
			where = new QueryWhere("owner_user_id is not null", date, taglike);
		}

		QueryTable queryTable = new QueryTable(select, table);
		queryTable.setWhere(where);
		queryTable.setOrder(order);
		queryTable.setLimit(limit);

		return queryTable.toString();
	}

	private static String buildEdgeWeightQuery(final Arguments args) {


		QueryDate date = new QueryDate(args.getDay(), args.getMonth(), args.getYear());

		if (date.toString() == "") {
			date = null;
		}

		QuerySelect select = new QuerySelect(new String[] {"`from`", "`to`" }, new String[] {}, "weight");
		QueryOrderBy order = new QueryOrderBy(new String[] {"`from`", "`to`" });
		QueryGroupBy group = new QueryGroupBy(new String[] {"`from`", "`to`" });
		QueryLimit limit = new QueryLimit(args.getLimit());

		String table = null;

		QueryTable firstTable = new QueryTable(
				new QuerySelect(new String[] { "owner_user_id", "id" }, new String[] { "`to`" }),
				"`bigquery-public-data.stackoverflow.posts_questions`");
		QueryTable secondTable = new QueryTable(
				new QuerySelect(new String[] { "owner_user_id", "parent_id" }, new String[] { "`from`" }),
				"`bigquery-public-data.stackoverflow.posts_answers`");

		if (args.getType().equals("answer") && args.getUser() != 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			secondTable.setWhere(new QueryWhere("owner_user_id is not null AND " + "parent_id is not null AND "
							+ "owner_user_id=" + args.getUser()));
		} else if (args.getType().equals("question") && args.getUser() != 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id=" + args.getUser(), date));

			secondTable.setWhere(new QueryWhere("owner_user_id is not null AND " + "parent_id is not null"));
		} else if (args.getType().equals("question") && args.getUser() == 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			secondTable.setWhere(new QueryWhere("owner_user_id is not null AND " + "parent_id is not null"));
		}

		table = queryJoin(firstTable.toString(), secondTable.toString(), "id=parent_id");

		QueryTable queryTable = new QueryTable(select, table);
		queryTable.setOrder(order);
		queryTable.setGroup(group);
		queryTable.setLimit(limit);
		return queryTable.toString();
	}

	private static String buildEdgeQuery(final Arguments args) {

		QueryDate date = new QueryDate(args.getDay(), args.getMonth(), args.getYear());

		if (date.toString() == "") {
			date = null;
		}



		QueryTable firstTable = new QueryTable(
				new QuerySelect(new String[] { "owner_user_id", "id" }, new String[] { "`to`" }),
				"`bigquery-public-data.stackoverflow.posts_questions`");
		QueryTable secondTable = new QueryTable(
				new QuerySelect(new String[] { "owner_user_id", "parent_id" }, new String[] { "`from`" }),
				"`bigquery-public-data.stackoverflow.posts_answers`");

		if (args.getType().equals("answer") && args.getUser() != 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));

			secondTable.setWhere(new QueryWhere("owner_user_id is not null AND "
							+ "parent_id is not null AND owner_user_id=" + args.getUser(),
							date));
		} else if (args.getType().equals("question") && args.getUser() != 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id=" + args.getUser(), date));

			secondTable.setWhere(new QueryWhere("owner_user_id is not null AND " + "parent_id is not null",
							date));
		} else if (args.getType().equals("question") && args.getUser() == 0) {
			firstTable.setWhere(new QueryWhere("owner_user_id is not null", date));
			secondTable.setWhere(new QueryWhere("owner_user_id is not null AND " + "parent_id is not null"));
		}

		String table = queryJoin(firstTable.toString(), secondTable.toString(), "id=parent_id");

		QuerySelect select = new QuerySelect(new String[] { "`from`", "`to`" }, new String[] {}, true);
		QueryOrderBy order = new QueryOrderBy(new String[] { "`from`", "`to`" });
		QueryLimit limit = new QueryLimit(args.getLimit());

		QueryTable queryTable = new QueryTable(select, table);
		queryTable.setOrder(order);
		queryTable.setLimit(limit);
		return queryTable.toString();
	}

	/**
	 * Converts the Query to a string.
	 */
	@Override
	public String toString() {
		return query;
	}

	private static String queryJoin(final String first, final String second, final String condition) {
		return "(" + first + ")" + " JOIN " + "(" + second + ")" + " ON " + condition;
	}

	private static String queryUnionAll(final String first, final String second) {
		return "(" + first + ")" + " UNION ALL " + "(" + second + ")";
	}
}
