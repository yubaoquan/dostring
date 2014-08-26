package implenentsAndExtends;

import java.util.Arrays;
import java.util.List;

abstract class Shape {
	public boolean marked = false;

	void draw() {
		System.out.println(this + ".draw()" + " marked:" + marked);
	}

	abstract public String toString();
}

class Circle extends Shape {
	Circle() {
		super.marked = true;
	}

	public String toString() {
		return "Circle";
	}
}

class Square extends Shape {
	public String toString() {
		return "Square";
	}
}

class Triangle extends Shape {
	public String toString() {
		return "Triangle";
	}
}

class Rhomboid extends Shape {
	public String toString() {
		return "Rhomboid";
	}
}

public class Shapes {

	public static void rotate(Shape shape) {
		if (!(shape instanceof Circle)) {
			System.out.println("rotating " + shape);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Shape> shapeList = Arrays.asList(new Circle(), new Square(),
				new Triangle(), new Rhomboid());
		for (Shape shape : shapeList) {
			shape.draw();
			rotate(shape);
		}

		Shape shape = new Rhomboid();
		shape.draw();
		if (shape instanceof Rhomboid) {
			((Rhomboid) shape).draw();
		}
		if (shape instanceof Circle) {
			((Circle) shape).draw();
		}

	}

}
