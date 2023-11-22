package it.macros.app.services.holders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class BaseHolder
{
	@Autowired
	protected MessageSource messageSource = null;

	/**
	 * @param key
	 * @return String
	 */
	protected String getText(String key) {
		return getText(key, null);
	}

	/**
	 * @param key
	 * @param args
	 * @return String
	 */
	protected String getText(String key, Object[] args) {
		return messageSource.getMessage(key, args, null, null);
	}
}