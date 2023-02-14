package telran.recursion;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MdArray<T> {
	private MdArray<T>[] array;
	private T value;

	public MdArray(int[] dimensions, T value) {
		this(dimensions, 0, value);
	}

	@SuppressWarnings("unchecked")
	public MdArray(int[] dimensions, int firstDim, T value) {
		if (firstDim == dimensions.length) {
			this.value = value;
			array = null;
		} else {
			this.value = null;
			array = new MdArray[dimensions[firstDim]];
			for (int i = 0; i < array.length; i++) {
				array[i] = new MdArray<>(dimensions, firstDim + 1, value);
			}
		}
	}
	
	public void forEach (Consumer<T> consumer) {
		if (array == null) {
			consumer.accept(value);
		} else {
			for (MdArray<T> arr: array) {
				arr.forEach(consumer);
			}
		}
	}
	
	public T[] toArray(T[] ar) {
		ArrayList<T> list = new ArrayList<>();
		forEach(obj -> list.add(obj));
		return list.toArray(ar);
	}
	
	public T getValue(int[] indexes) {
		MdArray<T> mdArray = getScalar(indexes);
		return mdArray.value;
	}
	
	public void setValue(int[] indexes, T value) {
		MdArray<T> mdArray = getScalar(indexes);
		mdArray.value = value;
	}

	private MdArray<T> getScalar(int[] indexes) {
		MdArray<T> mdArray = array[indexes[0]];
		for (int i = 1; i < indexes.length; i++) {
			if (mdArray.array == null) {
				throw new NoSuchElementException();
			}
			mdArray = mdArray.array[indexes[i]];
		}		
		if (mdArray.array != null) {
			throw new IllegalStateException();
		}
		return mdArray;
	}

}