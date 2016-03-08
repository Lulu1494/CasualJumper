package math;

import math.Vector2;

public class Rectangle {

	public final double left, bottom, right, top;
	public final double width, height, halfWidth, halfHeight;
	public final double centerX, centerY;

	public Rectangle(double width, double height) {
		this(0, 0, width, height);
	}

	public Rectangle(double left, double bottom, double right, double top) {
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
		width = right - left;
		height = top - bottom;
		halfWidth = width * .5;
		halfHeight = height * .5;
		centerX = left + halfWidth;
		centerY = bottom + halfHeight;
	}

	@Override
	public String toString() {
		return "Rectangle[" + left + ", " + bottom + " -> " + right + ", " + top + "]";
	}

	public Vector2 getCenter() {
		return new Vector2(centerX, centerY);
	}

	public Rectangle translate(Vector2 t) {
		return new Rectangle(left + t.x, bottom + t.y, right + t.x, top + t.y);
	}

	public boolean containsPoint(Vector2 point) {
		return point.x >= left
				&& point.x <= right
				&& point.y >= bottom
				&& point.y <= top;
	}

	public boolean intersects(Rectangle other) {
		return right > other.left
				&& left < other.right
				&& top > other.bottom
				&& bottom < other.top;
	}
}
