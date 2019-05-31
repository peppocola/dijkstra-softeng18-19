package it.uniba.parsing;

/**
 * This class parses the input parameters. <i>&#60;NoEcb&#62;</i>
 */
public final class Parser {

	/**
	 * The regular expression used to parse the arguments.
	 */
	private static final String REGEX = "(" + "(yyyy=(\\d){4})|" + "(mm=(\\d){1,2})|" + "(dd=(\\d){1,2})|"
			+ "(type=(question|post|answer))|" + "(taglike=(\\w)+)|" + "(limit=(\\d)+)|"
			+ "(edge=(yes|no))|" + "(weight=(yes|no))|" + "(user=(\\d)+)" + ")";

	private Parser() {
	}
	public static Arguments parse(final String[] args) throws ParseException {

		Arguments parsed = new Arguments();

		for (final String str : args) {
			if (!str.matches(REGEX)) {
				throw new ParseException("unknown pattern: " + str);
			}

			final String[] values = str.split("=");

			switch (values[0]) {
			case "yyyy":
				parsed.setYear(Integer.parseInt(values[1]));
				break;
			case "mm":
				parsed.setMonth(Integer.parseInt(values[1]));
				break;
			case "dd":
				parsed.setDay(Integer.parseInt(values[1]));
				break;
			case "type":
				parsed.setType(values[1]);
				break;
			case "taglike":
				parsed.setTaglike(values[1]);
				break;
			case "limit":
				parsed.setLimit(Integer.parseInt(values[1]));
				break;
			case "edge":
				parsed.setEdge(values[1].equals("yes"));
				break;
			case "weight":
				parsed.setWeight(values[1].equals("yes"));
				break;
			case "user":
				parsed.setUser(Integer.parseInt(values[1]));
				break;
			default:
				break;
			}
		}
		return parsed;
	}
}
