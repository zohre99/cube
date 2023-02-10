package com.rubic.cube.controller.mapper;

import com.rubic.cube.controller.model.response.CartResponse;
import com.rubic.cube.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "userId", source = "cart.user.id")
    CartResponse cartToCartResponse(Cart cart);

}
