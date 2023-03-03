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
@Transactional
public class OrderItemService {

    private final OrderItemRepository repository;
    private final CartService cartService;

    @Transactional
    public Long orderProduct(Product product, User user) {
        Cart cart = cartService.getUserCurrentCart(user);
        OrderItem newOrderItem = new OrderItem();

        OrderItem currentOrderItem = repository.findByProductAndCart(product, cart)
                .orElse(null);
        if (currentOrderItem != null) {
            currentOrderItem.setCount(currentOrderItem.getCount() + 1);
            repository.save(currentOrderItem);
            return currentOrderItem.getId();
        }
        newOrderItem.setCount(1);
        newOrderItem.setCart(cart);
        newOrderItem.setProduct(product);
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
    public void reduceOrderCountByOne(Product product, User user) {
        OrderItem orderItem = findByProductAndUser(product, user);
        if (orderItem.getCount() == 1) {
            repository.delete(orderItem);
        } else {
            orderItem.setCount(orderItem.getCount() - 1);
            repository.save(orderItem);
        }
    }

}
