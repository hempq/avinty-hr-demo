package com.avinty.hr.exception;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
public class MissingIdException extends RuntimeException {

	public MissingIdException() {
		super();
	}

	public MissingIdException(
			final String message,
			final Throwable cause,
			final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MissingIdException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public MissingIdException(final String message) {
		super(message);
	}

	public MissingIdException(final Throwable cause) {
		super(cause);
	}
}
