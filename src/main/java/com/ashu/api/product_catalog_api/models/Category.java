package com.ashu.api.product_catalog_api.models;

import javax.persistence.*;

@Entity
@Table(name = "category_tbl")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	private String name;

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category() {
	}

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

	@Override
	public String toString() {
		return String.format("Category [id=%s, name=%s]", id, name);
	}

}
