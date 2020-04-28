package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditProductDto {

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
	
	private String name;
    private float price;

}