package com.nik.tripfinder.controllers;

import com.nik.tripfinder.exceptions.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<String> handleCustomException(GeneralException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

}
