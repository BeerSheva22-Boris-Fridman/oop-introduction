package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.Map;
import telran.util.Map.Entry;
import telran.util.Set;

abstract class MapTest {

	Map<Integer, String> map;

	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
		map.put(4, "Four");
	}

	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertNull(map.get(111));
	}

	@Test
	void putTest() {
		assertEquals("One", map.put(1, "another one"));
		assertEquals("another one", map.get(1));
		assertNull(map.put(5, "Five"));
		assertEquals("Five", map.get(5));
	}

	@Test
	void putIfAbsentTest() {
		assertNull(map.putIfAbsent(5, "Five"));
//		assertEquals("another five", map.putIfAbsent(5, "another five"));
		assertEquals("Five", map.putIfAbsent(5, "another five"));
		assertEquals("Five", map.get(5));
	}

	@Test
	void getOrDefaultTest() {
		assertEquals("Four", map.get(4));
		assertEquals("Four", map.getOrDefault(4, "another four"));
		assertEquals("another five", map.getOrDefault(5, "another five"));
	}

	@Test
	void containsKeyTest() {
		assertTrue(map.containsKey(2));
		assertFalse(map.containsKey(22));
	}

	@Test
	void containsValueTest() {
		assertTrue(map.containsValue("One"));
		assertFalse(map.containsValue("Onee"));
		assertTrue(map.containsValue("Two"));
	}

	@Test
	void removeTest() {
		assertEquals("One", map.remove(1));
		assertNull(map.remove(1));
		assertNull(map.get(1));
		assertFalse(map.containsKey(1));
		assertFalse(map.containsValue("One"));
	}

	@Test
	void valuesTest() {
		String[] exp = {"One", "Two", "Three", "Four"};
		Collection<String> values = map.values();
		assertArrayEquals(exp, values.toArray(new String[values.size()]));
	}

	@Test
	void testKeySet() {
		Set<Integer> keys = map.keySet();
		Integer[] exp = {1, 2, 3, 4};
		assertArrayEquals(exp, keys.toArray(new Integer[keys.size()]));
	}
	
	@Test
	void entrySetTest() {
		Integer[] exp1 = {1, 2, 3, 4};
		String[] exp2 = {"One", "Two", "Three", "Four"};
		Set<Entry<Integer, String>> entry = map.entrySet();
		String[] values = new String[entry.size()];
		Integer[] keys = new Integer[entry.size()];
		int i = 0;
		for (Entry<Integer, String> en: entry) {
			keys[i] = en.getKey();
			values[i++] = en.getValue();
		}
		assertArrayEquals(exp1, keys);
		assertArrayEquals(exp2, values);
	}
	

}
