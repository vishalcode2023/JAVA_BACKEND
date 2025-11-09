package com.Incture.JavaFinalProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Incture.JavaFinalProject.Entity.ProductModel;
import java.util.Optional;


public interface ProductsRepository extends JpaRepository<ProductModel, Long> {
    Optional <ProductModel> findById(long id);
}
