package exceptions;

public class PronosticoAlreadyExists extends Exception {
	public PronosticoAlreadyExists() {
		super();
	}

	/**
	 * This exception is triggered if the event has already finished
	 * 
	 * @param s String of the exception
	 */
	public PronosticoAlreadyExists(String s) {
		super(s);
	}

}
