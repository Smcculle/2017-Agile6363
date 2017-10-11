package org.zibran.example.projConfig;

public class Startup{
	
	public static void main(String[] args){
		final int size = 10;
		MaxHeap<Integer> heap = new MaxHeap<Integer>(size, Integer.class);
		heap.insert(1);
		heap.insert(3);
		heap.insert(5);
		heap.insert(4);
		System.out.println(heap);
	}
}