package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

public class StreamTests {

	static ArrayList<Integer> list;
	static Integer[] numbers = { 10, 20, 3, 8, 100, 200, -10, -5 };

	@BeforeAll
	static void setUp() {
		list = new ArrayList<>();
//		Arrays.stream(numbers).forEach(obj -> list.add(obj));
		Arrays.stream(numbers).forEach(list::add);
	}
	@Test
	void sortTest() {
		Integer[] expected = { -10, -5, 3, 8, 10, 20, 100, 200 };
		assertArrayEquals(expected, list.stream().sorted().toArray(Integer[]::new));
	}
	@Test
	void sumNegative() {
		assertEquals(-15, list.stream().filter(n -> n < 0).mapToInt(n -> n).sum());
	}
	@Test
	void stringsArrayTest() {
		String[] expected = { "10", "20", "3", "8", "100", "200", "-10", "-5" };
		assertArrayEquals(expected, list.stream().map(n -> "" + n).toArray(String[]::new));
	}
	@Test
	void stringTest() {
		String expected = "10, 20, 3, 8, 100, 200, -10, -5";
		assertEquals(expected, list.stream().map(n -> n.toString()).collect(Collectors.joining(", ")));
	}
	@Test
	void sportLotoTest() {
		new Random().ints(1, 50).distinct().limit(7).forEach(n -> System.out.print(n + " "));
	}
	@Test
	void toArrayShufflingTest() {
		Integer[] nums = { 11, 22, 33, 44, 55, 66, 77 };
		ArrayList<Integer> list2 = new ArrayList<>();
		Arrays.stream(nums).forEach(list2::add);
		
		Integer[] shuffled = list2.toArrayShuffling(new Integer[nums.length]);
		assertFalse(Arrays.equals(nums, shuffled));
		assertEquals(nums.length, shuffled.length);
		
		System.out.println();
		Arrays.stream(nums).forEach(n -> System.out.print(n + " "));
		System.out.println();
		Arrays.stream(shuffled).forEach(n -> System.out.print(n + " "));
		
		Arrays.sort(shuffled);
		assertArrayEquals(nums, shuffled);
	}

}