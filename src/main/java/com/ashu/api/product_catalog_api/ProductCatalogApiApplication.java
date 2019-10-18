package com.ashu.api.product_catalog_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories("com.ashu.api.product_catalog_api.repository")
public class ProductCatalogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApiApplication.class, args);
	}

}
