package com.rubic.cube.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "CreateProductRequest", description = "Product information")
public class CreateProductRequest {

    @Schema(description = "Product code", example = "SWT-10A")
    @NotBlank
    private String code;

    @Schema(description = "Product name", example = "Sweater")
    @NotBlank
    private String name;

    @Schema(description = "Product color", example = "Blue")
    @NotBlank
    private String color;

    @Schema(description = "Product description", example = "Cozy!")
    private String description;

    @Schema(description = "Product stock", example = "100")
    @NotNull
    private Integer stock;

}
