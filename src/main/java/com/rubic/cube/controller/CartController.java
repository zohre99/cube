package com.rubic.cube.controller;

import com.rubic.cube.controller.mapper.CartMapper;
import com.rubic.cube.controller.model.request.CartSubmitRequest;
import com.rubic.cube.controller.model.response.CartResponse;
import com.rubic.cube.entity.Cart;
import com.rubic.cube.entity.User;
import com.rubic.cube.exception.ExceptionMessage;
import com.rubic.cube.service.CartService;
import com.rubic.cube.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CartController.CART_ADDRESS)
public class CartController {
    public static final String CART_ADDRESS = "/cart";
    private final CartService cartService;
    private final UserService userService;
    private final CartMapper cartMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "returns user's current cart information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USER_NOT_FOUND_MSG)
    })
    public CartResponse getUserCurrentCart(@RequestParam Long userId) {
        User user = userService.findById(userId);
        Cart cart = cartService.getUserCurrentCart(user);
        return cartMapper.cartToCartResponse(cart);
    }

    @PatchMapping("/{cartId}/submit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "PATCH", description = "finalize user's order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Submitted"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.CART_NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.INVALID_CART_STATE_MSG)
    })
    public void submitCart(@PathVariable("cartId") Long cartId,
                           @RequestBody @Validated CartSubmitRequest cartSubmitRequest) {
        cartService.submitCart(cartId, cartSubmitRequest);
    }

}
