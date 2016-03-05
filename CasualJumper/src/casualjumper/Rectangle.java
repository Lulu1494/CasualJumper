package casualjumper;

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

    public Vector2 getCenter() {
	return new Vector2(centerX, centerY);
    }

    public Rectangle translate(double tx, double ty) {
	return new Rectangle(left + tx, bottom + ty, right + tx, top + ty);
    }

    public boolean containsPoint(double x, double y) {
	return x >= left
		&& x <= right
		&& y >= bottom
		&& y <= top;
    }

    public boolean intersects(Rectangle other) {
	return right >= other.left
		&& left <= other.right
		&& top >= other.bottom
		&& bottom <= other.top;
    }
}
