package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.Cart;
import org.example.evaluations.evaluation.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/{cartId}")
    public Cart deleteCartById(@PathVariable Long cartId) {
        return cartService.deleteCartById(cartId);
    }

    @PostMapping
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @PutMapping("/{cartId}")
    public Cart updateCart(@RequestBody Cart cart,@PathVariable Long cartId) {
        return cartService.updateCart(cartId,cart);
    }
}
