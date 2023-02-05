package com.rubic.cube.service;

import com.rubic.cube.entity.Cart;
import com.rubic.cube.entity.OrderItem;
import com.rubic.cube.entity.Product;
import com.rubic.cube.entity.User;
import com.rubic.cube.exception.BusinessCodeException;
import com.rubic.cube.exception.ExceptionMessage;
import com.rubic.cube.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    @Transactional
    public Long order(Long productId, Long userId) {
        User user = userService.findById(userId);
        Cart cart = cartService.getUserCurrentCart(user);
        Product product = productService.findById(productId);

        if (product.getStock() < 1) {
            throw new BusinessCodeException(ExceptionMessage.SOLED_OUT, ExceptionMessage.SOLED_OUT_MSG);
        }

        OrderItem currentOrderItem = orderItemRepository.findByProductAndCart(product, cart)
                .orElse(null);
        if (currentOrderItem != null) {
            currentOrderItem.setCount(currentOrderItem.getCount() + 1);
            productService.reduceStockByOne(product);
            return orderItemRepository.save(currentOrderItem).getId();
        }

        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setCart(cart);
        newOrderItem.setProduct(product);
        productService.reduceStockByOne(product);
        return orderItemRepository.save(newOrderItem).getId();
    }

    @Transactional
    public void reduceOrderCountByOne(Long id) {
        OrderItem orderItem = findById(id);
        if (orderItem.getCount() == 1) {
            orderItemRepository.delete(orderItem);
        } else {
            orderItem.setCount(orderItem.getCount() - 1);
            orderItemRepository.save(orderItem);
        }
        productService.increaseStockByOne(orderItem.getProduct());
    }

    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new BusinessCodeException(ExceptionMessage.ORDER_ITEM_NOT_FOUND,
                        ExceptionMessage.ORDER_ITEM_NOT_FOUND_MSG));
    }


}
