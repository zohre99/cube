package com.rubic.cube.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@ApiModel(value = "UpdateUserModel", description = "It contains user first name and last name.")
public class UpdateUserModel {

    @Schema(description = "first name", example = "John")
    private String firstName;

    @Schema(description = "last name", example = "Doe")
    private String lastName;

}