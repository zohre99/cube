package com.rubic.cube.controller;

import com.rubic.cube.controller.model.response.IdModelResponse;
import com.rubic.cube.exception.ExceptionMessage;
import com.rubic.cube.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OrderItemController.ORDER_ADDRESS)
@RequiredArgsConstructor
public class OrderItemController {
    public static final String ORDER_ADDRESS = "/orders";

    private final OrderItemService orderItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create", description = "Order a product for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USER_NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.PRODUCT_NOT_FOUND)
    })
    public IdModelResponse order(@RequestParam Long productId,
                                 @RequestParam Long userId) {
        Long id = orderItemService.order(productId, userId);
        return new IdModelResponse(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Patch", description = "Reduce order count")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Edited"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.ORDER_ITEM_NOT_FOUND_MSG)
    })
    public void reduceOrderItemByOne(@PathVariable("id") Long id) {
        orderItemService.reduceOrderCountByOne(id);
    }

    //TODO: implement add or reduce by more than one api's

}
