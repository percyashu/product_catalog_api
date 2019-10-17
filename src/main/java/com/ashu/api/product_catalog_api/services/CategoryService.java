package com.ashu.api.product_catalog_api.services;


import com.ashu.api.product_catalog_api.dto.CategoryDTO;
import com.ashu.api.product_catalog_api.models.Category;

import java.util.List;


public interface CategoryService {
	List<CategoryDTO> generate();
	CategoryDTO generateOne(Integer id);
	Category add(CategoryDTO categoryDTO);
	void edit(int id, CategoryDTO categoryDTO);
	void del(int id);

}
