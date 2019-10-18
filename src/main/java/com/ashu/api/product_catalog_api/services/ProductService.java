package com.ashu.api.product_catalog_api.services;

import com.ashu.api.product_catalog_api.dto.ProductDTO;
import com.ashu.api.product_catalog_api.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public interface ProductService {
	
	List<ProductDTO> generate();
	ProductDTO generateOne(Integer id);
	Product add(ProductDTO productDTO, int categoryId);
	void edit(int id, ProductDTO productDTO, int productId);
	void del(int id);
	
	
	

}
