package com.avinty.hr.exception;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
public class GeneralException extends RuntimeException {

	public GeneralException() {
		super();
	}

	public GeneralException(
			final String message,
			final Throwable cause,
			final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GeneralException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public GeneralException(final String message) {
		super(message);
	}

	public GeneralException(final Throwable cause) {
		super(cause);
	}
}
