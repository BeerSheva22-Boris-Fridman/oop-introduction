package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.recursion.LinearRecursion.*;

import org.junit.jupiter.api.Test;



public class LinearRecursionTest {
	
	@Test
	void fTest() {
		f(6);
	}
	void f(int a) {
		if (a > 5) {
			f(a - 1);			
		}
	}	
	@Test
	void factorialTest() {
		assertEquals(24, factorial(4));
		assertEquals(720, factorial(6));
	}
	@Test
	void powerTest() {
		assertEquals(1, power(1000, 0));
		assertThrowsExactly(IllegalArgumentException.class, () -> power(1000, -1));
		assertEquals(1000, power(10, 3));
		assertEquals(-1000, power(-10, 3));
	}
	@Test
	void sumTest() {
		int[] ar = {1, 1, 1, 2};
		assertEquals(5, sum(ar));
		assertEquals(0, sum(new int[0]));
	}
	@Test 
	void reverseTest() {
		int[] ar = {1, 2, 3, 4};
		int[] exp = {4, 3, 2, 1};
		reverse(ar);
		assertArrayEquals(exp, ar);
		int[] ar1 = {1, 2, 3, 4, 5};
		int[] exp1 = {5, 4, 3, 2, 1};
		reverse(ar1);
		assertArrayEquals(exp1, ar1);
		int[] ar2 = {1};
		int[] exp2 = {1};
		reverse(ar2);
		assertArrayEquals(exp2, ar2);
	}
	@Test
	void squareTest() {
		assertEquals(0, square(0));
		assertEquals(16, square(4));
		assertEquals(1, square(-1));
	}
	@Test
	void substringTest() {
		assertTrue(isSubstring("hello", "ell"));
		assertFalse(isSubstring("hello", "elo"));
	}

}