package com.rubic.cube.controller.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "IdModelResponse", description = "Entity id")
public class IdModelResponse {

    @Schema(description = "The ID of an entity", example = "1")
    private Long id;
}
