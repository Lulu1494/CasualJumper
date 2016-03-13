package platformer;

import engine.Camera;
import engine.CasualJumper;
import math.Rectangle;
import math.Vector2;
import engine.StdDraw;
import java.awt.Color;

public class Enemy extends Player {
	
	private final double moveSpeed = 1.5;
	
	public Enemy() {
		moveDirection = -1;
	}
	
	@Override
	public void update() {
		if (getBounds().translate(new Vector2(moveDirection * moveSpeed, 0)).intersects(CasualJumper.player.getBounds())) {
			CasualJumper.player.reset();
		}
		if (!canMove(Vector2.DOWN)) {
			if (!canMove(new Vector2(moveDirection, 0)) || canMove(new Vector2(moveDirection * getBounds().width, -1)) && canMove(new Vector2(moveDirection * getBounds().width, -34))) {
				moveDirection = -moveDirection;
			}
			if (!canMove(new Vector2(getBounds().width * moveDirection, 0), CasualJumper.player) && canMove(new Vector2(getBounds().width * moveDirection, 32))) {
				jump(5);
			}
		}
		velocity = new Vector2(moveSpeed * moveDirection, velocity.y);
	}
	
	@Override
	public void jump(double speed) {
		super.jump(speed);
		jumping = false;
		jumpSustain = false;
	}
	
	@Override
	public void draw(Camera camera) {
		StdDraw.setPenColor(Color.GREEN);
		Vector2 screenPoint = camera.worldToScreen(position);
		Rectangle bounds = getBounds();
		StdDraw.filledRectangle(
				screenPoint.x + bounds.halfWidth,
				screenPoint.y + bounds.halfHeight,
				bounds.halfWidth,
				bounds.halfHeight);
	}
	
}
