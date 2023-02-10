package com.rubic.cube.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OrderItemController.ORDER_ADDRESS)
@RequiredArgsConstructor
public class OrderItemController {
    public static final String ORDER_ADDRESS = "/orders";

}
