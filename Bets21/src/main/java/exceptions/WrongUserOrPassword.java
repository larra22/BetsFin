package exceptions;

public class WrongUserOrPassword extends Exception {
	public WrongUserOrPassword() {
		super();
	}

	/**
	 * This exception is triggered if the event has already finished
	 * 
	 * @param s String of the exception
	 */
	public WrongUserOrPassword(String s) {
		super(s);
	}

}
