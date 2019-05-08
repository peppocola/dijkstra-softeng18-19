package it.uniba.query;

import it.uniba.main.ArgumentException;
import it.uniba.main.Arguments;

/**
 * The Query class.
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
		query = "SELECT distinct owner_user_id\r\n";

		String date = new QueryDate(args.getDay(), args.getMonth(), args.getYear()).toString();

		if (args.getType() == null) {

			throw new ArgumentException("invalid argument " + args.getType());

		} else if (args.getType().equals("question") && args.getTaglike() == null) {
			query += " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n" + "	WHERE " + date
					+ " and owner_user_id is not null\r\n";
		} else if (args.getType().equals("answer") && args.getTaglike() == null) {
			query += " FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n" + " WHERE " + date
					+ " and owner_user_id is not null\r\n";
		} else if (args.getType().equals("post") && args.getTaglike() == null) {
			query += "FROM\r\n" + "((SELECT distinct owner_user_id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n" + " WHERE " + date
					+ " and owner_user_id is not null)\r\n" + "UNION ALL\r\n" + "(SELECT distinct owner_user_id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n" + " WHERE " + date
					+ " and owner_user_id is not null))\r\n";
		} else if (args.getType().equals("answer") && args.getTaglike() != null) {
			query += "FROM\r\n" + "(SELECT distinct parent_id, owner_user_id\r\n"
					+ "FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n" + " WHERE " + date
					+ " and owner_user_id is not null)\r\n" + "JOIN\r\n" + "(SELECT distinct id\r\n"
					+ "FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
					+ " WHERE REGEXP_CONTAINS (tags, r\"" + args.getTaglike() + "\"))\r\n" + "ON parent_id = id\r\n";
		} else if (args.getType().equals("post") && args.getTaglike() != null) {
			query += " FROM (SELECT distinct owner_user_id" + " FROM\r\n"
					+ "(SELECT distinct parent_id, owner_user_id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n" + " WHERE " + date
					+ " and owner_user_id is not null)\r\n" + " JOIN\r\n" + " (SELECT distinct id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
					+ " WHERE REGEXP_CONTAINS (tags, r\"" + args.getTaglike() + "\"))\r\n" + " ON parent_id = id\r\n"
					+ " UNION ALL " + "(SELECT owner_user_id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n" + " WHERE " + date
					+ " and REGEXP_CONTAINS(tags, r\"" + args.getTaglike() + "\") \r\n"
					+ " and owner_user_id is not null))\r\n";
		} else if (args.getType().equals("question") && args.getTaglike() != null) {
			query += " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n" + " WHERE " + date
					+ " and REGEXP_CONTAINS(tags, r\"" + args.getTaglike() + "\") \r\n"
					+ " and owner_user_id is not null\r\n";
		}

		query += " order by owner_user_id\r\n" + " LIMIT " + args.getLimit();
	}

	/**
	 * Converts the Query to a string.
	 */
	@Override
	public String toString() {
		return query;
	}
}
