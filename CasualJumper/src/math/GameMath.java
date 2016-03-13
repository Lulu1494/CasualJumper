package math;

public class GameMath {

	public static double lerp(double a, double b, double c) {
		return a * (1 - c) + b * c;
	}

	public static double rand(double a, double b) {
		return lerp(a, b, Math.random());
	}

	public static int rand(int a, int b) {
		return (int) Math.round(rand((double) a, b));
	}

	public static double clamp(double n, double a, double b) {
		return n <= a ? a : n >= b ? b : n;
	}

	public static int clamp(int n, int a, int b) {
		return n <= a ? a : n >= b ? b : n;
	}
}
