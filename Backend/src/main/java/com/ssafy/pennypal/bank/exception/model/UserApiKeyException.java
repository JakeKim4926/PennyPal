package com.ssafy.pennypal.bank.exception.model;

public class UserApiKeyException extends RuntimeException {
    public UserApiKeyException() {
        super();
    }

    public UserApiKeyException(String message) {
        super(message);
    }

    public UserApiKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
