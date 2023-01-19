package com.rubic.cube.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductStockByColorResponse {
    private String color;
    private Long stock;
}
