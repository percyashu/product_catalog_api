package com.ashu.api.product_catalog_api.repository;

import com.ashu.api.product_catalog_api.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
