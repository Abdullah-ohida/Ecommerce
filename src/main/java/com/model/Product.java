package com.model;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    private String id;
    private String name;
    private String Description;
    private BigDecimal price;
    private String image;
    private String category;
}
