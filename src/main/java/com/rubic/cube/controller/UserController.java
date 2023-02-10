package com.rubic.cube.controller;

import com.rubic.cube.controller.mapper.UserMapper;
import com.rubic.cube.controller.model.request.CreateUseRequest;
import com.rubic.cube.controller.model.request.UpdatePasswordRequest;
import com.rubic.cube.controller.model.request.UpdateUserRequest;
import com.rubic.cube.controller.model.response.IdModelResponse;
import com.rubic.cube.controller.model.response.UserResponse;
import com.rubic.cube.entity.User;
import com.rubic.cube.exception.ExceptionMessage;
import com.rubic.cube.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(UserController.USER_CONTROLLER_ADDRESS)
public class UserController {
    public static final String USER_CONTROLLER_ADDRESS = "/users";

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create", description = "It creates a new user with given info.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USERNAME_IS_ALREADY_EXISTS_MSG)
    })
    public IdModelResponse create(@Parameter(description = "User information.")
                                  @RequestBody @Valid CreateUseRequest createUseRequest) {
        User user = userMapper.createUserModelToUser(createUseRequest);
        Long createdUserId = userService.create(user);
        return new IdModelResponse(createdUserId);
    }

    @DeleteMapping("/delete-by-username/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete", description = "It deletes a user by it's username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USER_NOT_FOUND_MSG)
    })
    public void deleteByUsername(@Parameter(description = "specify username.")
                                 @PathVariable("username") String username) {
        userService.deleteByUsername(username);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete", description = "It deletes a user by it's user id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USER_NOT_FOUND_MSG)
    })
    public void deleteByID(@Parameter(description = "specify user id.")
                           @PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update", description = "It updates user first name and last name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USER_NOT_FOUND_MSG)
    })
    public IdModelResponse update(@Parameter(description = "specify username.")
                                  @PathVariable("username") String username,
                                  @Parameter(description = "specify user update model; containing user first name and user last name.")
                                  @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        Long id = userService.updateByUsername(username, updateUserRequest);
        return new IdModelResponse(id);
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "It finds user information by it's username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USER_NOT_FOUND_MSG)
    })
    public UserResponse findByUsername(@Parameter(description = "specify username.")
                                            @PathVariable String username) {
        User user = userService.findByUsername(username);
        return userMapper.userToUserResponse(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "It fetches users information with pagination.")
    public List<UserResponse> findAll(@Parameter(description = "specify which page do you want to fetch.")
                                           @RequestParam("page") int page,
                                           @Parameter(description = "specify the size of each page.")
                                           @RequestParam("limit") int limit) {
        List<User> users = userService.findAll(page, limit);
        return userMapper.usersToUserResponses(users);
    }

    @PatchMapping("/{username}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Patch", description = "It changes user's password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USER_NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.INCORRECT_PASSWORD_MSG)
    })
    public void updatePassword(
            @Parameter(description = "specify username.")
            @PathVariable("username") String username,
            @Parameter(description = "specify password information.")
            @RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        userService.updatePassword(username, updatePasswordRequest);
    }

}
