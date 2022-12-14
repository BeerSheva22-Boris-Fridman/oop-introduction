package telran.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ShapeTests {

	@Test
	@Disabled
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(20, 5);
		assertEquals(20, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
	}

	@Test
	@Disabled
	void squareTest() {
		Square square = new Square(3);
		assertEquals(3, square.getWidth());
		assertEquals(3, square.getHeight());
		displayStrings(square.presentation(10));
		square.setSymbol("#");
		displayStrings(square.presentation(20));
	}

	private void displayStrings(String strings[]) {
		for (String str : strings) {
			System.out.println(str);
		}
	}

	@Test
	void triangleTest() {
		LeftTriangle tringle = new LeftTriangle(10); 
		displayStrings(tringle.presentation(20));
	}
	@Test
	void triangleRightTest() {
		RightTriangle tringle = new RightTriangle(5);
		displayStrings(tringle.presentation(20));
	}

	@Test
	void cipherTest() {
		BaseCipher basecipher = new BaseCipher(2);
		int number = 10;	
		System.out.println("cipher: " + basecipher.cipher(number));
		System.out.println("decipher num: " + basecipher.deCipher((basecipher.cipher(number))));
		assertEquals(number, basecipher.deCipher((basecipher.cipher(number))));
	}
	

	}


