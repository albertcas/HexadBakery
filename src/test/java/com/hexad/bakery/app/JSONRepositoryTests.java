package com.hexad.bakery.app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexad.bakery.app.models.Product;


@SpringBootTest
class JSONRepositoryTests {
	static int NUM_PRODUCTS = 3;
	static String VEGEMITE_SCROLL = "VS5";
	static String VEGEMITE_SCROLL_DESC = "Vegemite Scroll";
	static List<Integer> VEGEMITE_SCROLL_PACKS_SIZES = new ArrayList<Integer>(Arrays.asList(5,3));

	
	static String CROISSANT = "CF";
	static String CROISSANT_DESC = "Croissant";
	static List<Integer> CROISSANT_PACKS_SIZES = new ArrayList<Integer>(Arrays.asList(9,5,3));
	
	static String BLUEBERRY_MUFFIN = "MB11";
	static String BLUEBERRY_MUFFIN_DESC = "Blueberry muffin";
	static List<Integer> BLUEBERRY_MUFFIN_PACKS_SIZES = new ArrayList<Integer>(Arrays.asList(8,5,2));
	
	static String DONUTS = "DON";
	
	
	

	JSONArray productList;

	@BeforeEach
	void readJSON() {

		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("src/main/resources/products.json")) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			productList = (JSONArray) obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void readJSONGives3products() {
		assertTrue(productList.size() > 0);
		assertEquals(NUM_PRODUCTS, productList.size());
	}
	
	@Test
	void vegemiteScrollExists() {
		JSONObject product = null;
		product = Utils.findProduct(productList, VEGEMITE_SCROLL);
		assertTrue(product != null);
		assertEquals(product.get("code"), VEGEMITE_SCROLL);
	}

	@Test
	void croissantlExists() {
		JSONObject product = null;
		product = Utils.findProduct(productList, CROISSANT);
		assertTrue(product != null);
		assertEquals(product.get("code"), CROISSANT);
	}
	
	@Test
	void blueberryMuffinsExists() {
		JSONObject product = null;
		product = Utils.findProduct(productList, BLUEBERRY_MUFFIN);
		assertTrue(product != null);
		assertEquals(product.get("code"), BLUEBERRY_MUFFIN);
	}
	
	@Test
	void donutsNotExists() {
		JSONObject product = null;
		product = Utils.findProduct(productList, DONUTS);
		assertTrue(product == null);
		
	}
	
	@Test 
	void vegemiteScrollsHave2Packs() {
		JSONObject product = null;
		product = Utils.findProduct(productList, VEGEMITE_SCROLL);
		JSONArray packs = (JSONArray) product.get("packs");	
		assertEquals(packs.size(), 2);
	}
	
	@Test 
	void croissantsHave3Packs() {
		JSONObject product = null;
		product = Utils.findProduct(productList, CROISSANT);
		JSONArray packs = (JSONArray) product.get("packs");	
		assertEquals(packs.size(), 3);
	}
	
	@Test 
	void blueberryMuffinsHave3Packs() {
		JSONObject product = null;
		product = Utils.findProduct(productList, BLUEBERRY_MUFFIN);
		JSONArray packs = (JSONArray) product.get("packs");	
		assertEquals(packs.size(), 3);
	}
	
	@Test
	void vegemiteScrollDescription() {
		JSONObject product = null;
		product = Utils.findProduct(productList, VEGEMITE_SCROLL);
		assertEquals(product.get("description"), VEGEMITE_SCROLL_DESC);
	}
	
	@Test
	void croissantDescription() {
		JSONObject product = null;
		product = Utils.findProduct(productList, CROISSANT);
		assertEquals(product.get("description"), CROISSANT_DESC);
	}
	
	@Test
	void blueberryMuffinDescription() {
		JSONObject product = null;
		product = Utils.findProduct(productList, BLUEBERRY_MUFFIN);
		assertEquals(product.get("description"), BLUEBERRY_MUFFIN_DESC);
	}
	
	@Test
	void vegemiteScrollFristPack() {
		JSONObject product = null;
		product = Utils.findProduct(productList, VEGEMITE_SCROLL);
		JSONArray packs = (JSONArray) product.get("packs");	
		JSONObject pack = (JSONObject) packs.get(0);
		assertEquals(Integer.parseInt((String) pack.get("size")), 3);
		assertEquals(Double.parseDouble((String) pack.get("price")), 6.99);
	}
	
	@Test
	void croissantSecondPack() {
		JSONObject product = null;
		product = Utils.findProduct(productList, CROISSANT);
		JSONArray packs = (JSONArray) product.get("packs");	
		JSONObject pack = (JSONObject) packs.get(1);
		assertEquals(Integer.parseInt((String) pack.get("size")), 5);
		assertEquals(Double.parseDouble((String) pack.get("price")), 9.95);
	}
	
	@Test
	void blueberryMuffinThirdPack() {
		JSONObject product = null;
		product = Utils.findProduct(productList, BLUEBERRY_MUFFIN);
		JSONArray packs = (JSONArray) product.get("packs");	
		JSONObject pack = (JSONObject) packs.get(2);
		assertEquals(Integer.parseInt((String) pack.get("size")), 8);
		assertEquals(Double.parseDouble((String) pack.get("price")), 24.95);
	}
	
	@Test
	void createVegemiteScroll() {
		JSONObject product = null;
		product = Utils.findProduct(productList, VEGEMITE_SCROLL);
		Product vegemiteScroll = new Product(product);
		assertEquals(vegemiteScroll.getCode(), VEGEMITE_SCROLL);
		assertEquals(vegemiteScroll.getDescription(), VEGEMITE_SCROLL_DESC);
		assertEquals(vegemiteScroll.getPackSizes(), VEGEMITE_SCROLL_PACKS_SIZES);
		assertEquals(vegemiteScroll.getPacks().size(), 2);
	}
	
	@Test
	void createCroissant() {
		JSONObject product = null;
		product = Utils.findProduct(productList, CROISSANT);
		Product vegemiteScroll = new Product(product);
		assertEquals(vegemiteScroll.getCode(), CROISSANT);
		assertEquals(vegemiteScroll.getDescription(), CROISSANT_DESC);
		assertEquals(vegemiteScroll.getPackSizes(), CROISSANT_PACKS_SIZES);
		assertEquals(vegemiteScroll.getPacks().size(), 3);
	}
	
	@Test
	void creatBlueberryMuffin() {
		JSONObject product = null;
		product = Utils.findProduct(productList, BLUEBERRY_MUFFIN);
		Product vegemiteScroll = new Product(product);
		assertEquals(vegemiteScroll.getCode(), BLUEBERRY_MUFFIN);
		assertEquals(vegemiteScroll.getDescription(), BLUEBERRY_MUFFIN_DESC);
		assertEquals(vegemiteScroll.getPackSizes(), BLUEBERRY_MUFFIN_PACKS_SIZES);
		assertEquals(vegemiteScroll.getPacks().size(), 3);
	}
}
