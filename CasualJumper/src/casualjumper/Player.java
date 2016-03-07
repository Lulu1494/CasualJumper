package casualjumper;

import java.awt.Color;

public class Player extends PhysicsEntity {

	private final double jumpSpeed = 10;

	@Override
	public void update() {
		final int moveDirection = Input.getAxis(Input.RIGHT, Input.LEFT);
		final boolean jumping = Input.isKeyDown(Input.JUMP);
		final boolean moving = moveDirection != 0;
		if (jumping) {
			setVelocity(getVelocityX(), jumpSpeed);
		}
		if (moving) {
			setVelocity(4 * moveDirection, getVelocityY());
		}
	}
	
	public boolean canJump() {
		return isGrounded();
	}

	@Override
	public void draw(Camera camera) {
		StdDraw.setPenColor(Color.BLUE);
		double screenX = camera.worldToScreenX(getPositionX());
		double screenY = camera.worldToScreenY(getPositionY());
		StdDraw.filledRectangle(screenX, screenY, 16, 16);
	}

}
