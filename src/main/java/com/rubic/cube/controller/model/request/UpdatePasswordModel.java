package com.rubic.cube.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "UpdatePasswordModel", description = "It specifies current and new password.")
public class UpdatePasswordModel {

    @Schema(description = "current password", example = "0a92d#b134")
    @NotBlank(message = "current password cannot be empty")
    private String currentPassword;

    @Schema(description = "new password; containing at least 8 characters", example = "0a92d#b134")
    @NotBlank(message = "new password cannot be empty")
    @Size(min = 8, max = 50)
    private String newPassword;

}
