package it.uniba.main;
/**
 * Exception thrown when a parse error occurs.
 */
public class ParseException extends Exception {
	/**
	 * The ParseException constructor.
	 * @param msg The error message
	 */
	public ParseException(final String msg) {
		super(msg);
	}
}
