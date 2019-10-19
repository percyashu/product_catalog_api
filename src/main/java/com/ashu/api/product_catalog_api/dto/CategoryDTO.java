package com.ashu.api.product_catalog_api.dto;


import com.ashu.api.product_catalog_api.models.Category;

public class CategoryDTO extends Category {

	public CategoryDTO(int id, String name,String description) {
		super(id, name,description);
		
	}

	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}

}
