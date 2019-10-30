package com.ashu.api.product_catalog_api.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Entity
@Table(name = "product_tbl")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(unique = true)
    @Size(min=5 , max=60,message ="Name must be between 5 to 60 characters" )
    @NotNull
    private String name;
    @NotNull(message = "Can not be Null")
    private Integer quantity;
    @NotNull(message = "Can not be Null")
    private Double price;
    private String ImageUrl;
    @ManyToOne
    private Category category;
    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
    @ApiModelProperty(hidden = true)
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
    @ApiModelProperty(hidden = true)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
