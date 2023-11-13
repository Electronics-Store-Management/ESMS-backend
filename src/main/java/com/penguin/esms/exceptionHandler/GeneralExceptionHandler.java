package com.penguin.esms.exceptionHandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<?> handleException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<?> handleNotReadableException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body not found");
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler({ HttpMessageNotReadableException.class })
//    public ResponseEntity<?> handleNotReadableException() {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body not found");
//    }
}
