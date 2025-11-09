package com.Incture.JavaFinalProject.Services;

import com.Incture.JavaFinalProject.Entity.CartItem;
import com.Incture.JavaFinalProject.Entity.CartModel;
import com.Incture.JavaFinalProject.Repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    // Add product to cart
    public CartModel addToCart(Long userId, Long productId, Integer quantity) {
        CartModel cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(CartModel.builder().userId(userId).totalPrice(0.0).build()));

        CartItem item = CartItem.builder()
                .cart(cart)
                .productId(productId)
                .quantity(quantity)
                .build();
        cart.getItems().add(item);
        cartRepository.save(cart);
        return cart;
    }

    // Update product quantity
    public CartModel updateCartItem(Long userId, Long productId, Integer quantity) {
        CartModel cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().forEach(item -> {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
            }
        });
        return cartRepository.save(cart);
    }

    // Remove product from cart
    public CartModel removeCartItem(Long userId, Long productId) {
        CartModel cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        return cartRepository.save(cart);
    }

    // Get cart by user
    public CartModel getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> CartModel.builder().userId(userId).totalPrice(0.0).build());
    }
}
