package com.rubic.cube.controller.model.response;

import com.rubic.cube.constant.CartStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@ApiModel(value = "CartResponse", description = "Cart information")
public class CartResponse {
    @Schema(description = "Cart id", example = "1")
    private Long id;

    @Schema(description = "Owner user id", example = "1")
    private Long userId;

    @Schema(description = "Owner user address", example = "Earth, somewhere nice")
    private String address;

    @Schema(description = "Cart status", example = "IN_PROGRESS")
    private CartStatus status;

}
