package telran.shapes;

public class SquareTriangle extends Square {
	private boolean isDiaganalLeft;

	protected SquareTriangle(int size, boolean isDiaganalLeft) {
		super(size);
		this.isDiaganalLeft = isDiaganalLeft;

	}

	public String[] presentation(int offset) {
		String[] res = new String[getWidth()];
		char[][] charMatrix = getVerticalLine();
		addDiagonalLine(charMatrix);
		for (int i = 0; i < res.length - 1; i++) {
			res[i] = getOffset(offset) + new String(charMatrix[i]);

		}

		res[res.length - 1] = getOffset(offset) + getSymbol().repeat(getWidth());

		return res;  

	}

	private void addDiagonalLine(char[][] charMatrix) {
		char symbol = getSymbol().charAt(0);
		for (int i = 1; i < charMatrix.length - 1; i++) {
			int index = isDiaganalLeft ? i : charMatrix.length - 1 - i;
			charMatrix[i][index] = symbol;
		}
	}

	private char[][] getVerticalLine() {
		char symbol = getSymbol().charAt(0);
		char[][] res = new char[getWidth()][getWidth()];
		int index = isDiaganalLeft ? 0 : res.length - 1;
		for (int i = 0; i < res.length; i++) {
			res[i][index] = symbol;
		}
		return res;
	}
}
