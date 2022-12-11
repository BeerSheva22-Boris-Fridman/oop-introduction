package telran.shapes;

public class Square extends Rectangle {

	public Square(int size) {
		super(size, size);
	}

	public void setWidth(int value) {
		setSize(value);
	}

	public void setHeight(int value) {
		setSize(value);
	}
	private void setSize(int value) {
		super.setWidth(value);
		super.setHeight(value);
	}
}
