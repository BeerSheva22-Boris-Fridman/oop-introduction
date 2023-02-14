package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.recursion.MdArray;

public class MdArrayTest {
	MdArray<Integer> array = new MdArray<>(new int[] { 10, 5, 7 }, 50);
	MdArray<String> strings = new MdArray<>(new int[] { 3, 15, 7, 2, 10 }, "hello");

	@Test
	void forEachTest() {
		Integer[] sum = { 0 };
		array.forEach(num -> sum[0] += num);
		assertEquals(sum[0], 17500);

		Integer[] length = { 0 };
		strings.forEach(str -> length[0] += str.length());
		assertEquals(length[0], 31500);
	}

	@Test
	void toArrayTest() {
		Integer[] numbers = array.toArray(new Integer[0]);
		assertEquals(numbers.length, 350);
		Integer[] expected = new Integer[350];
		Arrays.fill(expected, 50);
		assertArrayEquals(numbers, expected);

		String[] stringsArray = strings.toArray(new String[0]);
		assertEquals(stringsArray.length, 6300);
	}

	@Test
	void getValueTest() {
		assertEquals(50, array.getValue(new int[] { 3, 4, 6 }));
		assertThrowsExactly(IllegalStateException.class, () -> array.getValue(new int[] { 3, 4 }));
		assertThrowsExactly(NoSuchElementException.class, () -> array.getValue(new int[] { 3, 4, 6, 0 }));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.getValue(new int[] { 3, 4, 7 }));
	}

	@Test
	void setValueTest() {
		array.setValue(new int[] { 3, 4, 6 }, 100);
		assertEquals(100, array.getValue(new int[] { 3, 4, 6 }));
		assertThrowsExactly(IllegalStateException.class, () -> array.setValue(new int[] { 3, 4 }, 100));
		assertThrowsExactly(NoSuchElementException.class, () -> array.setValue(new int[] { 3, 4, 6, 0 }, 100));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.setValue(new int[] { 3, 4, 7 }, 100));
	}

}