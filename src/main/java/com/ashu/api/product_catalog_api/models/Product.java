package com.ashu.api.product_catalog_api.models;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "product_tbl")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    private String name;
    private Integer quantity;
    private Double price;
    @Lob
    private byte[] image;
    private String EnImage;
    @ManyToOne

    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;

    }

    public String getEnImage() {
        return EnImage;
    }

    public void setEnImage(String enImage) {
        EnImage = enImage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", image=" + Arrays.toString(image) +
                ", EnImage='" + EnImage + '\'' +
                ", category=" + category +
                '}';
    }
}
