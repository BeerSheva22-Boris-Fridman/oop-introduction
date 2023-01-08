package telran.util;

import java.util.function.Predicate;

public interface Collection<T>  extends Iterable <T> {
boolean add (T element);
boolean remove (T pattern);
boolean removeIf(Predicate<T> predicate);
boolean isEmpty();
int size();
boolean contains (T pattern);

/****************************/
/**
 * 
 * @param ar
 * @return array containing elements of a Collection
 * if ar reffers to memory that is enough for all elements of Collection then new array is not created, 
 * otherwise there will be create new array.
 * if ar refers to memory that is greater than required for all elements of Collection, then all
 * elements of Collection will be put in the array and rest of memory will be filled by null's
 */
T[] toArray(T[]array);
}
