package telran.util.test;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import telran.util.*;

class LinkedListTest extends ListTest{
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}
	@Test
	public void indexOfTest () {
		LinkedList <String> list = new LinkedList <String> ();
		list.add("one");
		list.add(null);
		list.add("two");
		list.add("three");
		list.add("two");
		
		assertEquals(2, list.indexOf("two"));
	}
	@Test
	public void iteratorTest () {
		LinkedList <String> list = new LinkedList <String> ();
		Iterator<String> itr = list.iterator(); 
		assertEquals(false, itr.hasNext()); 
		list.add("one");
		assertEquals(true, itr.hasNext());
		assertEquals("one", itr.next());
		list.add("two");
		assertEquals("two", itr.next());
		list.add("three");
		assertEquals("three", itr.next());
		assertEquals(false, itr.hasNext());
	}
}