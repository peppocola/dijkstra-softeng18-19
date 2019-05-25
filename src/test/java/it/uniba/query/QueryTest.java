package it.uniba.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import it.uniba.main.ArgumentException;
import it.uniba.main.Arguments;

class QueryTest {
	@Test
	void testQuery() {
		String query = "SELECT distinct owner_user_id" + " FROM (SELECT distinct parent_id, owner_user_id"
				+ " FROM `bigquery-public-data.stackoverflow.posts_answers`"
				+ " WHERE (owner_user_id is not null) AND EXTRACT(year FROM creation_date)=2016"
				+ " AND EXTRACT(month FROM creation_date)=2)"
				+ " JOIN (SELECT distinct id FROM `bigquery-public-data.stackoverflow.posts_questions`"
				+ " WHERE (id is not null) AND REGEXP_CONTAINS (tags, r\"java\"))"
				+ " ON parent_id=id order by owner_user_id LIMIT 100";
		try {
			Arguments args = new Arguments("yyyy=2016 mm=02 type=answer taglike=java limit=100".split(" "));
			assertEquals(new Query(args).toString(), query);
		} catch (Exception p) {
			fail("unexpected exception thrown");
		}
	}

	@Test
	void testQuery1() {
		String query = "SELECT distinct owner_user_id"
				+ " FROM `bigquery-public-data.stackoverflow.posts_questions`"
				+ " WHERE (owner_user_id is not null) AND EXTRACT(year FROM creation_date)=2016"
				+ " AND EXTRACT(month FROM creation_date)=2 AND EXTRACT(day FROM creation_date)=11"
				+ " order by owner_user_id LIMIT 100";

		try {
			Arguments args = new Arguments("yyyy=2016 mm=02 dd=11 type=question limit=100".split(" "));
			assertEquals(new Query(args).toString(), query);
		} catch (Exception p) {
			fail("unexpected exception thrown");
		}
	}

	@Test
	void testQuery2() {
		String query = "SELECT `from`, `to`, count(*) as weight " + "FROM (SELECT owner_user_id as `to`, id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE (owner_user_id is not null)) JOIN "
				+ "(SELECT owner_user_id as `from`, parent_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "WHERE (owner_user_id is not null AND parent_id is not null AND owner_user_id=86)) "
				+ "ON id=parent_id group by `from`, `to` order by `from`, `to` LIMIT 100";

		try {
			Arguments args = new Arguments("type=answer user=86 edge=yes weight=yes limit=100".split(" "));
			assertEquals(new Query(args).toString(), query);
		} catch (Exception p) {
			fail("unexpected exception thrown");
		}
	}

	@Test
	void testQuery3() {
		String query = "SELECT distinct `from`, `to` " + "FROM (SELECT owner_user_id as `to`, id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE (owner_user_id is not null) AND EXTRACT(year FROM creation_date)=2016 "
				+ "AND EXTRACT(month FROM creation_date)=2 AND EXTRACT(day FROM creation_date)=11) "
				+ "JOIN (SELECT owner_user_id as `from`, parent_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "WHERE (owner_user_id is not null AND parent_id is not null)) "
				+ "ON id=parent_id order by `from`, `to` LIMIT 100";

		try {
			Arguments args = new Arguments(
					"yyyy=2016 mm=02 dd=11 type=question edge=yes limit=100".split(" "));
			assertEquals(new Query(args).toString(), query);
		} catch (Exception p) {
			fail("unexpected exception thrown");
		}
	}

	@Test
	void testQuery4() {
		String query = "SELECT distinct owner_user_id FROM ("
				+ "SELECT distinct owner_user_id FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "WHERE (owner_user_id is not null) AND EXTRACT(year FROM creation_date)=2016 "
				+ "AND EXTRACT(month FROM creation_date)=2 AND EXTRACT(day FROM creation_date)=11) "
				+ "UNION ALL ("
				+ "SELECT distinct owner_user_id FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE (owner_user_id is not null) AND EXTRACT(year FROM creation_date)=2016 "
				+ "AND EXTRACT(month FROM creation_date)=2 AND EXTRACT(day FROM creation_date)=11) "
				+ "order by owner_user_id LIMIT 100";

		try {
			Arguments args = new Arguments("yyyy=2016 mm=02 dd=11 type=post limit=100".split(" "));
			assertEquals(new Query(args).toString(), query);
		} catch (Exception p) {
			fail("unexpected exception thrown");
		}
	}

	@Test
	void testQuery5() {
		try {
			Arguments args = new Arguments("yyyy=2016 mm=02 dd=11 limit=100".split(" "));
			assertThrows(ArgumentException.class, () -> {
				new Query(args);
			});
		} catch (Exception p) {
			fail("unexpected exception thrown");
		}
	}

	@Test
	void testQuery6() {
		String query = "SELECT distinct owner_user_id FROM " + "((SELECT distinct parent_id, owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "WHERE (owner_user_id is not null) AND EXTRACT(year FROM creation_date)=2016 "
				+ "AND EXTRACT(month FROM creation_date)=2) "
				+ "JOIN (SELECT distinct id FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE (id is not null) AND REGEXP_CONTAINS (tags, r\"java\")) "
				+ "ON parent_id=id) UNION ALL ("
				+ "SELECT owner_user_id FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE (owner_user_id is not null) AND EXTRACT(year FROM creation_date)=2016 "
				+ "AND EXTRACT(month FROM creation_date)=2 AND REGEXP_CONTAINS (tags, r\"java\")) "
				+ "order by owner_user_id LIMIT 100";

		try {
			Arguments args = new Arguments("yyyy=2016 mm=02 type=post taglike=java limit=100".split(" "));
			assertEquals(new Query(args).toString(), query);
		} catch (Exception p) {
			fail("unexpected exception thrown");
		}
	}

	@Test
	void testQuery7() {
		String query = "SELECT `from`, `to`, count(*) as weight " + "FROM (SELECT owner_user_id as `to`, id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE (owner_user_id is not null) AND EXTRACT(year FROM creation_date)=2016 "
				+ "AND EXTRACT(month FROM creation_date)=2 AND EXTRACT(day FROM creation_date)=11) "
				+ "JOIN (SELECT owner_user_id as `from`, parent_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "WHERE (owner_user_id is not null AND parent_id is not null)) "
				+ "ON id=parent_id group by `from`, `to` "
				+ "order by `from`, `to` LIMIT 100";

		try {
			Arguments args = new Arguments(
					("yyyy=2016 mm=02 dd=11 type=question " + "edge=yes weight=yes limit=100")
					.split(" "));
			assertEquals(new Query(args).toString(), query);
		} catch (Exception p) {
			fail("unexpected exception thrown");
		}
	}

	@Test
	void testQuery8() {
		String query = "SELECT distinct `from`, `to` FROM ("
				+ "SELECT owner_user_id as `to`, id FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE (owner_user_id is not null)) "
				+ "JOIN (SELECT owner_user_id as `from`, parent_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "WHERE (owner_user_id is not null AND parent_id is not null AND owner_user_id=86)) "
				+ "ON id=parent_id order by `from`, `to` LIMIT 100";

		try {
			Arguments args = new Arguments("type=answer user=86 edge=yes limit=100".split(" "));
			assertEquals(new Query(args).toString(), query);
		} catch (Exception p) {
			fail("unexpected exception thrown");
		}
	}
}