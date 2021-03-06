package it.uniba.parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ArgumentsTest {

	private static String commands;

	private static String[] args;

	private static Arguments params = null;

	private static Parser parser = Parser.getInstance();

	@Test
	void testArguments() {

		commands = new String(
				"yyyy=2016 mm=02 dd=11 type=question edge=yes weight=yes user=1109 taglike=java limit=100");
		args = new String(commands).split(" ");

		try {
			params = parser.parse(args);
		} catch (final ParseException p) {
			fail(p.getMessage());
		}

		assertEquals(params.getDay(), 11);
		assertEquals(params.getMonth(), 2);
		assertEquals(params.getYear(), 2016);
		assertEquals(params.getUser(), 1109);
		assertEquals(params.getType(), "question");
		assertEquals(params.getTaglike(), "java");
		assertEquals(params.getLimit(), 100);
		assertTrue(params.getEdge());
		assertTrue(params.getWeight());
	}

	@Test
	void testToString() {

		commands = new String(
				"yyyy=2016 mm=02 dd=11 type=question edge=yes weight=yes user=1109 taglike=java limit=100");
		args = new String(commands).split(" ");

		try {
			params = parser.parse(args);
		} catch (final ParseException p) {
			fail(p.getMessage());
		}

		String compare = new String(
				"yyyy=2016 mm=2 dd=11 type=question taglike=java limit=100 edge=true weight=true user=1109");

		assertEquals(params.toString(), compare);
	}
}