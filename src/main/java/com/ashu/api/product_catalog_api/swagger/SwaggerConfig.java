package com.ashu.api.product_catalog_api.swagger;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Arrays;
import java.util.HashSet;

@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		HashSet<String> consumesAndProduces = new HashSet<String>(Arrays.asList("application/json", "application/xml"));
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metadata())
				.consumes(consumesAndProduces)
				.produces(consumesAndProduces)
				.pathMapping("/");
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("Product catalog API")
				.description("This project is aimed at managing a collection of products in a shop.\r\n" + 
						"This shop arranges products under different categories. Each product can belong to one and\r\n" + 
						"only one category and every category has a group of products. ")
				.version("1.0")
				.contact(new Contact("Percy", "https://github.com/percyashu/product_catalog_api",
						"https://github.com/percyashu/product_catalog_api"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.build();
	}	

}
