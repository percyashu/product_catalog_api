package com.ashu.api.product_catalog_api.services;

import com.ashu.api.product_catalog_api.dto.ProductDTO;
import com.ashu.api.product_catalog_api.exception.FileStorageException;
import com.ashu.api.product_catalog_api.exception.MyFileNotFoundException;
import com.ashu.api.product_catalog_api.models.Product;
import com.ashu.api.product_catalog_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class DBFileStorageService {
    @Autowired
    private ProductRepository repository;

    public byte[] storeFile(MultipartFile file){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            return file.getBytes();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public byte[] getFile(int fileId) {
        Product productDTO= repository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
        return productDTO.getImage();
    }
    }


