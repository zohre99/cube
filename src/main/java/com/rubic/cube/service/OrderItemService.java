package com.rubic.cube.service;

import com.rubic.cube.entity.*;
import com.rubic.cube.exception.BusinessCodeException;
import com.rubic.cube.exception.ExceptionMessage;
import com.rubic.cube.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderItemService {

    private final OrderItemRepository repository;
    private final CartService cartService;

    @Transactional
    public Long order(Stock stock, User user) {
        Cart cart = cartService.findUserCurrentCart(user);
        OrderItem newOrderItem = new OrderItem();

        OrderItem currentOrderItem = repository.findByProductAndCart(stock.getProduct(), cart)
                .orElse(null);
        if (currentOrderItem != null) {
            currentOrderItem.setCount(currentOrderItem.getCount() + 1);
            repository.save(currentOrderItem);
            return currentOrderItem.getId();
        }
        newOrderItem.setCount(1);
        newOrderItem.setCart(cart);
        newOrderItem.setProduct(stock.getProduct());
        return repository.save(newOrderItem).getId();
    }

    public OrderItem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessCodeException(ExceptionMessage.ORDER_ITEM_NOT_FOUND,
                        ExceptionMessage.ORDER_ITEM_NOT_FOUND_MSG));
    }

    public OrderItem findByProductAndUser(Product product, User user) {
        return repository.findActiveItemByProductAndUser(product, user)
                .orElseThrow(() -> new BusinessCodeException(ExceptionMessage.ORDER_ITEM_NOT_FOUND,
                        ExceptionMessage.ORDER_ITEM_NOT_FOUND_MSG));
    }

    @Transactional
    public void reduceOrderCountByOne(OrderItem orderItem) {
        if (orderItem.getCount() == 1) {
            repository.delete(orderItem);
        } else {
            orderItem.setCount(orderItem.getCount() - 1);
            repository.save(orderItem);
        }
    }

}
