package it.uniba.query;

import it.uniba.main.ArgumentException;
import it.uniba.main.Arguments;

/**
 * The Query class. This class creates the query using an instance of the class
 * Arguments.
 * 
 * <<Entity>>
 */
public class Query {

	/**
	 * The query table.
	 */
	private QueryTable queryTable;

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

		QueryDate date = new QueryDate(args.getDay(), args.getMonth(), args.getYear());

		if (date.toString() == "") {
			date = null;
		}

		QueryTaglike taglike = null;

		if (args.getTaglike() == null) {
			taglike = null;
		} else {
			taglike = new QueryTaglike(args.getTaglike());
		}

		QuerySelect select = null;
		String table = null;
		QueryWhere where = null;
		QueryOrderBy order = null;
		QueryGroupBy group = null;
		QueryLimit limit = null;

		if (args.getEdge()) {
			QueryTable firstTable = null;
			QueryTable secondTable = null;

			if (args.getWeight()) {
				select = new QuerySelect(new String[] { "`from`", "`to`" }, new String[] {}, "weight");

				if (args.getType().equals("answer") && args.getUser() != 0) {
					firstTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "id" },
									new String[] { "`to`" }),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("owner_user_id is not null", date));

					secondTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "parent_id" },
									new String[] { "`from`" }),
							"`bigquery-public-data.stackoverflow.posts_answers`",
							new QueryWhere("owner_user_id is not null AND "
									+ "parent_id is not null AND "
									+ "owner_user_id=" + args.getUser()));
				} else if (args.getType().equals("question") && args.getUser() != 0) {
					firstTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "id" },
									new String[] { "`to`" }),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("owner_user_id=" + args.getUser(), date));

					secondTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "parent_id" },
									new String[] { "`from`" }),
							"`bigquery-public-data.stackoverflow.posts_answers`",
							new QueryWhere("owner_user_id is not null AND "
									+ "parent_id is not null"));
				} else if (args.getType().equals("question") && args.getUser() == 0) {
					firstTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "id" },
									new String[] { "`to`" }),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("owner_user_id is not null", date));

					secondTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "parent_id" },
									new String[] { "`from`" }),
							"`bigquery-public-data.stackoverflow.posts_answers`",
							new QueryWhere("owner_user_id is not null AND "
									+ "parent_id is not null"));
				}
			} else {
				select = new QuerySelect(new String[] { "`from`", "`to`" }, new String[] {}, true);

				if (args.getType().equals("answer") && args.getTaglike() == null
						&& args.getUser() != 0) {
					firstTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "id" },
									new String[] { "`to`" }),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("owner_user_id is not null", date));

					secondTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "parent_id" },
									new String[] { "`from`" }),
							"`bigquery-public-data.stackoverflow.posts_answers`",
							new QueryWhere("owner_user_id is not null AND "
									+ "parent_id is not null AND owner_user_id="
									+ args.getUser(), date));
				} else if (args.getType().equals("question") && args.getTaglike() == null
						&& args.getUser() != 0) {
					firstTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "id" },
									new String[] { "`to`" }),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("owner_user_id=" + args.getUser(), date));

					secondTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "parent_id" },
									new String[] { "`from`" }),
							"`bigquery-public-data.stackoverflow.posts_answers`",
							new QueryWhere("owner_user_id is not null AND "
									+ "parent_id is not null", date));
				} else if (args.getType().equals("question") && args.getTaglike() == null
						&& args.getUser() == 0) {
					firstTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "id" },
									new String[] { "`to`" }),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("owner_user_id is not null", date));

					secondTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id", "parent_id" },
									new String[] { "`from`" }),
							"`bigquery-public-data.stackoverflow.posts_answers`",
							new QueryWhere("owner_user_id is not null AND "
									+ "parent_id is not null"));
				}
			}

			table = queryJoin(firstTable.toString(), secondTable.toString(), "id=parent_id");

			order = new QueryOrderBy(new String[] { "`from`", "`to`" });

			group = new QueryGroupBy(new String[] { "`from`", "`to`" });
		} else {
			select = new QuerySelect(new String[] { "owner_user_id" }, new String[] {}, true);

			if (taglike == null) {
				if (args.getType().equals("question") && args.getUser() == 0) {
					table = "`bigquery-public-data.stackoverflow.posts_questions`";
					where = new QueryWhere("owner_user_id is not null", date);
				} else if (args.getType().equals("answer") && args.getUser() == 0) {
					table = "`bigquery-public-data.stackoverflow.posts_answers`";
					where = new QueryWhere("owner_user_id is not null", date);
				} else if (args.getType().equals("post") && args.getUser() == 0) {
					QueryTable firstTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id" },
									new String[] {}, true),
							"`bigquery-public-data.stackoverflow.posts_answers`",
							new QueryWhere("owner_user_id is not null", date));

					QueryTable secondTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id" },
									new String[] {}, true),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("owner_user_id is not null", date));

					table = queryUnionAll(firstTable.toString(), secondTable.toString());
				}
			} else {
				if (args.getType().equals("answer") && args.getUser() == 0) {
					QueryTable firstTable = new QueryTable(
							new QuerySelect(new String[] { "parent_id", "owner_user_id" },
									new String[] {}, true),
							"`bigquery-public-data.stackoverflow.posts_answers`",
							new QueryWhere("owner_user_id is not null", date));

					QueryTable secondTable = new QueryTable(
							new QuerySelect(new String[] { "id" }, new String[] {}, true),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("id is not null", taglike));

					table = queryJoin(firstTable.toString(), secondTable.toString(),
							"parent_id=id");
				} else if (args.getType().equals("post") && args.getUser() == 0) {
					QueryTable firstTable = new QueryTable(
							new QuerySelect(new String[] { "parent_id", "owner_user_id" },
									new String[] {}, true),
							"`bigquery-public-data.stackoverflow.posts_answers`",
							new QueryWhere("owner_user_id is not null", date));

					QueryTable secondTable = new QueryTable(
							new QuerySelect(new String[] { "id" }, new String[] {}, true),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("id is not null", taglike));

					QueryTable thirdTable = new QueryTable(
							new QuerySelect(new String[] { "owner_user_id" },
									new String[] {}),
							"`bigquery-public-data.stackoverflow.posts_questions`",
							new QueryWhere("owner_user_id is not null", date, taglike));

					String joinTable = queryJoin(firstTable.toString(), secondTable.toString(),
							"parent_id=id");

					table = queryUnionAll(joinTable, thirdTable.toString());

				} else if (args.getType().equals("question") && args.getUser() == 0) {
					table = "`bigquery-public-data.stackoverflow.posts_questions`";
					where = new QueryWhere("owner_user_id is not null", date, taglike);
				}

			}

			order = new QueryOrderBy(new String[] { "owner_user_id" });
		}

		limit = new QueryLimit(args.getLimit());

		queryTable = new QueryTable(select, table, where, order, group, limit);
	}

	/**
	 * Converts the Query to a string.
	 */
	@Override
	public String toString() {
		return queryTable.toString();
	}

	private static String queryJoin(String first, String second, String condition) {
		return "(" + first + ")" + " JOIN " + "(" + second + ")" + " ON " + condition;
	}

	private static String queryUnionAll(String first, String second) {
		return "(" + first + ")" + " UNION ALL " + "(" + second + ")";
	}
}
