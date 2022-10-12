package com.hexad.bakery.app;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Utils {

	public static List<List<Integer>> getShortestSum(List<Integer> packs, int goal) {

		// Base case
		if (packs == null) {
			return null;
		}

		List<List<Integer>> result = new ArrayList<>();
		getSum(packs, 0, goal, new ArrayList<Integer>(), result);

		return result;
	}

	private static void getSum(List<Integer> packs, int index, int goal, List<Integer> current,
			List<List<Integer>> result) {
		// TODO Auto-generated method stub
		if (goal == 0) {
			result.add(new ArrayList<>(current));		
			return;
		}

		for (int i = index; i < packs.size(); i++) {
			if (packs.get(i) <= goal) {
				current.add(packs.get(i));
				getSum(packs, i, goal - packs.get(i), current, result);
				current.remove(Integer.valueOf(packs.get(i)));
			}
		}
	}
	
	public static JSONObject findProduct(JSONArray productList, String code) {
		for (int i = 0; i < productList.size(); i++) {
			JSONObject obj = (JSONObject) productList.get(i);
			if(isChosen(obj, code)) {
				return (JSONObject) productList.get(i);
			}
		}
		return null;
	}

	private static boolean isChosen(JSONObject product, String code) {
		return product.get("code").equals(code);
	}
	

		
	
}
