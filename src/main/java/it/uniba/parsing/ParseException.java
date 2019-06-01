package it.uniba.parsing;

/**
 * Exception thrown when a parse error occurs.
 * 
 * <i>&#60;noECB&#62;</i>
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
