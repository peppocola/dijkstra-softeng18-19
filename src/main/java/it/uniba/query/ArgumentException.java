package it.uniba.query;

/**
 * Exception thrown when the arguments are invalid.
 * 
 * <i>&#60;noECB&#62;</i>
 */
public class ArgumentException extends Exception {

	/**
	 * The ArgumentException constructor.
	 * 
	 * @param message The error message
	 */
	public ArgumentException(final String message) {
		super(message);
	}
}
