package com.hexad.bakery.app.services;

import java.util.List;

import com.hexad.bakery.app.Utils;
import com.hexad.bakery.app.models.Bill;
import com.hexad.bakery.app.models.Product;
import java.lang.Math;
public class BakeryBiller {
	

	
	public static Double calculatePrice(Product product, int orderedUnits) {
		Double price = 0.0;
		List<Integer> packs = Utils.getShortestSum(product.getPackSizes(), orderedUnits).get(0);
		
		for (int i = 0; i < packs.size(); i++) {
			price += product.getPricePack(packs.get(i));
		}
		return (double) Math.round(price * 100) / 100;
	}
	
	public static Bill generateBill(Product product, int orderedUnits) {
		return Bill.builder().product(product).
				price(calculatePrice(product, orderedUnits)).
				packs(Utils.getShortestSum(product.getPackSizes(), orderedUnits).get(0)).
				orderedUnits(orderedUnits).build();
	}
	

}
