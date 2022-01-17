package com.epamjwd.provider.exception;

public class UtilityException extends Exception{
    public UtilityException() {
        super();
    }

    public UtilityException(String message) {
        super(message);
    }

    public UtilityException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilityException(Throwable cause) {
        super(cause);
    }
}
