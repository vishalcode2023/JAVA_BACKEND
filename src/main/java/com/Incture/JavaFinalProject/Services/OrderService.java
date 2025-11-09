package com.Incture.JavaFinalProject.Services;

import com.Incture.JavaFinalProject.Entity.*;
import com.Incture.JavaFinalProject.Repository.CartRepository;
import com.Incture.JavaFinalProject.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    // Checkout cart -> create order
    public Order checkout(Long userId) {
        CartModel cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = Order.builder()
                .userId(userId)
                .totalAmount(cart.getItems().stream().mapToDouble(i -> i.getQuantity() * 10).sum()) // replace 10 with product price fetch
                .orderDate(LocalDateTime.now())
                .paymentStatus("PENDING")
                .orderStatus("PLACED")
                .items(cart.getItems().stream().map(i -> OrderItem.builder()
                        .productId(i.getProductId())
                        .quantity(i.getQuantity())
                        .price(10.0) // replace with actual price
                        .build())
                        .collect(Collectors.toList()))
                .build();

        order.getItems().forEach(item -> item.setOrder(order));
        Order savedOrder = orderRepository.save(order);

        // Clear cart after checkout
        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    public java.util.List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
}
