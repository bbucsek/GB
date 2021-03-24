package com.gb.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiError {

    private HttpStatus status;
    private String message;


    public ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}