package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	private String name;
    private float price;
    private long categoryId;
}