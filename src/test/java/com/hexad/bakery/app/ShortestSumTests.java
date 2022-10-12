package com.hexad.bakery.app;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ShortestSumTests {

	
	@Test
	void vegemiteScrollPacksTest_withOrderOf10() {
		ArrayList<Integer> packs = new ArrayList<Integer>(Arrays.asList(3,5));
		int order = 10;
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(5,5));
		List<Integer> result = Utils.getShortestSum(packs, order).get(0);
		assertEquals(result, expected);
	}
	
	@Test
	void blueberryMuffinPacksTest_withOrderOf14() {
		ArrayList<Integer> packs = new ArrayList<Integer>(Arrays.asList(2,5,8));
		Collections.sort(packs, Collections.reverseOrder());
		int order = 14;
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(8,2,2,2));
		assertTrue(Utils.getShortestSum(packs, order).size() > 0);
		List<Integer> result = Utils.getShortestSum(packs, order).get(0);
		assertEquals(result, expected);
	}
	
	@Test
	void croissantPacksTest_withOrderOf13() {
		ArrayList<Integer> packs = new ArrayList<Integer>(Arrays.asList(3,5,9));
		Collections.sort(packs, Collections.reverseOrder());
		int order = 13;
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(5,5,3));
		assertTrue(Utils.getShortestSum(packs, order).size() > 0);
		List<Integer> result = Utils.getShortestSum(packs, order).get(0);
		assertEquals(result, expected);
	}

	@Test
	void noPosibleCombination_givesNoPacks() {
		ArrayList<Integer> packs = new ArrayList<Integer>(Arrays.asList(3,5,9));
		Collections.sort(packs, Collections.reverseOrder());
		int order = 7;
		assertTrue(Utils.getShortestSum(packs, order).size() == 0);
	}
	
	@Test
	void noPacks_givesNoPacks() {
		ArrayList<Integer> packs = new ArrayList<Integer>();
		Collections.sort(packs, Collections.reverseOrder());
		int order = 7;
		assertTrue(Utils.getShortestSum(packs, order).size() == 0);
	}
	

}
