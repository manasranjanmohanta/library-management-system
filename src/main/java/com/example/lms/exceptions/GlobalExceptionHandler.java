package com.example.lms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException exception) {
        String message = exception.getMessage();
        ErrorMessage response = new ErrorMessage();
        response.setMessage(message);
        response.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String message = "Invalid input: '" + ex.getValue() + "' is not a valid type";
        ErrorMessage response = new ErrorMessage();
        response.setMessage(message);
        response.setStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorMessage> handleIllegalStateException(IllegalStateException exception) {
        String message = exception.getMessage();
        ErrorMessage response = new ErrorMessage();
        response.setMessage(message);
        response.setStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(response);
    }
}
