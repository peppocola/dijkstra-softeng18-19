package it.uniba.main;
/**
 * Exception thrown when the arguments are invalid.
 */
public class ArgumentException extends Exception {
	
	/**
	 * The ArgumentException constructor.
	 * @param message The error message
	 */
	public ArgumentException(String message) {
		super(message);
	}
}
