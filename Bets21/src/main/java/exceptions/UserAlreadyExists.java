package exceptions;

public class UserAlreadyExists extends Exception {
	public UserAlreadyExists() {
		super();
	}

	/**
	 * This exception is triggered if the event has already finished
	 * 
	 * @param s String of the exception
	 */
	public UserAlreadyExists(String s) {
		super(s);
	}

}
