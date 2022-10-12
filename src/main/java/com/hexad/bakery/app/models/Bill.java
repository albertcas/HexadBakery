package com.hexad.bakery.app.models;

import java.util.List;

import lombok.Builder;

@Builder
public class Bill {

	int orderedUnits;
	Double price;
	Product product;
	List<Integer> packs;
	
	public int getOrderedUnits() {
		return orderedUnits;
	}
	public void setOrderedUnits(int orderedUnits) {
		this.orderedUnits = orderedUnits;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Integer> getPacks() {
		return packs;
	}
	public void setPacks(List<Integer> packs) {
		this.packs = packs;
	}
	
	public String returnBill() {
		StringBuilder str = new StringBuilder();
		str.append("Your Bill:\n");
		str.append(getOrderedUnits() + " Units of " + this.getProduct().getDescription() + ".\n");
		str.append("Detail:\n");
		for (int i = 0; i < getPacks().size(); i++) {
			str.append(this.getPacks().get(i) + "x= " + this.getProduct().getPricePack(this.getPacks().get(i)) + "$\n");
		}
		str.append("Total: " + this.getPrice());
		return str.toString();
	}
	
	
}
