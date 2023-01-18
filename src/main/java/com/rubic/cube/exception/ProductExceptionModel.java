package com.rubic.cube.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ProductExceptionModel {

    private int status;
    private Date timestamp;
    private String errorCode;
    private String errorMessage;

}
