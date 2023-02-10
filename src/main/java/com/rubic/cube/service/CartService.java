package com.rubic.cube.service;

import com.rubic.cube.constant.CartStatus;
import com.rubic.cube.controller.model.request.CartSubmitRequest;
import com.rubic.cube.entity.Cart;
import com.rubic.cube.entity.User;
import com.rubic.cube.exception.BusinessCodeException;
import com.rubic.cube.exception.ExceptionMessage;
import com.rubic.cube.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart getUserCurrentCart(User user) {
        Cart existingCart = cartRepository.findByUserAndStatus(user, CartStatus.PROGRESSING)
                .orElse(null);
        if (existingCart != null) return existingCart;
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setStatus(CartStatus.PROGRESSING);
        return cartRepository.save(newCart);
    }

    public void submitCart(Long cartId, CartSubmitRequest submitRequest) {
        Cart cart = findById(cartId);
        if (cart.getStatus() != CartStatus.PROGRESSING) {
            throw new BusinessCodeException(ExceptionMessage.INVALID_CART_STATE, ExceptionMessage.INVALID_CART_STATE_MSG);
        }
        cart.setStatus(CartStatus.SUBMITTED);
        cart.setAddress(submitRequest.getAddress());
        cartRepository.save(cart);
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new BusinessCodeException(ExceptionMessage.CART_NOT_FOUND, ExceptionMessage.CART_NOT_FOUND_MSG));
    }

}
