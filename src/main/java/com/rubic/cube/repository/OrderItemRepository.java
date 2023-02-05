package com.rubic.cube.repository;

import com.rubic.cube.entity.Cart;
import com.rubic.cube.entity.OrderItem;
import com.rubic.cube.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByProductAndCart(Product product, Cart cart);

}
