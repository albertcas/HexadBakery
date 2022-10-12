package com.hexad.bakery.app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexad.bakery.app.models.Product;
import com.hexad.bakery.app.services.BakeryBiller;


@SpringBootTest
class BillerTests {

	static String VEGEMITE_SCROLL = "VS5";
	static String CROISSANT = "CF";
	static String BLUEBERRY_MUFFIN = "MB11";

	JSONArray productList;
	Product vegemiteScroll;
	Product croissant;
	Product blueberryMuffin;

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
		
		vegemiteScroll = new Product(Utils.findProduct(productList, VEGEMITE_SCROLL));
		croissant = new Product(Utils.findProduct(productList, CROISSANT));
		blueberryMuffin = new Product(Utils.findProduct(productList, BLUEBERRY_MUFFIN));
	}
	
	@Test
	void vegemiteScrollOrderOf10_gives1798() {
		assertEquals(BakeryBiller.generateBill(vegemiteScroll, 10).getPrice(), 17.98);
	}
	
	@Test
	void blueBerryOrderOf14_gives548() {
		assertEquals(BakeryBiller.generateBill(blueberryMuffin, 14).getPrice(), 54.8);
	}
	
	@Test
	void croissantOrderOf13_gives2585() {
		assertEquals(BakeryBiller.generateBill(croissant, 13).getPrice(), 25.85);
	}
}
