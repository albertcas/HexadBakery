package com.hexad.bakery.app.models;

import lombok.Builder;

public class Pack {
	
	private int size;
	private Double price;
	
	
	@Builder
	public Pack(int size, Double price) {
		super();
		this.size = size;
		this.price = price;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}	
	
	
}
