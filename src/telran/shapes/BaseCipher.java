package telran.shapes;

public class BaseCipher {
	private static final char MAX_VALUE = 126;
	private static final char MIN_VALUE = 33;
	public String key;
	private int lenghtOfKey;

	public BaseCipher(int length) {
		if (length < 2) {
			length = 2;
		} else if (length > 94) {
			length = 94;
		}
		this.key = generateKey(length);
		this.lenghtOfKey = length;
	}

	private String generateKey(int length) {
		String res;
		char[] keyArr = new char[length];
		boolean[] checkSymbol = new boolean[94];
		for (int i = 0; i < keyArr.length; i++) {
			keyArr[i] = generateUniqSymbol(checkSymbol);
		}
		res = String.valueOf(keyArr);
		return res;
	}

	private char generateUniqSymbol(boolean[] checkSymbol) {
		char symbol;
		do {
			symbol = getRandomSymbol();
		} while (checkSymbol[symbol - MIN_VALUE] == true);
		checkSymbol[symbol - MIN_VALUE] = true;
		return symbol;
	}

	private char getRandomSymbol() {
		return (char) (MIN_VALUE + Math.random() * (MAX_VALUE - MIN_VALUE + 1));
	}

	public String cipher(int numberForCipher) {
		String res;
	
		char[] tempCipherdNumber = new char[GetNumberOfCipheredSymbol(numberForCipher)];
		for (int i = 0; i < tempCipherdNumber.length; i++) {
			tempCipherdNumber[i] = (char) (numberForCipher % lenghtOfKey);
			numberForCipher /= lenghtOfKey;
		}
		//char[] reversedArray = reverse(tempCipherdNumber);
		res = String.valueOf(tempCipherdNumber);
		return res;
	}

//private char[] reverse(char[] tempCipherdNumber) {
//	char[] newArray = new char[tempCipherdNumber.length];
//
//    for (int i = 0; i < newArray.length; i++) {
//        newArray[newArray.length - 1 - i] = tempCipherdNumber[i];
//    }
//
//    return newArray;
//	}

private int GetNumberOfCipheredSymbol(int number) {
	int res = 0;
	do {
		number /= lenghtOfKey;
		res++;
	}while (number !=0);
	return res;
}

public int deCipher (String cipherdNumber) {
int res = 0;
for (int i = 0; i < cipherdNumber.length(); i++) {
	char symbol = cipherdNumber.charAt(i);
	res += (int) (key.indexOf(symbol) * Math.pow(lenghtOfKey, cipherdNumber.length() - 1 - i)); 
}
return res;
}

}
