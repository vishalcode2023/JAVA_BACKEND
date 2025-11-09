package com.Incture.JavaFinalProject.Controller;

import com.Incture.JavaFinalProject.Entity.Order;
import com.Incture.JavaFinalProject.Services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public Order checkout(@RequestParam Long userId) {
        return orderService.checkout(userId);
    }

    @GetMapping
    public List<Order> getOrders(@RequestParam Long userId) {
        return orderService.getOrders(userId);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateOrderStatus(id, status);
    }
}
