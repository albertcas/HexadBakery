package com.hexad.bakery.app.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Product {

	private String code;
	private String description;
	private List<Pack> packs = new ArrayList<>();
	
	public Product(JSONObject product) {
		
		this.setCode((String) product.get("code"));
		this.setDescription((String) product.get("description"));
		
		JSONArray packs = (JSONArray) product.get("packs");	
		for (int i = 0; i < packs.size(); i++) {
			JSONObject obj = (JSONObject) packs.get(i);
			String price = (String) obj.get("price");
			String size = (String) obj.get("size");
			addPack(Pack.builder().size(Integer.parseInt(size)).price((Double.parseDouble(price))).build());		
		}
		
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Pack> getPacks() {
		return packs;
	}

	public void setPacks(List<Pack> packs) {
		this.packs = packs;
	}
	
	public void addPack(Pack pack) {
		this.packs.add(pack);
	}
	
	public Double getPricePack(int size) {
		Double price = 0.0;
		for(int i = 0; i < getPacks().size(); i++) {
			if(getPacks().get(i).getSize() == size) {
				price = getPacks().get(i).getPrice();
			}
		}
		return price;
	}
	
	public List<Integer> getPackSizes() {
		List<Integer> packSizes = new ArrayList<>();
		for(int i = 0; i < getPacks().size(); i++) {
			packSizes.add(getPacks().get(i).getSize());
		}
		Collections.sort(packSizes, Collections.reverseOrder());
		return packSizes;
	} 
	
}
