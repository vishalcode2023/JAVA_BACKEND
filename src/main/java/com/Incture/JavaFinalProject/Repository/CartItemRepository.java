package com.Incture.JavaFinalProject.Repository;

import com.Incture.JavaFinalProject.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {}