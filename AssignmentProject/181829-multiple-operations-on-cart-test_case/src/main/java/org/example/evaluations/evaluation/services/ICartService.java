package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Cart;

import java.util.List;

public interface ICartService {
    Cart getCartById(Long cartId);

    List<Cart> getCartByUserId(Long userId);

    Cart deleteCartById(Long cartId);

    Cart updateCart(Long cartId, Cart request);

    Cart addCart(Cart request);
}
