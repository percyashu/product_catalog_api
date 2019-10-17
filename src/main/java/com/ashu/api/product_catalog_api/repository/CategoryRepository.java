package com.ashu.api.product_catalog_api.repository;

import com.ashu.api.product_catalog_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
