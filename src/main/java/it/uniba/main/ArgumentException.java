package it.uniba.main;

/**
 * Exception thrown when the arguments are invalid.
 * 
 * <<noECB>>
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
