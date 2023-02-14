package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer i1, Integer i2) {
		int res = Math.abs(i1) % 2 - Math.abs(i2) % 2;
		if (res == 0) {
			res = i1 % 2 != 0 ? Integer.compare(i2, i1) : Integer.compare(i1, i2);
		}
//		int res = 0;
//		if (i1 % 2 == 0 && i2 % 2 == 0) {
//			res = i1 - i2;
//		} else if (i1 % 2 == 0 && i2 % 2 != 0) {
//			res = -1;
//		} else if (i1 % 2 != 0 && i2 % 2 == 0) {
//			res = 1;
//		} else if (i1 % 2 != 0 && i2 % 2 != 0) {
//			res = i2 - i1;
//		}
		return res;
	}

}
