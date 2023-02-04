package com.rubic.cube.exception;

public class BusinessCodeException extends RuntimeException {
    private String exceptionCode;

    public BusinessCodeException(String exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
