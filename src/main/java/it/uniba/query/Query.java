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
		query = "";

		String date = new QueryDate(args.getDay(), args.getMonth(), args.getYear()).toString();

		if (args.getType() == null) {

			throw new ArgumentException("invalid argument " + args.getType());

		} else if (args.getType().equals("question") && args.getTaglike() == null && args.getEdge() != true
				&& args.getWeight() != true) {
			query += "SELECT distinct owner_user_id\\r\\n FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
					+ "	WHERE " + date
					+ " and owner_user_id is not null\r\n order by owner_user_id\r\n";
		} else if (args.getType().equals("answer") && args.getTaglike() == null && args.getEdge() != true
				&& args.getWeight() != true) {
			query += "SELECT distinct owner_user_id\\r\\n FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n"
					+ " WHERE " + date
					+ " and owner_user_id is not null\r\n order by owner_user_id\r\n";
		} else if (args.getType().equals("post") && args.getTaglike() == null && args.getEdge() != true
				&& args.getWeight() != true) {
			query += "SELECT distinct owner_user_id\\r\\n FROM\r\n" + "((SELECT distinct owner_user_id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n" + " WHERE " + date
					+ " and owner_user_id is not null)\r\n" + "UNION ALL\r\n" + "(SELECT distinct owner_user_id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n" + " WHERE " + date
					+ " and owner_user_id is not null))\r\n order by owner_user_id\r\n";
		} else if (args.getType().equals("answer") && args.getTaglike() != null && args.getEdge() != true
				&& args.getWeight() != true) {
			query += "SELECT distinct owner_user_id\\r\\n FROM\r\n" + "(SELECT distinct parent_id, owner_user_id\r\n"
					+ "FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n" + " WHERE " + date
					+ " and owner_user_id is not null)\r\n" + "JOIN\r\n" + "(SELECT distinct id\r\n"
					+ "FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
					+ " WHERE REGEXP_CONTAINS (tags, r\"" + args.getTaglike() + "\"))\r\n"
					+ "ON parent_id = id\r\n order by owner_user_id\r\n";
		} else if (args.getType().equals("post") && args.getTaglike() != null && args.getEdge() != true
				&& args.getWeight() != true) {
			query += "SELECT distinct owner_user_id\\r\\n FROM (SELECT distinct owner_user_id" + " FROM\r\n"
					+ "(SELECT distinct parent_id, owner_user_id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n" + " WHERE " + date
					+ " and owner_user_id is not null)\r\n" + " JOIN\r\n" + " (SELECT distinct id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
					+ " WHERE REGEXP_CONTAINS (tags, r\"" + args.getTaglike() + "\"))\r\n" + " ON parent_id = id\r\n"
					+ " UNION ALL " + "(SELECT owner_user_id\r\n"
					+ " FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n" + " WHERE " + date
					+ " and REGEXP_CONTAINS(tags, r\"" + args.getTaglike() + "\") \r\n"
					+ " and owner_user_id is not null))\r\n order by owner_user_id\r\n";
		} else if (args.getType().equals("question") && args.getTaglike() != null && args.getEdge() != true
				&& args.getWeight() != true) {
			query += "SELECT distinct owner_user_id\\r\\n FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
					+ " WHERE " + date
					+ " and REGEXP_CONTAINS(tags, r\"" + args.getTaglike() + "\") \r\n"
					+ " and owner_user_id is not null\r\n order by owner_user_id\r\n";
		} else if (args.getType().equals("answer") && args.getTaglike() == null && args.getEdge() == true
				&& args.getWeight() != true && date == "") {
			query += "SELECT distinct `from`,`to` \r\n"
					+ "FROM( \r\n"
					+ "(SELECT owner_user_id as `to`, id \r\n"
					+ "FROM `bigquery-public-data.stackoverflow.posts_questions` \r\n"
					+ "WHERE owner_user_id is not null) \r\n"
					+ "JOIN \r\n"
					+ "(SELECT owner_user_id as `from`, parent_id \r\n"
					+ "FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n"
					+ "WHERE owner_user_id is not null AND parent_id is not null \r\n" 
					+ "AND owner_user_id =" + args.getUser() + ") \r\n"
					+ "ON id= parent_id) \r\n"
					+ "order by `from`,`to` \r\n";
		} else if (args.getType().equals("question") && args.getTaglike() == null && args.getEdge() == true
				&& args.getWeight() != true) {
			query += "SELECT distinct `from`,`to` \r\n" + "FROM(\r\n" + "(SELECT owner_user_id as `to`, id\r\n"
					+ "FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n"
					+ "WHERE owner_user_id is not null AND " + date + ")\r\n" + "JOIN \r\n"
					+ "(SELECT owner_user_id as `from`, parent_id\r\n"
					+ "FROM `bigquery-public-data.stackoverflow.posts_answers`\r\n"
					+ "WHERE owner_user_id is not null AND parent_id is not null)\r\n" + "ON id= parent_id)\r\n"
					+ "order by `from`,`to`\r\n";
		}
		
		query += " LIMIT " + args.getLimit();
	}

	/**
	 * Converts the Query to a string.
	 */
	@Override
	public String toString() {
		return query;
	}
}
