package com.rubic.cube.controller.mapper;

import com.rubic.cube.controller.model.request.CreateUseRequest;
import com.rubic.cube.controller.model.response.UserResponse;
import com.rubic.cube.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", imports = {java.util.Date.class})
public interface UserMapper {

    User toUser(CreateUseRequest createUseRequest);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

}
