package com.rubic.cube.controller.model.request;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private String id;
    private String name;
    private String color;
    private String description;
    private String stock;
}
