package it.uniba.main;

/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 * 
 * <b>DO NOT RENAME</b>
 */
public final class AppMain {

	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {

	}

	/**
	 * This is the main entry of the application.
	 *
	 * @param args
	 *            The command-line arguments.
	 */
	public static void main(final String[] args) {
		
		if(args.length > 0) {
			switch (args[0]) {
			case "it":
				System.out.println("Ciao mondo");
				break;
				
			case "es":
				System.out.println("Hola Mundo");
				break;

			default:
				System.out.println("Specify the language. Languages supported: 'it' or 'es'.");
				break;
			
			}
		} else {
			System.out.println("Hello world.");
		}
		
	}

}
