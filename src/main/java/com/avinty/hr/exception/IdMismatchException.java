package com.avinty.hr.exception;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
public class IdMismatchException extends RuntimeException {

    public IdMismatchException() {
        super();
    }

    public IdMismatchException(
            final String message,
            final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IdMismatchException(final String message) {
        super(message);
    }

    public IdMismatchException(final Throwable cause) {
        super(cause);
    }
}
