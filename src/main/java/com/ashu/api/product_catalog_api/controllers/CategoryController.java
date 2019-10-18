package com.ashu.api.product_catalog_api.controllers;

import com.ashu.api.product_catalog_api.dto.CategoryDTO;
import com.ashu.api.product_catalog_api.models.Category;
import com.ashu.api.product_catalog_api.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	@ResponseBody
	@PostMapping("/category")
	@ApiOperation(value = "Add a new Category in the system")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) {
		Category category = categoryService.add(categoryDTO);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{categoryId}")
				.buildAndExpand(category.getId()).toUri();
		responseHeaders.setLocation(newCategoryUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	@ApiOperation(value = "Gets all Categories in the system")
	@GetMapping("/category")
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		return ResponseEntity.ok(categoryService.generate());
	}

	@ApiOperation(value = "Gets a particular Category in the system")
	@GetMapping("category/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory( @PathVariable int categoryId) {
		CategoryDTO category=categoryService.generateOne(categoryId);
		if(category==null)
			return ResponseEntity.notFound().build();
		else
				return ResponseEntity.ok().body(category);
		
	}

	@ApiOperation(value = "Edits a particular Category in the system")
	@PutMapping("/category/{categoryId}")
	public ResponseEntity<?> editCategory(@PathVariable Integer categoryId, @RequestBody CategoryDTO categoryDTO) {
		categoryService.edit(categoryId, categoryDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	@ApiOperation(value = "Deletes a particular Category in the system")
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<?> delCategory(@PathVariable int categoryId ){
		categoryService.del(categoryId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
