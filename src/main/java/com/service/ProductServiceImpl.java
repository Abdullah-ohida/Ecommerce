package com.service;

import com.dto.ProductDto;
import com.exception.ProductException;
import com.model.Product;
import com.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void addProduct(ProductDto productDto) {
        Product productToSave = ProductDto.unpackDto(productDto);
        addNewProduct(productToSave);
    }

    private void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void removeProduct(String productId) {
        Product productToRemove = findProd(productId);
        removeProductFromDb(productToRemove);
    }

    @Override
    public void removeAllProducts() {
        removeAllProductFromDb();
    }

    private void removeAllProductFromDb() {
        productRepository.deleteAll();
    }

    private Product findProd(String id){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            return productOptional.get();
        }
        throw new ProductException("");
    }

    private void removeProductFromDb(Product product) {
        productRepository.delete(product);
    }


    @Override
    public ProductDto findProductById(String productId) {
        return ProductDto.packDto(getProductById(productId));
    }

    @NotNull
    private Product getProductById(String productId) {
        Optional<Product> products = productRepository.findById(productId);
        if (products.isPresent()) {
            return products.get();
        } else {
            throw new ProductException("No product found with that id");
        }
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDto = new ArrayList<>();
        for (Product product : getAllProductFromDb()) {
            productDto.add(ProductDto.packDto(product));
        }
        return productDto;
    }

    @Override
    public Product findProduct(String productId) {
       return ProductDto.unpackDto(findProductById(productId));
    }

    private List<Product> getAllProductFromDb() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto updatedProductDetails) {
        Product productToUpdate = findProduct(productId);
        if (!productToUpdate.getDescription().equals(updatedProductDetails.getDescription())) {
            productToUpdate.setDescription(updatedProductDetails.getDescription());
        }
        if (!productToUpdate.getPrice().equals(updatedProductDetails.getPrice())) {
            productToUpdate.setPrice(updatedProductDetails.getPrice());
        }
        if (!productToUpdate.getImage().equals(updatedProductDetails.getImage())) {
            productToUpdate.setImage(updatedProductDetails.getImage());
        }
        if (!productToUpdate.getName().equals(updatedProductDetails.getName())) {
            productToUpdate.setName(updatedProductDetails.getName());
        }
        if (!productToUpdate.getCategory().equals(updatedProductDetails.getCategory()))
            productToUpdate.setCategory(updatedProductDetails.getCategory());
        return ProductDto.packDto(saveProduct(productToUpdate));
    }

    @Override
    public List<ProductDto> getAllProductsInCategory(String category) {
        List<ProductDto> productDto = new ArrayList<>();
        for (Product product : getProductInCategory(category)) {
            productDto.add(ProductDto.packDto(product));
        }
        return productDto;
    }

    private List<Product> getProductInCategory(String category) {
        if (productRepository.findProductByCategory(category) == null)
            throw new ProductException(String.format("%s %s %s", "Product with this", category, "is not found"));
        else
            return productRepository.findProductByCategory(category);
    }


    private Product saveProduct(Product product) {
        return productRepository.save(product);
    }

}
