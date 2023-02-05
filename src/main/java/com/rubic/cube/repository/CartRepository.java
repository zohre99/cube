package com.rubic.cube.repository;

import com.rubic.cube.constant.CartStatus;
import com.rubic.cube.entity.Cart;
import com.rubic.cube.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserAndStatus(User user, CartStatus cartStatus);

}
