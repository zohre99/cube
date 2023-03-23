package com.rubic.cube.controller.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@ApiModel(value = "ProductResponse", description = "Product information")
public class ProductResponse {

    @Schema(description = "Product id", example = "1")
    private Long id;

    @Schema(description = "Product code", example = "SWT-10A")
    private String code;

    @Schema(description = "Product name", example = "Sweater")
    private String name;

    @Schema(description = "Product color", example = "Blue")
    private String color;

    @Schema(description = "Product description", example = "Cozy!")
    private String description;

    @Schema(description = "Product stock", example = "100")
    private Integer availableCount;
}
