package com.rubic.cube.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ProductExceptionModel> handleException(ProductException productException) {
        ProductExceptionModel exceptionModel = new ProductExceptionModel(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                productException.getExceptionCode(),
                productException.getMessage());
        return new ResponseEntity<>(exceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
