package com.rubic.cube.service;

import com.rubic.cube.constant.CartStatus;
import com.rubic.cube.entity.Cart;
import com.rubic.cube.entity.User;
import com.rubic.cube.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;


    public Cart getUserCurrentCart(User user) {
        Cart existingCart = cartRepository.findByUserAndStatus(user, CartStatus.IN_PROGRESS)
                .orElse(null);
        if (existingCart != null) return existingCart;
        Cart newCart = new Cart();
        newCart.setUser(user);
        return cartRepository.save(newCart);
    }

}
