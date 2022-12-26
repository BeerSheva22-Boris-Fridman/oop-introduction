package telran.util.test;

import java.util.function.Predicate;

public class isNullPredicate <T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		if (t == null) {
			return true;
		} else {
			return false;
		}

	}
}
