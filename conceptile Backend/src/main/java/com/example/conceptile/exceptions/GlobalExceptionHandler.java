package com.example.conceptile.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp){
        var errors=new HashMap<String,String>();
        exp.getBindingResult().getAllErrors().forEach(error ->{
            var fieldName= ((FieldError)error).getField();
            var errorMessage= error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericExceptionHandler.class)
    public ResponseEntity<?> handleGenericExceptionHandler(GenericExceptionHandler exp){
        Map<String, String> errors = exp.getErrors();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
