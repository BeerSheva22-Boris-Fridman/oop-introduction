package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Collection<T> extends Iterable<T> {
	boolean add(T element);

	boolean remove(T pattern);

//	boolean removeIf(Predicate<T> predicate);
	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while (it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove(); // obj = it next, but the previous is deleted //check that was "next" doing
								// remove
			}
		}
		return oldSize > size();
	}

	boolean isEmpty();

	int size();

	boolean contains(T pattern);

	/**
	 * 
	 * @param ar
	 * @return array containing elements of Collection if ar refers to memory that
	 *         is enough for all elements of Collection then new array is not
	 *         created, otherwise there will be created new array. if ar refers to
	 *         memory that is greater than required for all elements of Collection
	 *         then all elements Collection will be put in the array and rest of
	 *         memory will be filled by null's
	 */
//	T[] toArray(T[]ar);
	default T[] toArray(T[] ar) {
		int size = size();
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Iterator<T> it = iterator();
		int i = 0;
		while (it.hasNext()) {
			ar[i++] = it.next();
		}
//		int index = 0;
//		for (T obj: this) {
//			ar[index++] = obj;
//		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	default Stream<T> stream() {
		return StreamSupport.stream(this.spliterator(), false);
	}

	default Stream<T> parallelStream() {
		return StreamSupport.stream(this.spliterator(), true);
	}

	@SuppressWarnings("unchecked")
	default T[] toArrayShuffling(T[] array) {
//		toArray(array);
//		int length = array.length;
//		T[] res = (T[]) new Integer[length];
//		int[] index = {0};
//		new Random().ints(0, length).distinct().limit(length).forEach(n -> res[index[0]++] = (T) array[n]);
//		return res;

//		T[] ar = toArray(array);
//		T[] res = Arrays.copyOf(ar, ar.length);
//		int[] index = {0};
//		new Random().ints(0, res.length).distinct().limit(res.length).forEach(i -> res[index[0]++] = ar[i]);
//		return res;

		return (T[]) this.stream().sorted((o1, o2) -> new Random().nextInt()).toArray(Integer[]::new);
	}

}