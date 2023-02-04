package com.rubic.cube.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "CreateUserModel", description = "It defines user information.")
public class CreateUserModel {

    @Schema(description = "username", example = "j.doe")
    @NotBlank(message = "username cannot be empty")
    private String username;

    @Schema(description = "password; containing at least 8 characters", example = "0a92d#b134")
    @NotBlank(message = "password cannot be empty")
    @Size(min = 8, max = 50)
    private String password;

    @Schema(description = "first name", example = "John")
    @NotBlank(message = "first name cannot be empty")
    private String firstName;

    @Schema(description = "last name", example = "Doe")
    @NotBlank(message = "last name cannot be empty")
    private String lastName;

}
