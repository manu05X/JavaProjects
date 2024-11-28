package org.example.evaluations.evaluation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<String> handleUserError(Exception e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IndexOutOfBoundsException.class})
    public ResponseEntity<String> handleInternalErrors(Exception e) {
        return new ResponseEntity<>("Something went bad at our side",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class,NumberFormatException.class})
    public ResponseEntity<String> handleTypeCastingExceptions(Exception e) {
        return new ResponseEntity<>("Please pass userId or cartId in correct format",HttpStatus.CONFLICT);
    }
}
