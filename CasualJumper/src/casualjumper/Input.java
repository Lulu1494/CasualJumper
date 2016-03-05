/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casualjumper;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Davidson
 */
public class Input {

    public static int RIGHT = KeyEvent.VK_RIGHT,
	    LEFT = KeyEvent.VK_LEFT,
	    JUMP = KeyEvent.VK_Z;

    private final static Set<Integer> keysDown = new HashSet<>();

    public static int getAxis(int positiveKeyCode, int negativeKeyCode) {
	return (isKeyDown(positiveKeyCode) ? 1 : 0)
		- (isKeyDown(negativeKeyCode) ? 1 : 0);
    }

    /**
     *
     * @param keyCode
     * @return true if the key is currently down
     */
    public static boolean isKeyDown(int keyCode) {
	return StdDraw.isKeyPressed(keyCode);
    }

    /**
     *
     * @param keyCode the key to check.
     * @return true for the first time the key is known to be down since it was
     * last known to be up.
     */
    public static boolean isKeyPressed(int keyCode) {
	if (!isKeyDown(keyCode)) {
	    keysDown.remove(keyCode);
	} else if (!keysDown.contains(keyCode)) {
	    keysDown.add(keyCode);
	    return true;
	}
	return false;
    }
}