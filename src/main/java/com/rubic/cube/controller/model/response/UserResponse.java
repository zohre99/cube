package com.rubic.cube.controller.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@ApiModel(value = "UserResponseModel", description = "User information")
public class UserResponse {

    @Schema(description = "User id", example = "1")
    private Long id;

    @Schema(description = "User unique name", example = "j.doe")
    private String username;

    @Schema(description = "User first name", example = "John")
    private String firstName;

    @Schema(description = "User last name", example = "Doe")
    private String lastName;

//    @Schema(description = "Created date.", example = "2022-11-09T15:50:46.852Z")
//    private Date createdDate;
//
//    @Schema(description = "lastModifiedDate", example = "2022-11-09T15:50:46.852Z")
//    private Date lastModifiedDate;

}
