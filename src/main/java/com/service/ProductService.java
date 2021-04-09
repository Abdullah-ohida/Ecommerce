package com.service;

import com.dto.ProductDto;
import com.model.Product;


import java.util.List;


public interface ProductService {
    void addProduct(ProductDto productDto);
    void removeProduct(String productId);
    void removeAllProducts();
    ProductDto findProductById(String productId);
    List<ProductDto> getAllProducts();
    Product findProduct(String productId);
    ProductDto updateProduct(String productId, ProductDto updatedProductDetails);
    List<ProductDto> getAllProductsInCategory(String category);
}
