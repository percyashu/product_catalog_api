package com.ashu.api.product_catalog_api.controllers;

import com.ashu.api.product_catalog_api.dto.ProductDTO;
import com.ashu.api.product_catalog_api.models.Image;

import com.ashu.api.product_catalog_api.models.Product;
import com.ashu.api.product_catalog_api.payload.UploadFile;

import com.ashu.api.product_catalog_api.repository.ProductRepository;
import com.ashu.api.product_catalog_api.services.ImageService;

import com.ashu.api.product_catalog_api.services.ProductService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

	private ProductService productService;
    @Autowired
    private ImageService service;
    @Autowired
	private ProductRepository repository;
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

    @ApiOperation(value = "Get all products in the system")
	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.generate());
	}

    @ApiOperation(value = "Gets a  particular PathVariable ID product  in the system")
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId) {
		ProductDTO product=productService.generateOne(productId);
		if(product==null)
			return ResponseEntity.notFound().build();
		else
				return ResponseEntity.ok().body(product);
		
	}

	@ResponseBody
    @ApiOperation(value = "Creates a new  product under a particular category ID  in the system")
	@PostMapping("/products/category/{categoryId}")
	       public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer categoryId) {
            Product product = productService.add(productDTO,categoryId);
            HttpHeaders responseHeaders = new
                    HttpHeaders();
            URI newProductUri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{categoryId}")
                    .buildAndExpand(product.getId()).toUri();
            responseHeaders.setLocation(newProductUri);
            return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);

	}
    @ApiOperation(value = "Edit a product in the system")
    @PutMapping("/products/{productId}/category/{categoryId}")
    public ResponseEntity<?> editProduct(@PathVariable Integer productId,@PathVariable Integer categoryId, @RequestBody ProductDTO productDTO) {
        productService.edit(productId, productDTO,categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @ApiOperation(value = "Deletes a product in the system")
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> delProduct(@PathVariable Integer productId){
		productService.del(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
    @ApiOperation(value = "Add  an image under a particular product ID in the system")
    @PostMapping("/products/{productId}/image")
    public ResponseEntity<?> savePerson(@RequestParam("file") MultipartFile file,@PathVariable Integer productId) throws JsonParseException, JsonMappingException,IOException {
        Image  image =service.saveImage(file);
        HttpHeaders responseHeaders = new
                HttpHeaders();
        URI newProductUri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{imageId}")
                .buildAndExpand(image.getId()).toUri();
        responseHeaders.setLocation(newProductUri);
        Product product = repository.findById(productId).get();
        String fileUri = newProductUri.toString();
        product.setImageUrl(fileUri);
        repository.save(product);
        return new ResponseEntity<>(new UploadFile(fileUri),responseHeaders, HttpStatus.CREATED);

    }
    @ApiOperation(value = "Gets image under a particular product ID in the system")
    @GetMapping("/products/{productId}/image/{imageId}")
    public ResponseEntity<?> getPersonImage(@PathVariable Integer  imageId,@PathVariable Integer productId) {
            Image image =service.getRec(imageId,productId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
                .body(new ByteArrayResource(image.getLogo()));

    }
    @ApiOperation(value = "Edit  an image under a particular product ID in the system")
    @PutMapping("/products/{productId}/image/{imageId}")
    public ResponseEntity<?> editImage(@RequestParam("file") MultipartFile file,@PathVariable Integer  imageId,@PathVariable Integer productId){
	    service.edRec(imageId,productId,file);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ApiOperation(value = "Delete  an image under a particular product ID in the system")
    @DeleteMapping("/products/{productId}/image/{imageId}")
    public ResponseEntity<?> delImage(@PathVariable Integer  imageId,@PathVariable Integer productId){
        service.delImage(imageId,productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}

