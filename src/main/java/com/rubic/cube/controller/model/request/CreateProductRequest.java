package com.rubic.cube.controller.model.request;

import lombok.Data;

@Data
public class CreateProductRequest {
    private String code;
    private String name;
    private String color;
    private String description;
    private String stock;
}
