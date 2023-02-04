package com.rubic.cube.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "CreateProductRequest", description = "Product information")
public class UpdateProductRequest {

    @Schema(description = "Product id", example = "1")
    @NotNull
    private String id;

    @Schema(description = "Product name", example = "Sweater")
    private String name;

    @Schema(description = "Product color", example = "Blue")
    private String color;

    @Schema(description = "Product description", example = "Cozy!")
    private String description;

    @Schema(description = "Product stock", example = "100")
    private String stock;

}
