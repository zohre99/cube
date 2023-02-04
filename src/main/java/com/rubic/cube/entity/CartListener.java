package com.rubic.cube.entity;

import org.springframework.stereotype.Component;

import javax.persistence.PostUpdate;

@Component
public class CartListener {

    @PostUpdate
    public void prePersist(Cart cart) {
        //TODO: add cart state logic; if the cart is final update the cache
    }

}
