package com.Incture.JavaFinalProject.Controller;


import com.Incture.JavaFinalProject.Entity.CartModel;
import com.Incture.JavaFinalProject.Services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add/{productId}")
    public CartModel addToCart(@RequestParam Long userId, @PathVariable Long productId, @RequestParam Integer quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    @PutMapping("/update/{productId}")
    public CartModel updateCart(@RequestParam Long userId, @PathVariable Long productId, @RequestParam Integer quantity) {
        return cartService.updateCartItem(userId, productId, quantity);
    }

    @DeleteMapping("/remove/{productId}")
    public CartModel removeCart(@RequestParam Long userId, @PathVariable Long productId) {
        return cartService.removeCartItem(userId, productId);
    }

    @GetMapping
    public CartModel getCart(@RequestParam Long userId) {
        return cartService.getCart(userId);
    }
}
