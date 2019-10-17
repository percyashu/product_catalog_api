package com.ashu.api.product_catalog_api.repository;


import com.ashu.api.product_catalog_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByIdAndCategoryId(int Id, int categoryId);
}
