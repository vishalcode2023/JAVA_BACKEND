
package com.Incture.JavaFinalProject.Repository;

import com.Incture.JavaFinalProject.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}