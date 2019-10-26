package com.ashu.api.product_catalog_api.models;

import org.springframework.beans.factory.annotation.Value;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category_tbl")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	@Column(unique = true)
	@Size(min=5 , max=60)
	@NotNull(message = "Can not be Null")
	private String name;
	private String description;

	public Category(int id, String name,String description) {
		super();
		this.id = id;
		this.name = name;
		this.description=description;

	}

	public Category() {
	}

	@ApiModelProperty(hidden = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}

}
