package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

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
		// была ошибка, так как просто создавалась удвоенная копия
		// исходного массива, а не он сам увеличивался в два раза.

	}

	/**
	 * Описание из документации Removes the first occurrence of the specified
	 * element from this list, if it is present. If the list does not contain the
	 * element, it is unchanged. More formally, removes the element with the lowest
	 * index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an
	 * element exists). Returns true if this list contained the specified element
	 * (or equivalently, if this list changed as a result of the call). Parameters:
	 * o - element to be removed from this list, if present Returns: true if this
	 * list contained the specified element
	 */

	/**
	 * Ход решения: 1. проверить есть ли паттерн в имеющемся массиве 2. паттерна
	 * нет, тогда возращаем фолс 3. паттерн присутствует, удаляем паттерн и
	 * возвращаем тру. 3.1 удаление паттрена - чтобы удалить паттерн мы сдвигаем
	 * элементы на предыдущий индекс
	 */
	@Override
	public boolean remove(T pattern) {
		boolean check = false;
		for (int i = 0; i < size; i++) {
			if (array[i].equals(pattern)) {
				remove(i);
				check = true;
			}
		}
		return check;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T[] toArray(T[] array) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public T remove(int index) {
		if (isIndexExist(index)) {
			System.arraycopy(array, index + 1, array, index, size - index);
			size--;
		}
		return null;
	}

	private boolean isIndexExist(int index) {
		if (index > -1 && index < size) {

		}
		return true;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0; // создаем переменную в которой будет подсчитываться номер индекса по которому
						// найдется паттерн
		// данная переменная будет возвращаться в лучае если паттерн будет найден в
		// массиве
		for (T element : this.array) {

			if (element.equals(pattern)) { // проверяем есть ли в массиве наш паттерн
				return index;// возвращаем текущее значение индекса если паттерн найден

			} else {
				index++;// если паттерн не найден то прибавляем к индексу 1 и возвращаемся в фор для
						// поиска паттерна
				// в следующем индексе

			}
		}
		return -1;// если паттерн нигде не нейден, то возвращаем -1
	}

	@Override
	public int lastIndexOf(T pattern) {
		int i = array.length - 1;
		while (i > -1 && !isTheSame(array[i], pattern)) {
			i--;
		}
		return i > -1 ? i : -1;
	}

	private boolean isTheSame(T t, T pattern) {
		return t == null ? pattern == t : t.equals(pattern);
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int index, T element) {
		// TODO Auto-generated method stub

	}

}
