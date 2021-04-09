package com.controller;

import com.dto.ProductDto;
import com.model.ApiResponse;
import com.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products/new")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto){
        log.info("Product DTO : {}", productDto);
        productService.addProduct(productDto);
        return new ResponseEntity<>(new ApiResponse(true, "Product created successfully"), HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable String productId){
        log.info("productId: {}", productId);
        productService.removeProduct(productId);
        return new ResponseEntity<>(new ApiResponse(true, "product removed successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/products")
    public ResponseEntity<?> deleteAllProduct(){
        productService.removeAllProducts();
        return new ResponseEntity<>(new ApiResponse(true, "All product removed successfully"), HttpStatus.OK);
    }

    @PatchMapping("/products/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto updatedProductDetails, @PathVariable String productId){
        log.info("productId: {}", productId);
        productService.updateProduct(productId, updatedProductDetails);
        return new ResponseEntity<>(new ApiResponse(true, "product updated successfully"), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        log.info("Products: {}", productService.getAllProducts());
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId){
        log.info("productId: {}", productId);
        ProductDto productDto = productService.findProductById(productId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/products/category/{productCategory}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String productCategory) {
        log.info("productId: {}", productCategory);
        List<ProductDto> products = productService.getAllProductsInCategory(productCategory);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
