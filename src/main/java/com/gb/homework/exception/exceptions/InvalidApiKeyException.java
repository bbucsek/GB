package com.gb.homework.exception.exceptions;

public class InvalidApiKeyException extends Exception {
    public InvalidApiKeyException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
};
