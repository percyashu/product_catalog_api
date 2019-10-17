package com.ashu.api.product_catalog_api.controllers;

import com.ashu.api.product_catalog_api.dto.ProductDTO;
import com.ashu.api.product_catalog_api.models.Product;
import com.ashu.api.product_catalog_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.generate());
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId) {
		ProductDTO product=productService.generateOne(productId);
		if(product==null)
			return ResponseEntity.notFound().build();
		else
				return ResponseEntity.ok().body(product);
		
	}

	@ResponseBody
	@PostMapping("/products/category/{categoryId}")
	public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer categoryId,@RequestParam("file") MultipartFile file) {
		 Product product = productService.add(productDTO,categoryId,file);
		 HttpHeaders responseHeaders = new
		 HttpHeaders(); 
		 URI newProductUri = ServletUriComponentsBuilder
				 .fromCurrentRequest().path("/{categoryId}")
				 .buildAndExpand(product.getId()).toUri();
		 responseHeaders.setLocation(newProductUri);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(product.getName())
				.toUriString();
		 return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
		 

	}

	@PutMapping("/products/{productId}/category/{categoryId}")
	public ResponseEntity<?> editProduct(@PathVariable Integer productId,@PathVariable Integer categoryId, @RequestBody ProductDTO productDTO,@RequestParam("file") MultipartFile file) {
		productService.edit(productId, productDTO,categoryId,file);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> delProduct(@PathVariable Integer productId){
		productService.del(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

