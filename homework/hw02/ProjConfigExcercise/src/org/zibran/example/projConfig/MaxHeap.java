package org.zibran.example.projConfig;

import java.lang.reflect.Array;

/*
 *  Generic implementation of Max Heap
 * 	Internally uses fixed size array, 
 *	which is doubled in size when becomes full.
 */
public class MaxHeap<T extends Comparable<? super T>>{
	private static final int INVALID_INDEX = -1;
	private static final int DEFAULT_CAPACITY = 5;

	private int capacity = 0;
	private T[] items = null;
	private int currentSize = 0;
	
	private Class<T> clazz;

	public MaxHeap(){
		this(DEFAULT_CAPACITY);
	}

	public MaxHeap(Class<T> clazz){
		this(DEFAULT_CAPACITY, clazz);
	}

	@SuppressWarnings("unchecked") // to suppress compiler warning due to typecasting
	public MaxHeap(int initialCapacity){
		this.capacity = initialCapacity;
		this.items = (T[]) new Comparable[capacity]; 
	}

	@SuppressWarnings("unchecked") // to suppress compiler warning due to typecasting
	public MaxHeap(int initialCapacity, Class<T> clazz){
		this.capacity = initialCapacity;
		this.clazz = clazz;
		// use reflection to create the array of the given type
		this.items = (T[]) Array.newInstance(clazz, capacity); 
	}

	/**
	 * Meant to use for test purposes only.
	 */
	@SuppressWarnings("unchecked") // to suppress compiler warning due to typecasting
	public MaxHeap(T[] breadthFirstSeq, Class clazz){		
		this(breadthFirstSeq);
		this.clazz = clazz;
	}

	/**
	 * Meant to use for test purposes only.
	 */
	public MaxHeap(T[] breadthFirstSeq){		
		this.items = breadthFirstSeq;
		this.currentSize = breadthFirstSeq.length;
		this.capacity = currentSize;
	}
	
	public void insert(T value){
		if(currentSize >= capacity){
			doubleCapacity();			
		}
		items[currentSize] = value;
		currentSize++;
		moveUp(currentSize - 1);
	}

	@SuppressWarnings("unchecked") // to suppress compiler warning due to typecasting
	private void doubleCapacity(){
		// use reflection to create the array of the given type
		this.capacity = this.capacity * 2 + 1;
		T[] newArray;
		if(this.clazz != null){
			newArray = (T[]) Array.newInstance(this.clazz, this.capacity);
		}else{
			newArray = (T[]) new Comparable[capacity]; 
		}
		System.arraycopy(this.items, 0, newArray, 0, this.currentSize);
		items = newArray;
	}

	private void moveUp(int index){
		int parentIndex = parentOf(index);
		int compareResult = items[parentIndex].compareTo(items[index]);
		if(compareResult < 0){
			swap(parentIndex, index);
			moveUp(parentIndex);
		}
	}

	private void swap(int i, int j){
		T temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}

	/**
	 *	Removes the item at the root
	 */
	public T remove(){
		if(currentSize == 0){
			throw new IndexOutOfBoundsException("Cannot remove from empty heap");
		}

		T toReturn = items[0];
		items[0] = items[currentSize - 1];
		items[currentSize - 1] = null;
		currentSize--;
		moveDown(0);
		return toReturn;
	}

	public boolean isEmpty(){
		return currentSize == 0;
	}

	public boolean isFull(){
		return currentSize == capacity;
	}

	private void moveDown(int index){
		int potentialChild = largerChild(index);
		if(potentialChild < 0){ // no children
			return;
		}

		int compareResult = items[potentialChild].compareTo(items[index]);
		if(compareResult >= 0){
			swap(potentialChild, index);
			moveDown(potentialChild);
		}
	}

	private int largerChild(int parentIndex){
		int left = leftChildOf(parentIndex);
		int right = rightChildOf(parentIndex);

		if(left < currentSize){
			if(right < currentSize){
				int compareResult = items[left].compareTo(items[right]);
				if(compareResult >= 0){
					return left;
				}else{
					return right;
				}
			}else{ // no right child
				return left;
			}
		}else{
			return INVALID_INDEX;
		}
	}

	private final int parentOf(int i){
		return (i-1)/2;
	}

	private final int leftChildOf(int i){
		return i * 2 + 1;
	}

	private final int rightChildOf(int i){
		return i * 2 + 2;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < currentSize; i++){
			sb.append(" " + items[i]);
		}
		return sb.toString().trim();
	}
}