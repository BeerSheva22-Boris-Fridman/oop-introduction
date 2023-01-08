package telran.util.test;



import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class ArrayListTest extends ListTest{
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new ArrayList<>(2);
		super.setUp();
	}
	
	@Test
	void testIterator() {
		ArrayList <String> list = new ArrayList<String>(); 
		list.add("one");
		list.add("two");
		list.add("three");
		Iterator<String> it = list.iterator();
		Assert.assertEquals ("one", it.next());
		Assert.assertEquals ("two", it.next());
		Assert.assertEquals ("three", it.next());
	}
}