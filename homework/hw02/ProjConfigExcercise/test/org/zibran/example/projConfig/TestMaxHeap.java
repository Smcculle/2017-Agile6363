package org.zibran.example.projConfig;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestMaxHeap{
	
	@Test
	public void testRemove1(){
		Integer[] seq = {100, 84, 71, 60, 23, 12, 29, 1, 37, 4};		
		MaxHeap<Integer> heap = new MaxHeap<Integer>(seq, Integer.class);
		assertTrue(heap.isFull());
		int value = heap.remove();
		assertEquals(100,value);
		assertFalse(heap.isFull());
	}

	@Test
	public void testInsert1(){
		Integer[] seq = {100, 84, 71, 60, 23, 12, 29, 1, 37, 4};
		MaxHeap<Integer> heap = new MaxHeap<Integer>(seq, Integer.class);
		assertTrue(heap.isFull());
		heap.insert(90);
		assertEquals("100 90 71 60 84 12 29 1 37 4 23",heap.toString());
		assertFalse(heap.isFull());
	}

	@Test
	public void testInsertToEmptyHeap(){		
		MaxHeap<Integer> heap = new MaxHeap<Integer>(Integer.class);
		assertTrue(heap.isEmpty());
		heap.insert(90);
		assertEquals("90",heap.toString());
	}

	@Test
	public void testRemoveThatMakesEmpty(){		
		Integer[] seq = {100};
		MaxHeap<Integer> heap = new MaxHeap<Integer>(seq, Integer.class);		
		assertEquals("100",heap.toString());
		int value = heap.remove();
		assertEquals(value, 100);
		assertTrue(heap.isEmpty());
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveFromEmptyHeap(){
		MaxHeap<Integer> heap = new MaxHeap<Integer>(Integer.class);
		assertTrue(heap.isEmpty());
		heap.remove(); // shoud raise exception
	}

	@Test
	public void testCreationFromBfsSeqOnly(){
		Integer[] seq = {100, 84, 71, 60, 23, 12, 29, 1, 37, 4};
		MaxHeap<Integer> heap = new MaxHeap<Integer>(seq);
		assertFalse(heap.isEmpty());
		int value = heap.remove();
		assertEquals(value, 100);
	}

	@Test
	public void testCreationToContainStrings(){
		MaxHeap<String> heap = new MaxHeap<String>();
		heap.insert("man");
		heap.insert("Man");
		heap.insert("anna");
		heap.insert("man");
		heap.insert("Anna");
		heap.insert("manna");
		assertFalse(heap.isEmpty());
		String value = heap.remove();
		assertEquals("manna", value);
	}
}