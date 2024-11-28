package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.models.Cart;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable Long cartId) {
         if(cartId<=0) {
             throw new IllegalArgumentException("Incorrect cart Id passed");
         }
         return new Cart(cartId);
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart req) {
        Cart cart = new Cart();
        cart.setFriendlyName(req.getFriendlyName());
        cart.setTotalValue(req.getTotalValue());
        cart.setId(req.getId());
        User user = new User();
        if(req.getUser() == null) {
            throw new NullPointerException("No User associated with Cart");
        }
        user.setName(req.getUser().getName());
        user.setId(req.getUser().getId());

        List<String> items= new ArrayList<>();
        for(int s=0;s<=req.getUniqueItems().size();s++) {
            items.add(req.getUniqueItems().get(s));
        }
        cart.setUniqueItems(items);

        return cart;
    }

    @GetMapping("/{userId}/{cartId}")
    public Cart getCartByUserIdAndCartId(@PathVariable String userId,@PathVariable Long cartId) {
        Long UserIdInLong = Long.parseLong(userId);
        return new Cart(cartId, UserIdInLong);
    }
}
