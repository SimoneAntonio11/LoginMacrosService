package it.macros.app.services.exceptions;

import it.macros.app.services.constants.ServiceMessages;

@SuppressWarnings("serial")
public class ServiceValidationException extends ServiceException
{
	public ServiceValidationException() {
		super();
	}

	/**
	 * @param message
	 */
	public ServiceValidationException(String message) {
		super(message);
	}

	/**
	 * @param serviceMessages
	 */
	public ServiceValidationException(ServiceMessages serviceMessages) {
		super(serviceMessages.name());
		this.code = serviceMessages.getCode();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceValidationException(Throwable cause) {
		super(cause);
	}
}