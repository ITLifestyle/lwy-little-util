package com.lwy.littleutil.comparefield.exception;

public class NoCompareKeyException extends RuntimeException{
    public NoCompareKeyException() {
    }

    public NoCompareKeyException(String message) {
        super(message);
    }

    public NoCompareKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCompareKeyException(Throwable cause) {
        super(cause);
    }

    public NoCompareKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
