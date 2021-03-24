package com.gb.homework.exception.exceptions;

public class WrongInputException extends Exception {
    public WrongInputException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
};
