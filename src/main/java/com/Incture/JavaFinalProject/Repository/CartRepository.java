package com.Incture.JavaFinalProject.Repository;

import com.Incture.JavaFinalProject.Entity.CartModel;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartModel, Long> {
    Optional<CartModel> findByUserId(Long userId);
}