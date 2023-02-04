package com.rubic.cube.controller.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "ProductStockByColorResponse", description = "Product information for each color")
public class ProductStockByColorResponse {

    @Schema(description = "Product color", example = "blue")
    private String color;

    @Schema(description = "Product stock for a specific color", example = "10")
    private Long stock;
}
