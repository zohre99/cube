package com.rubic.cube.controller.mapper;

import com.rubic.cube.controller.model.request.CreateUseRequest;
import com.rubic.cube.controller.model.response.UserResponse;
import com.rubic.cube.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", imports = {java.util.Date.class})
public interface UserMapper {

    User createUserModelToUser(CreateUseRequest createUseRequest);

    UserResponse userToUserResponse(User user);

    List<UserResponse> usersToUserResponses(List<User> users);

}
