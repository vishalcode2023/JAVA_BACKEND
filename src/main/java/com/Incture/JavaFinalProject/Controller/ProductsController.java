package com.Incture.JavaFinalProject.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Incture.JavaFinalProject.Dto.ProductsResponse;
import com.Incture.JavaFinalProject.Entity.ProductModel;
import com.Incture.JavaFinalProject.Services.ProductsServices;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsServices productsServices;

    @PostMapping("/create")
    public ResponseEntity<ProductsResponse> CreateProducts(@RequestBody ProductsResponse productsResponse) {
        ProductsResponse response = productsServices.CreateProducts(productsResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getall")
    public List<ProductModel> getAllProducts() {
        return productsServices.getAllProducts(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findproductByid(@PathVariable long id) {
        ProductModel productModel = productsServices.findproductByid(id);
        return ResponseEntity.ok(productModel);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductModel> updatetheProduct(@PathVariable long id,
            @RequestBody ProductsResponse productsResponse) {
        ProductModel productModel = productsServices.updatetheProduct(id, productsResponse);
        return ResponseEntity.ok(productModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducts(@PathVariable long id) {
        String productModel = productsServices.deleteProducts(id);
        return ResponseEntity.ok(productModel);
    }
}
