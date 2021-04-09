package com;

import com.repository.ProductRepository;
import com.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

public class TodoAppApplication {
public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

}
