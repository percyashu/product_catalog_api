package com.ashu.api.product_catalog_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
