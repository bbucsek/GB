package com.gb.homework.exception;

import com.gb.homework.exception.exceptions.InvalidApiKeyException;
import com.gb.homework.exception.exceptions.WrongInputException;
import com.gb.homework.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof InvalidApiKeyException) {

            HttpStatus status = HttpStatus.FORBIDDEN;
            InvalidApiKeyException exception = (InvalidApiKeyException) ex;

            return handleException(exception, headers, status, request);
        }

        HttpStatus status = HttpStatus.BAD_REQUEST;
        WrongInputException exception = (WrongInputException) ex;
        return handleException(exception, headers, status, request);
    }

    private ResponseEntity<ApiError> handleException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new ApiError(ex.getMessage(), status), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(body, headers, status);
    }
}
