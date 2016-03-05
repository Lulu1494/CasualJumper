/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casualjumper;

/**
 *
 * @author Davidson
 */
public class GameMath {

    public static double lerp(double a, double b, double c) {
	return a * (1 - c) + b * c;
    }

    public static double randn(double a, double b) {
	return lerp(a, b, Math.random());
    }

    public static double clamp(double n, double a, double b) {
	return n <= a ? a : n >= b ? b : n;
    }
}
