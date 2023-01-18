package com.rubic.cube.controller.model.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private String color;
    private String description;
    private String stock;
}
