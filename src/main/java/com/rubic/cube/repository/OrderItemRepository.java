package com.rubic.cube.repository;

import com.rubic.cube.entity.Cart;
import com.rubic.cube.entity.OrderItem;
import com.rubic.cube.entity.Product;
import com.rubic.cube.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByProductAndCart(Product product, Cart cart);

    @Query("SELECT o FROM OrderItem o " +
            "WHERE o.product = :product " +
            "And o.cart.user = :user " +
            "And o.cart.status = com.rubic.cube.constant.CartStatus.PROGRESSING")
    Optional<OrderItem> findActiveItemByProductAndUser(Product product, User user);

}
