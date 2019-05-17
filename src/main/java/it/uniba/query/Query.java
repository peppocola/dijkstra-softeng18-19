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
		String date = new QueryDate(args.getDay(), args.getMonth(), args.getYear()).toString();

		String dataCond = "";
		if (date != "") {
			dataCond = "AND " + date;
		}

		if (args.getType() == null) {
			throw new ArgumentException("invalid argument " + args.getType());
		}

		if (args.getEdge()) {
			if (args.getWeight()) {
				if (args.getType().equals("answer") && args.getTaglike() == null
						&& args.getUser() != 0) {
					query = "SELECT `from`,`to`, count(*) as weight " + "FROM( "
							+ "(SELECT owner_user_id as `to`, id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ "WHERE owner_user_id is not null " + dataCond + ") "
							+ "JOIN  " + "(SELECT owner_user_id as `from`, parent_id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ "WHERE owner_user_id is not null "
							+ "AND parent_id is not null AND owner_user_id ="
							+ args.getUser() + ") " + "ON id= parent_id) "
							+ "group by `from`,`to` " + " " + "order by `from`,`to` " + " ";
				} else if (args.getType().equals("question") && args.getTaglike() == null
						&& args.getUser() != 0) {
					query = "SELECT distinct `from`,`to`, count (*) as weight  " + "FROM( "
							+ "(SELECT owner_user_id as `to`, id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ "WHERE owner_user_id is not null AND owner_user_id ="
							+ args.getUser() + " " + dataCond + ") " + "JOIN  "
							+ "(SELECT owner_user_id as `from`, parent_id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ "WHERE owner_user_id is not null AND parent_id is not null "
							+ dataCond + ") " + "ON id= parent_id) "
							+ "group by `from`,`to` " + "order by `from`,`to` ";
				} else if (args.getType().equals("question") && args.getTaglike() == null
						&& args.getUser() == 0) {
					query = "SELECT distinct `from`,`to`, count(*) as weight  " + "FROM( "
							+ "(SELECT owner_user_id as `to`, id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ "WHERE owner_user_id is not null " + dataCond + ") "
							+ "JOIN  " + "(SELECT owner_user_id as `from`, parent_id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ "WHERE owner_user_id is not null AND parent_id is not null"
							+ ") " + "ON id= parent_id) " + "group by `from`,`to` "
							+ "order by `from`,`to` ";
				}

			} else {
				if (args.getType().equals("answer") && args.getTaglike() == null
						&& args.getUser() != 0) {
					query = "SELECT distinct `from`,`to`  " + "FROM(  "
							+ "(SELECT owner_user_id as `to`, id  "
							+ "FROM `bigquery-public-data.stackoverflow.posts_questions`  "
							+ "WHERE owner_user_id is not null " + dataCond + ") "
							+ "JOIN  " + "(SELECT owner_user_id as `from`, parent_id  "
							+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ "WHERE owner_user_id is not null AND parent_id is not null "
							+ dataCond + " " + "AND owner_user_id =" + args.getUser()
							+ ")  " + "ON id= parent_id)  " + "order by `from`,`to`  ";
				} else if (args.getType().equals("question") && args.getTaglike() == null
						&& args.getUser() != 0) {
					query = "SELECT distinct `from`,`to`  " + "FROM( "
							+ "(SELECT owner_user_id as `to`, id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ "WHERE owner_user_id is not null AND owner_user_id = "
							+ args.getUser() + " " + dataCond + ") " + "JOIN  "
							+ "(SELECT owner_user_id as `from`, parent_id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ "WHERE owner_user_id is not null AND parent_id is not null "
							+ dataCond + ") " + "ON id= parent_id) "
							+ "order by `from`,`to` ";
				} else if (args.getType().equals("question") && args.getTaglike() == null
						&& args.getUser() == 0) {
					query = "SELECT distinct `from`,`to`  " + "FROM( "
							+ "(SELECT owner_user_id as `to`, id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ "WHERE owner_user_id is not null " + dataCond + ") "
							+ "JOIN  " + "(SELECT owner_user_id as `from`, parent_id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ "WHERE owner_user_id is not null AND parent_id is not null) "
							+ "ON id= parent_id) " + "order by `from`,`to` ";
				}
			}

		} else {
			if (args.getTaglike() == null) {
				if (args.getType().equals("question") && !args.getWeight() && args.getUser() == 0) {
					query = "SELECT distinct owner_user_id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ "WHERE owner_user_id is not null " + dataCond + " "
							+ "order by owner_user_id ";
				} else if (args.getType().equals("answer") && !args.getWeight()
						&& args.getUser() == 0) {
					query = "SELECT distinct owner_user_id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ " WHERE owner_user_id is not null " + dataCond + " "
							+ "order by owner_user_id ";
				} else if (args.getType().equals("post") && !args.getWeight() && args.getUser() == 0) {
					query = "SELECT distinct owner_user_id  FROM "
							+ "((SELECT distinct owner_user_id "
							+ " FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ " WHERE owner_user_id is not null " + dataCond + ") "
							+ "UNION ALL " + "(SELECT distinct owner_user_id "
							+ " FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ " WHERE owner_user_id is not null " + dataCond + ")) "
							+ "order by owner_user_id ";
				}
			} else {
				if (args.getType().equals("answer") && !args.getWeight() && args.getUser() == 0) {
					query = "SELECT distinct owner_user_id  FROM "
							+ "(SELECT distinct parent_id, owner_user_id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ " WHERE owner_user_id is not null " + dataCond + ") "
							+ "JOIN " + "(SELECT distinct id "
							+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ " WHERE REGEXP_CONTAINS (tags, r\"" + args.getTaglike()
							+ "\")) " + "ON parent_id = id  order by owner_user_id ";
				} else if (args.getType().equals("post") && !args.getWeight() && args.getUser() == 0) {
					query = "SELECT distinct owner_user_id  FROM (SELECT distinct owner_user_id"
							+ " FROM " + "(SELECT distinct parent_id, owner_user_id "
							+ " FROM `bigquery-public-data.stackoverflow.posts_answers` "
							+ " WHERE owner_user_id is not null " + dataCond + ") "
							+ " JOIN " + " (SELECT distinct id "
							+ " FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ " WHERE REGEXP_CONTAINS (tags, r\"" + args.getTaglike()
							+ "\")) " + " ON parent_id = id " + " UNION ALL "
							+ "(SELECT owner_user_id "
							+ " FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ " WHERE REGEXP_CONTAINS(tags, r\"" + args.getTaglike()
							+ "\") " + dataCond + " "
							+ " and owner_user_id is not null))  order by owner_user_id ";
				} else if (args.getType().equals("question") && !args.getWeight()
						&& args.getUser() == 0) {
					query = "SELECT distinct owner_user_id "
							+ " FROM `bigquery-public-data.stackoverflow.posts_questions` "
							+ " WHERE REGEXP_CONTAINS(tags, r\"" + args.getTaglike()
							+ "\") " + dataCond + " "
							+ " and owner_user_id is not null  order by owner_user_id ";
				}
			}
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
