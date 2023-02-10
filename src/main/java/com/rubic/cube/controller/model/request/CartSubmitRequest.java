package com.rubic.cube.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "CartSubmitRequest", description = "Cart details")
public class CartSubmitRequest {

    @Schema(description = "specifies the address to which orders are to be shipped")
    @NotBlank(message = "address cannot be null")
    private String address;

}
