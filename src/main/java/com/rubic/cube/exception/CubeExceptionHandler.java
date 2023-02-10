package com.rubic.cube.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.stream.Collectors;

@ControllerAdvice
public class CubeExceptionHandler {

    @ExceptionHandler(BusinessCodeException.class)
    public ResponseEntity<BusinessCodeExceptionModel> handleException(BusinessCodeException productException) {
        BusinessCodeExceptionModel exceptionModel = new BusinessCodeExceptionModel(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                productException.getExceptionCode(),
                productException.getMessage());
        return new ResponseEntity<>(exceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BusinessCodeExceptionModel> handle(MethodArgumentNotValidException exception) {
        String error = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList())
                .get(0);

        BusinessCodeExceptionModel exceptionModel = new BusinessCodeExceptionModel(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "NULL",
                error);
        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

}
