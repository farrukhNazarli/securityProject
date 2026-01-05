package com.example.security.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler()
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails=new ErrorDetails(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
