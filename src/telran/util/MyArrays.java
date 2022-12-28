package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import telran.util.test.isNullPredicate;

public class MyArrays {
	static public <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--;
		} while (moveMaxAtEnd(objects, length, comparator));

	}

	private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
		boolean res = false;
		for (int i = 0; i < length; i++) {
			if (comp.compare(objects[i], objects[i + 1]) > 0) {
				swap(objects, i, i + 1);
				res = true;
			}
		}
		return res;
	}

	private static <T> void swap(T[] objects, int i, int j) {
		T tmp = objects[i];
		objects[i] = objects[j];
		objects[j] = tmp;

	}

	public static <T> int binarySearch(T[] arraySorted, T key, Comparator<T> comp) {
		int left = 0;
		int right = arraySorted.length - 1;
		int middle = right / 2;
		while (left <= right && !arraySorted[middle].equals(key)) {
			if (comp.compare(key, arraySorted[middle]) < 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}
		return left > right ? -left - 1 : middle;
	}

	public static <T> T[] filter(T[] array, Predicate<T> predicate) {
		int countPredicate = getCountPredicate(array, predicate);

		T[] res = Arrays.copyOf(array, countPredicate);// тут будет массив с первыми countPredicate элементами из
														// массива array, таким образом получаем массив нужной длинны
		int index = 0;
		for (T element : array) { // это запись фор, для масива типа Т. начнется проход ровно по всему массиву
									// array
			if (predicate.test(element)) {
				res[index++] = element;
			}
		}

		return res;
	}

	private static <T> int getCountPredicate(T[] array, Predicate<T> predicate) {
		int res = 0;

		for (T element : array) {
			if (predicate.test(element)) {
				res++;
			}
		}

		return res;
	}

	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
//	Predicate <T> temp;
//	T[] res;
//	temp = predicate.negate();
//	res = MyArrays.filter(array, temp);
//	return res;

		return filter(array, predicate.negate());

	}

	public static <T> T[] removeRepeated(T[] array) {
		// 1. наполнить массив чек значениями тру на тех индексах где находятся
		// повторяющиеся элементы в массиве аррей
		// 2. посчитать количество фолсов, таким образом поймем длинну массива рез
		// 3. переписываем из массива аррей в рез элементы с фолсами в чеке.
		// 4. возвращаем результат
		// вывод- не получилось, так как метод контейнс проверяет весь массив, а не с
		// конкретного индекса
		// 1. проверяем есть ли в массиве рес элемент из массива аррей
		// 2. если отсутствует, то переписываем из массива аррей в рес, если нет, то
		// переходим к следующему элементу и проводим такуюже операцию
		// 3. если индекс (переменная которая подсчитывает количество переданных
		// элементов) массива рез равна длинне массива аррей, то возращаем результат, в
		// противном случае убираем нулевые элементы и возвращаем результат
		T[] res = Arrays.copyOf(array,array.length);
		Arrays.fill(res, null);
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (!contains(res, array[i])) {
				res[index++] = array[i];
			}
		}
		res = removeIf(res, new isNullPredicate<T>());
		return res;
	}

	public static <T> boolean contains(T[] array, T pattern) {
		for (T element : array) {
			if (pattern == null && element == null) {
				return true;
			}
			if (element != null && element.equals(pattern)) { // если в массиве есть значение, то нам достаточно найти
																// хотя бы одно такое
				// значение при этом возвращаем тру
				return true;
			} // если в массиве нет значения, то нам нужно проверить каждый элемент. Если ни
				// один из них не содержит нужное, то вернем фолс
		}
		return false;
	}
	public static <T> String join(T[] array, String delimiter) {
		String res = "";
		if (array.length > 0) {
			StringBuilder builder = new StringBuilder(array[0].toString());
			for (int i = 1; i < array.length; i ++) {
				builder.append(delimiter).append(array[i]);
			}
			res = builder.toString();
		}
		return res;
	}
	public static <T> String joinString(T[] array, String delimiter) {
		String res = "";
		if (array.length > 0) {
			res = array[0].toString();
			for (int i = 1; i < array.length; i ++) {
				res += delimiter + array[i];
			}
		}
		return res;
	}
}
