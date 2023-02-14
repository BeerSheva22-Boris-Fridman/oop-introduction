package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;


	private class ArrayListIterator implements Iterator<T> {
		int index = 0;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			
			return index < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return array[index++];
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(index - 1);
			index--;
//			ArrayList.this.remove(--index);
			flNext = false;
		}


	}

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T element) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}
	
/*
	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;			
		int index = 0;
//		int count = 0;
		for (int i = 0; i < oldSize; i++) {
			if (predicate.test(array[i])) {
//				count++;
				size--;
			} else {
				array[index++] = array[i];
			}
		}
//		Arrays.fill(array, count, oldSize, null);
		Arrays.fill(array, size, oldSize, null);
		
		return oldSize > size;
	}
	
	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(array, size);
		}
		System.arraycopy(array, 0, ar, 0, size);
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}	
*/
	
	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;

	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		T res = array[index];
		size--;
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while (index < size && !isEqual(array[index], pattern)) {
			index++;
		}
		return index < size ? index : -1;
	}

	

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while (index >= 0 && !isEqual(array[index], pattern)) {
			index--;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return array[index];
	}

	

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		array[index] = element;

	}

	@Override
	public Iterator<T> iterator() {
		
		return new ArrayListIterator();
	}


}