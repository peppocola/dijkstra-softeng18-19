package it.uniba.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ArgumentsTest {

	private static String commands;

	private static String[] args;

	private static Arguments params;

	@Test
	void testArguments() {

		commands = new String(
				"yyyy=2016 mm=02 dd=11 type=question edge=yes weight=yes user=1109 taglike=java limit=100");
		args = new String(commands).split(" ");

		try {
			params = new Arguments(args);
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
	void testArguments1() {

		commands = new String(
				"yyyy=2016 mm=march dd=11 type=question edge=maybe weight=yes user=1109 taglike=java limit=100");
		args = new String(commands).split(" ");

		assertThrows(ParseException.class, () -> {
			new Arguments(args);
		});

	}


}