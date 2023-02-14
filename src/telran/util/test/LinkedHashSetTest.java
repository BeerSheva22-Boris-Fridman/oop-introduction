package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class LinkedListTest extends ListTest{
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}
	
	@Test
	void isLoopTest() {		
		list.add(300);
		LinkedList<Integer> linkedList = (LinkedList<Integer>)list;
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(list.size() - 1, 0);
		assertTrue(linkedList.hasLoop());
		
	}
}