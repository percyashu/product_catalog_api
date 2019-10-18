package com.ashu.api.product_catalog_api.services;

import com.ashu.api.product_catalog_api.exception.ProductNotFoundException;
import com.ashu.api.product_catalog_api.models.Image;
import com.ashu.api.product_catalog_api.models.Product;
import com.ashu.api.product_catalog_api.repository.ImageRepository;
import com.ashu.api.product_catalog_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
    @Autowired
    ImageRepository repository;
    @Autowired
    ProductRepository productRepository;

    public Image saveImage(MultipartFile file){

         Image image =new Image();
         image.setFilename(file.getOriginalFilename());
         image.setFileType(file.getContentType());
        try {
            image.setLogo(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        repository.save(image);
        return image;
    }
    public Image getRec(int id, int prodId) {
        Boolean bool = productRepository.existsById(prodId);
        if (bool != false) {
            return repository.findById(id).get();
        } else {
            throw new ProductNotFoundException("ProductId - " + id + "  doesn't exist");
        }
    }


    public void edRec(Integer imageId, Integer productId, MultipartFile file) {
        Boolean bool = productRepository.existsById(productId);
        if (bool != false) {
            Image image =repository.findById(imageId).get();
            image.setFilename(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            try {
                image.setLogo(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            repository.save(image);
            return ;
        } else {
            throw new ProductNotFoundException("ProductId - " + productId + "  doesn't exist");
        }
    }

    public void delImage(Integer imageId, Integer productId) {
        Boolean bool = repository.existsById(productId);
        if (bool != false) {
            repository.deleteById(imageId);
            Product product= productRepository.findById(productId).get();
            product.setImageUrl(null);
            productRepository.save(product);
            return ;
        } else {
            throw new ProductNotFoundException("ProductImageId - " + imageId + "  doesn't exist");
        }
    }
}
