package exceptions;

public class ResultAlreadyExists extends Exception {
	public ResultAlreadyExists() {
		super();
	}

	/**
	 * This exception is triggered if the event has already finished
	 * 
	 * @param s String of the exception
	 */
	public ResultAlreadyExists(String s) {
		super(s);
	}
}
