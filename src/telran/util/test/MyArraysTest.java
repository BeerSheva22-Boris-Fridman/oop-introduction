package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {
	private static final int N_RUNS = 10000;
	private static final int N_NUMBERS = 10000;
	Integer numbers[] = {13, 2, -8, 47, 100, 10, -7, 7};
	String strings[] = {"ab", "abm", "abmb", "abmbc"};
	
//	Comparator<Integer> evenOddComparator = (i1, i2) -> {
//		int res = Math.abs(i1) % 2 - Math.abs(i2) % 2;
//		if (res == 0) {
//			res = i1 % 2 != 0 ? Integer.compare(i2, i1) : Integer.compare(i1, i2);
//		}
//		return res;
//	};
	Comparator<Integer> evenOddComparator = this::evenOddCompare;	

	@Test
	void sortTest() {
		String[] strings = {"abcd", "abc", "zz"};
		String[] expected = {"zz", "abc", "abcd"};
//		Integer [] ar = {3, 2, 1};
		MyArrays.sort(strings, new StringsLengthComparator());
		assertArrayEquals(expected, strings);
//		MyArrays.sort(ar, (a, b) -> a - b);
	}
	
	@Test
	void bibarySearchTest() {
		
		Comparator<String> comp = new StringsComparator();
		assertEquals(0, MyArrays.binarySearch(strings, "ab", comp));
		assertEquals(2, MyArrays.binarySearch(strings, "abmb", comp));
		assertEquals(3, MyArrays.binarySearch(strings, "abmbc", comp));
		assertEquals(-1, MyArrays.binarySearch(strings, "a", comp));
		assertEquals(-3, MyArrays.binarySearch(strings, "abma", comp));
		assertEquals(-5, MyArrays.binarySearch(strings, "lmn", comp));		
	}
	
	@Test
	void filterTest() {
		int dividor = 2;
		String subStr = "m";
//		Predicate<Integer> predEven = new DividorPredicate(dividor);
		Predicate<Integer> predEven = t -> t % dividor == 0;
//		Predicate<String> predSubstr = new SubctrPredicate(subStr);
		Predicate<String> predSubstr = s -> s.contains(subStr);
		String expectedStr[] = {"abm", "abmb", "abmbc"};
		Integer expectedNumbers[] = {2, -8, 100, 10};
		assertArrayEquals(expectedStr, MyArrays.filter(strings, predSubstr));
		assertArrayEquals(expectedNumbers, MyArrays.filter(numbers, predEven));		
	}	
	int evenOddCompare (Integer i1, Integer i2) {
		int res = Math.abs(i1) % 2 - Math.abs(i2) % 2;
		if (res == 0) {
			res = i1 % 2 != 0 ? Integer.compare(i2, i1) : Integer.compare(i1, i2);
		}
		return res;
	}
	@Test
	void removeIfTest() {
		int maxNumInAr = 8;
		Integer expected[] = {2, -8, -7, 7};
		Predicate<Integer> predMinNum = m -> m - maxNumInAr > 0;
		assertArrayEquals(expected, MyArrays.removeIf(numbers, predMinNum));
		String Str = "c";
		String expectedStr[] = {"ab", "abm", "abmb"};
		Predicate<String> predStr = s -> s.contains(Str);
		assertArrayEquals(expectedStr, MyArrays.removeIf(strings, predStr));		
	}
	@Test
	void removeRepeatedTest() {
		Integer repeated[] = {3, 1, 3, 2, 2, 2, 1, 3, 1};
		Integer expected[] = {3, 1, 2};		
		assertArrayEquals(expected, MyArrays.removeRepeated(repeated));	
		
		Integer nums[] = {100, 10, 18, 10, 20, 18}; 
		Integer exp[] = {100, 10, 18, 20};
		assertArrayEquals(exp, MyArrays.removeRepeated(nums));	
		
		Integer repeated2[] = {1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,17,18,19};
		Integer expected2[] = {1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19};		
		assertArrayEquals(expected2, MyArrays.removeRepeated(repeated2));
		
	}
	@Test
	void containsTest() {
		assertTrue(MyArrays.contains(numbers, 10));
		assertFalse(MyArrays.contains(numbers, 11));
		assertTrue(MyArrays.contains(strings, "abm"));
		assertFalse(MyArrays.contains(strings, "aa"));
		assertFalse(MyArrays.contains(numbers, null));
		Integer numbs2[] = {1, 2, null, 3, 4};
		assertTrue(MyArrays.contains(numbs2, null));
	}
	
	@Test 
	void evenOddTest() {
		
		Integer expected[] = {-8, 2, 10, 100, 47, 13, 7, -7};
//		MyArrays.sort(numbers, new EvenOddComparator());
		MyArrays.sort(numbers, evenOddComparator);
		assertArrayEquals(expected, numbers);
	}
	
	@Test
	void joinFunctionalTest() {
		String expected = "13, 2, -8, 47, 100, 10, -7, 7";
		assertEquals(expected, MyArrays.join(numbers, ", "));
	}
	@Test
	@Disabled
	void joinPerformanceTest() {
		Integer largeArray[] = getLargeNumbersArray();
		for (int i = 0; i < N_RUNS; i++) {
			MyArrays.join(largeArray, ",");
		}
	}
	Integer[] getLargeNumbersArray() {
		Integer[] res = new Integer[N_NUMBERS];
		Arrays.fill(res, 1000);
		return res;
	}

}