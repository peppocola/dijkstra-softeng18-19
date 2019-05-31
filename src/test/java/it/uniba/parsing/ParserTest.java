package it.uniba.parsing;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {
	private static String commands;
	private static String[] args;

	@Test
	void testParser() {

		commands = new String(
				"yyyy=2016 mm=march dd=11 type=question edge=maybe weight=yes user=1109 taglike=java limit=100");
		args = new String(commands).split(" ");

		assertThrows(ParseException.class, () -> {
			Parser.parse(args);

		});

	}

}
