package com.nik.tripfinder.controllers;

import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.payloads.responses.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(GeneralException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

}
