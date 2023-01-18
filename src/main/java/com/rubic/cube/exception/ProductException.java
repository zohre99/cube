package com.rubic.cube.exception;

public class ProductException extends RuntimeException {

    private String exceptionCode;

    public ProductException(String exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ProductException() {

    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
