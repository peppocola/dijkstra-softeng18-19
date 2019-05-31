package it.uniba.sotorrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;

import it.uniba.parsing.ParseException;
import it.uniba.parsing.Parser;
import it.uniba.query.ArgumentException;
import it.uniba.query.Query;
import it.uniba.query.QueryResults;

class SOQueryTest {
	ISOQuery soq;

	@Test
	void testgetResultOneColumn() {
		try {
			soq = new SOQuery();
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
		Job job = null;
		try {
			job = soq.runQuery(new Query(
					Parser.parse("yyyy=2016 mm=02 type=answer taglike=java limit=10".split(" "))));
		} catch (InterruptedException e) {
			fail(e.getMessage());
		} catch (ArgumentException e) {
			fail(e.getMessage());
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		try {
			QueryResults res = new QueryResults();
			List<String> cols = new ArrayList<String>();
			cols.add("owner_user_id");
			res.setColumns(cols);
			res.addTuple(new String[] {"13" });
			res.addTuple(new String[] {"371" });
			res.addTuple(new String[] {"476" });
			res.addTuple(new String[] {"700" });
			res.addTuple(new String[] {"806" });
			res.addTuple(new String[] {"1114" });
			res.addTuple(new String[] {"1190" });
			res.addTuple(new String[] {"1288" });
			res.addTuple(new String[] {"1354" });
			res.addTuple(new String[] {"1363" });
			assertEquals(res, soq.getResults(job));
		} catch (JobException e) {
			fail(e.getMessage());
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testgetResultTwoColumns() {
		try {
			soq = new SOQuery();
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
		Job job = null;
		try {
			job = soq.runQuery(
					new Query(Parser.parse("type=answer user=86 edge=yes limit=100".split(" "))));
		} catch (InterruptedException e) {
			fail(e.getMessage());
		} catch (ArgumentException e) {
			fail(e.getMessage());
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		try {
			QueryResults res = new QueryResults();
			List<String> cols = new ArrayList<String>();
			cols.add("from");
			cols.add("to");
			res.setColumns(cols);
			res.addTuple(new String[] {"86", "58" });
			res.addTuple(new String[] {"86", "78" });
			res.addTuple(new String[] {"86", "86" });
			res.addTuple(new String[] {"86", "87" });
			res.addTuple(new String[] {"86", "104" });
			res.addTuple(new String[] {"86", "105" });
			res.addTuple(new String[] {"86", "219" });
			res.addTuple(new String[] {"86", "260" });
			res.addTuple(new String[] {"86", "318" });
			res.addTuple(new String[] {"86", "362" });
			assertEquals(res, soq.getResults(job));
		} catch (JobException e) {
			fail(e.getMessage());
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testgetResultThreeColumns() {
		try {
			soq = new SOQuery();
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
		Job job = null;
		try {
			job = soq.runQuery(new Query(
					Parser.parse("type=answer user=86 edge=yes weight=yes limit=100".split(" "))));
		} catch (InterruptedException e) {
			fail(e.getMessage());
		} catch (ArgumentException e) {
			fail(e.getMessage());
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		try {
			QueryResults res = new QueryResults();
			List<String> cols = new ArrayList<String>();
			cols.add("from");
			cols.add("to");
			cols.add("weight");
			res.setColumns(cols);
			res.addTuple(new String[] {"86", "58", "1" });
			res.addTuple(new String[] {"86", "78", "1" });
			res.addTuple(new String[] {"86", "86", "10" });
			res.addTuple(new String[] {"86", "87", "1" });
			res.addTuple(new String[] {"86", "104", "1" });
			res.addTuple(new String[] {"86", "105", "1" });
			res.addTuple(new String[] {"86", "219", "2" });
			res.addTuple(new String[] {"86", "260", "1" });
			res.addTuple(new String[] {"86", "318", "1" });
			res.addTuple(new String[] {"86", "362", "1" });
			assertEquals(res, soq.getResults(job));
		} catch (JobException e) {
			fail(e.getMessage());
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
	}

}
