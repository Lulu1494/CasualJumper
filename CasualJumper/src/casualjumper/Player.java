package casualjumper;

import java.awt.Color;

public class Player extends PhysicsEntity {

	private final double jumpSpeed = 6;
	private final double moveSpeed = 3;

	@Override
	public double getGravityScale() {
		return 1;
	}

	@Override
	public void update() {
		double newVelocityX = moveSpeed * Input.getAxis(Input.RIGHT, Input.LEFT);
		double newVelocityY = velocity.y;
		
		if(Input.isKeyPressed(Input.RESET)) {
			position = Vector2.ZERO;
		}

		if (!canMove(Vector2.DOWN)) {
			if (Input.isKeyDown(Input.JUMP)) {
				newVelocityY = jumpSpeed;
			}
		} else {
			newVelocityY = velocity.y;
		}

		if (newVelocityX != 0 && !canMove(new Vector2(Math.signum(newVelocityX), 0))) {
			newVelocityX = 0;
		}
		if (newVelocityY != 0 && !canMove(new Vector2(0, Math.signum(newVelocityY)))) {
			newVelocityY = 0;
		}

		velocity = new Vector2(newVelocityX, newVelocityY);

		CasualJumper.MAP_ENTITIES.stream()
				.filter(obstacle -> !this.equals(obstacle) && obstacle.isDense())
				.forEach(obstacle -> {
					Vector2 collisionSolution = Physics.collisionSolution(getBounds(), obstacle.getBounds());
					if (!collisionSolution.isZero()) {
						translate(collisionSolution);
					}
				});
	}

	@Override
	public void draw(Camera camera) {
		StdDraw.setPenColor(Color.BLUE);
		Vector2 screenPoint = camera.worldToScreen(position);
		Rectangle bounds = getBounds();
		StdDraw.filledRectangle(screenPoint.x + bounds.halfWidth, screenPoint.y + bounds.halfHeight,
				bounds.halfWidth, bounds.halfHeight);
	}

}
