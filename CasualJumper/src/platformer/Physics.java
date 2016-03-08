package platformer;

import math.Rectangle;
import math.Vector2;

public class Physics {

	// base vector added to all physics entities every physics update
	public static Vector2 gravity = new Vector2(0, -.25);

	// gets the smallest vector that the mover should move to get out of the obstacle
	public static Vector2 collisionSolution(Rectangle mover, Rectangle obstacle) {
		if (!mover.intersects(obstacle)) {
//			System.out.println("not intersecting");
			return Vector2.ZERO;
		}

		// vector from the mover's center to the obstacle's center
		Vector2 toObstacle = obstacle.getCenter().subtract(mover.getCenter());
		double moveX, moveY;

		// obstacle is to the right of mover
		if (toObstacle.x > 0) {
			moveX = obstacle.left - mover.right;
			if (moveX > 0) {
				moveX = 0;
			}
		} // obstacle is to the left of mover
		else {
			moveX = obstacle.right - mover.left;
			if (moveX < 0) {
				moveX = 0;
			}
		}

		// obstacle is above mover
		if (toObstacle.y > 0) {
			moveY = obstacle.bottom - mover.top;
			if (moveY > 0) {
				moveY = 0;
			}
		} // obstacle is below mover
		else {
			moveY = obstacle.top - mover.bottom;
			if (moveY < 0) {
				moveY = 0;
			}
		}
		if (moveX != 0 && moveY != 0) {
			if (Math.abs(moveX) > Math.abs(moveY)) {
				moveX = 0;
			} else {
				moveY = 0;
			}
		}

		return new Vector2(moveX, moveY);
	}
}
