package math;

public class Vector2 {

	public static final Vector2 ZERO = new Vector2(0, 0);
	public static final Vector2 UP = new Vector2(0, 1);
	public static final Vector2 DOWN = new Vector2(0, -1);
	public static final Vector2 RIGHT = new Vector2(1, 0);
	public static final Vector2 LEFT = new Vector2(-1, 0);

	public final double x, y;

	public Vector2() {
		this(0, 0);
	}

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public static Vector2 fromPolar(double magnitude, double angle) {
		return new Vector2(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public Vector2 add(Vector2 other) {
		return new Vector2(x + other.x, y + other.y);
	}

	public Vector2 subtract(Vector2 other) {
		return new Vector2(x - other.x, y - other.y);
	}

	public Vector2 multiply(double s) {
		return s == 0 ? ZERO : new Vector2(x * s, y * s);
	}

	public Vector2 divide(double d) {
		return multiply(1 / d);
	}

	public Vector2 negate() {
		return isZero() ? ZERO : new Vector2(-x, -y);
	}

	public Vector2 normalized() {
		return divide(magnitude());
	}

	public Vector2 toMagnitude(double newMagnitude) {
		if (isZero()) {
			return this;
		}
		return multiply(newMagnitude / magnitude());
	}

	public boolean isZero() {
		return equals(ZERO);
	}

	public double magnitude() {
		return isZero() ? 0 : Math.hypot(x, y);
	}

	public double angle() {
		return isZero() ? 0 : Math.atan2(y, x);
	}

	public double squareMagnitude() {
		return isZero() ? 0 : dotProduct(this);
	}

	public double dotProduct(Vector2 other) {
		return isZero() || other.isZero() ? 0 : x * other.x + y * other.y;
	}

	public boolean equals(Vector2 other) {
		return x == other.x && y == other.y;
	}
}
