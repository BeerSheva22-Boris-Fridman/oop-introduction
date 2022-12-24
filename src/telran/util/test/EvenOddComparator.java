package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int reminder = Math.abs(o1) % 2;
		int res = reminder - Math.abs(o2) % 2;
		if (res == 0) {
			res = reminder !=0 ? Integer.compare(o2, o1) : Integer.compare(o1, o2); 
		}
		return res;
	}

}
