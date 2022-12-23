package telran.shapes;

import java.util.Arrays;

public class Canvas extends Shape {
	private Shape[] shapes;
	private int margin = 2;
	private String direction = "row";

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	@Override
	public int getHeight() {
		return direction.equals("row") ? super.getHeight() : heightsSum() + margin * (shapes.length - 1);
	}

	@Override
	public String[] presentation(int offset) {
		for (Shape shape: shapes) {
			if (shape instanceof Canvas) {
				((Canvas)shape).setDirection(direction); 
			}
		}
		return direction.equals("row") ? presentationInRow(offset) : presentationInColumn(offset);
	}

	private String[] presentationInColumn(int offset) {
		String res[] = new String[heightsSum() + (shapes.length - 1) * margin];
		Arrays.fill(res, " ".repeat(getWidth()));
		String temp [] = getPresentation(0, offset);
		int length = mergeLines(0, res, temp);
		for(int i = 1; i < shapes.length; i++) {
			length = mergeLines(length + margin, res, getPresentation(i, offset));
		}
		return res;
		
		length = mergeLines(length + margin, res, getPresentation(1, offset));
		length = mergeLines(length + margin, res, getPresentation(2, offset));
		length = mergeLines(length + margin, res, getPresentation(3, offset));
		length = mergeLines(length + margin, res, getPresentation(4, offset));
		length = mergeLines(length + margin, res, getPresentation(5, offset));
		
		
		
	}
	
	private int mergeLines(int destPos, String[] res, String[] shapePresentation) {
		System.arraycopy(
				shapePresentation, // массив строк прямоугольника (фигур из шейпа) 
				0, // начальная позиция в массиве фигуры из шейпа с которой начинаем копировать в новый массив
				res, //массив строк канваса в который копируем фигуры
				destPos, //начальный индекс массива канваса в который копируем
				shapePresentation.length // shapePresentation.length кол строк в строковом представлении прямоугольника
				); 
		return destPos + shapePresentation.length;
	}

	private String[] presentationInRow(int offset) {
		String[] res = getPresentation(0, offset);
		for (int i = 1; i < shapes.length; i++) {
			res = join(res, getPresentation(i, margin));
		}
		return res;
	}

	private String[] join(String[] res, String[] cur) {
		String[] joinRes = new String[res.length];
		for (int i = 0; i < joinRes.length; i++) {
			joinRes[i] = res[i] + cur[i];
		}
		return joinRes;
	}

	private String[] getPresentation(int shapeIndex, int offset) { 
		if (direction.equals("row")) {
			shapes[shapeIndex].setHeight(getHeight()); //set otnositsja k figure, a get otnositsja k canvasu
		} else {
			shapes[shapeIndex].setWidth(getWidth());
		}
		return shapes[shapeIndex].presentation(offset);
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	private int heightsSum() {
		int sum = 0;
		for (Shape shape: shapes) {
			sum += shape.getHeight();
		}
		return sum;
	}
}