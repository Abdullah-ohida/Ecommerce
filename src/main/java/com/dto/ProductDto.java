package com.dto;

import com.model.Product;
import com.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String description;
    private String name;
    private BigDecimal price;
    private String image;
    private String category;


    public static Product unpackDto(ProductDto productDto){
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        return product;
    }



    public static ProductDto packDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        return productDto;
    }
}
