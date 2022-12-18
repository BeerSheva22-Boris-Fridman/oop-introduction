package telran.shapes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import telran.shapes.Canvas;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
import telran.shapes.Square;
import telran.shapes.LeftTriangle;
import telran.shapes.RightTriangle;



class ShapeTests {

	@Test
	@Disabled
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(20, 5);
		assertEquals(20, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation_row(20));
		rectangle.setSymbol("#");
		displayStrings(rectangle.presentation_row(20));
	}

	@Test
	@Disabled
	void squareTest() {
		Square square = new Square(3);
		assertEquals(3, square.getWidth());
		assertEquals(3, square.getHeight());
		displayStrings(square.presentation_row(10));
		square.setSymbol("#");
		displayStrings(square.presentation_row(20));
	}

	private void displayStrings(String strings[]) {
		for (String str : strings) {
			System.out.println(str);
		}
	}

	@Test
	@Disabled
	void triangleTest() {
		LeftTriangle tringle = new LeftTriangle(10); 
		displayStrings(tringle.presentation_row(20));
	}
	@Test
	@Disabled
	void triangleRightTest() {
		RightTriangle tringle = new RightTriangle(5);
		displayStrings(tringle.presentation_row(20));
	}

	@Test
	@Disabled
	void cipherTest() {
		BaseCipher BaseCipher = new BaseCipher(100);
		int number = 5568926;	
		System.out.println("cipher: " + BaseCipher.cipher(number));
		System.out.println("decipher num: " + BaseCipher.deCipher((BaseCipher.cipher(number))));
		assertEquals(number, BaseCipher.deCipher((BaseCipher.cipher(number))));
	}
	
	
		Canvas canvas = new Canvas(10, 20,
				new Shape[] { new Rectangle(10, 3), new Square(10), new LeftTriangle(10) });
		Shape[] shapes = { new Rectangle(10, 3), new Square(10), new LeftTriangle(10), new RightTriangle(10),
				canvas, new Square(10) };
	
	@Test
	@Disabled
	void canvasInRowTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setMargin(3);
		displayStrings(canvas.presentation_row(2));

	}

	@Test
	// @Disabled
	void canvasInColumnTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setDirection("column");
		this.canvas.setDirection("column");
		canvas.setMargin(1);
		displayStrings(canvas.presentation_row(2)); 

	}

	}


