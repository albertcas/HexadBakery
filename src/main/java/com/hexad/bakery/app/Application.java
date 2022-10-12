package com.hexad.bakery.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.hexad.bakery.app.models.Bill;
import com.hexad.bakery.app.models.Product;
import com.hexad.bakery.app.services.BakeryBiller;

public class Application {


	public static void main(String[] args) {

		
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Code of the product you want (Croissants(CF), Blueberry Muffins (MB11) or Vegemite Scrolls(VS5)");
			String code = scan.nextLine();
			
			System.out.println("How many you want?");
			int order = scan.nextInt();
			
			
			JSONParser jsonParser = new JSONParser();
			try (FileReader reader = new FileReader("src/main/resources/products.json")) {
				// Read JSON file
				Object obj = jsonParser.parse(reader);

				JSONArray productList = (JSONArray) obj;
				JSONObject productChosen = Utils.findProduct(productList, code);
				if (productChosen == null) {
					System.out.println("The product doesn't exist");
				} else {
					Product product = new Product(productChosen);
					
					Bill bill = BakeryBiller.generateBill(product, order);
					System.out.println(bill.returnBill());;
				}


			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

	}
	

}
	



