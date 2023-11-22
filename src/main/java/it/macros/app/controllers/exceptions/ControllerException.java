package it.macros.app.controllers.exceptions;

@SuppressWarnings("serial")
public class ControllerException extends Exception
{
	public ControllerException() {
		super();
	}

	/**
	 * @param message
	 */
	public ControllerException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ControllerException(Throwable cause) {
		super(cause);
	}
}