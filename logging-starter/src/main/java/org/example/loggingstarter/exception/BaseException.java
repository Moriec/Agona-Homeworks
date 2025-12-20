package org.example.loggingstarter.exception;

public abstract class BaseException extends RuntimeException {
    
    private final String errorCode;

    protected BaseException(String message) {
        super(message);
        this.errorCode = null;
    }

    protected BaseException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
    }

    protected BaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    protected BaseException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public abstract int getHttpStatus();
}

