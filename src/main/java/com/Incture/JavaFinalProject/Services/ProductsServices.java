package com.Incture.JavaFinalProject.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Incture.JavaFinalProject.Dto.ProductsResponse;

import com.Incture.JavaFinalProject.Entity.ProductModel;
import com.Incture.JavaFinalProject.Exception.CustomException;
import com.Incture.JavaFinalProject.Repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsServices {

    private final ProductsRepository productsRepository;

    public ProductsResponse CreateProducts(ProductsResponse productsResponse) {
        ProductModel productModel = ProductModel.builder()
                .name(productsResponse.getName())
                .description(productsResponse.getDescription())
                .price(productsResponse.getPrice())
                .stock(productsResponse.getStock())
                .category(productsResponse.getCategory())
                .image_url(productsResponse.getImage_url())
                .rating(productsResponse.getRating())
                .build();

        productsRepository.save(productModel);

        return ProductsResponse.builder()
                .id(productsResponse.getId())
                .name(productsResponse.getName())
                .description(productsResponse.getDescription())
                .stock(productsResponse.getStock())
                .category(productsResponse.getCategory())
                .price(productsResponse.getPrice())
                .image_url(productsResponse.getImage_url())
                .rating(productsResponse.getRating())
                .build();
    }

    public List<ProductModel> getAllProducts() {
        return productsRepository.findAll();
    }

    public ProductModel findproductByid(long id) {
        ProductModel productModel = productsRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product Not Found"));
        return productModel;
    }

    public ProductModel updatetheProduct(long id, ProductsResponse productsResponse) {
        ProductModel productModel = productsRepository.findById(id)
                .orElseThrow(() -> new CustomException("Products not Found"));

        productModel.setName(productsResponse.getName());
        productModel.setDescription(productsResponse.getDescription());
        productModel.setPrice(productsResponse.getPrice());
        productModel.setStock(productsResponse.getStock());
        productModel.setCategory(productsResponse.getCategory());
        productModel.setImage_url(productsResponse.getImage_url());
        productModel.setRating(productsResponse.getRating());

        return productsRepository.save(productModel);
    }

    public String deleteProducts(long id) {
        if (productsRepository.findById(id).isEmpty()) {
            throw new CustomException("Product not Found");
        }
        productsRepository.deleteById(id);
        return "Deleted the Product successfully";
    }

}
