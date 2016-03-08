package platformer;

import engine.Camera;
import math.Rectangle;
import math.Vector2;
import engine.StdDraw;
import engine.PhysicsEntity;
import engine.CasualJumper;
import java.awt.Color;

public class Player extends PhysicsEntity {

	private boolean resetting;

	private final double moveSpeed = 3;
	private int moveDirection;

	private boolean jumping;
	private final double jumpSustainSpeed = .08;
	private final double jumpStartSpeed = 3;
	private boolean jumpSustain;
	private long jumpEndTime;
	private final long jumpDuration = 200;

	private boolean canJump() {
		return !canMove(Vector2.DOWN);
	}

	@Override
	public double getGravityScale() {
		return jumping ? 0 : 1;
	}

	@Override
	public void update() {
		resetting = Input.isKeyPressed(Input.RESET);
		moveDirection = Input.getAxis(Input.RIGHT, Input.LEFT);

		if (Input.isKeyPressed(Input.JUMP) && canJump()) {
			jumping = true;
			jumpSustain = true;
			jumpEndTime = jumpDuration + System.currentTimeMillis();
			velocity = new Vector2(velocity.x, jumpStartSpeed);
		}
		if (jumping) {
			jumpSustain = System.currentTimeMillis() < jumpEndTime;
			if (!Input.isKeyDown(Input.JUMP) || !jumpSustain) {
				jumping = false;
			}
		}
		if(position.y < -1000) position = new Vector2(position.x, position.y + 2000);
	}

	public void reset() {
		position = new Vector2(0, 500);
//		velocity = Vector2.ZERO;
	}

	@Override
	public void physicsUpdate() {
		if (resetting) {
			reset();

		} else {
			double vx, vy;

			vx = moveSpeed * moveDirection;

			if (jumping) {
				vy = velocity.y + getJumpSpeed();
			} else {
				vy = velocity.y;
			}

			if (vx != 0 && !canMove(new Vector2(Math.signum(vx), 0))) {
				vx = 0;
			}
			if (vy != 0 && !canMove(new Vector2(0, Math.signum(vy)))) {
				vy = 0;
			}

			if (velocity.x != vx || velocity.y != vy) {
				velocity = new Vector2(vx, vy);
			}
		}
		super.physicsUpdate();
		if (isDense()) {
			CasualJumper.MAP_ENTITIES.stream()
					.filter(obstacle -> !this.equals(obstacle) && obstacle.isDense())
					.forEach(obstacle -> {
						Vector2 collisionSolution = Physics.collisionSolution(getBounds(), obstacle.getBounds());
						if (!collisionSolution.isZero()) {
							translate(collisionSolution);
						}
					});
		}

	}

	@Override
	public void draw(Camera camera) {
		StdDraw.setPenColor(Color.BLUE);
		Vector2 screenPoint = camera.worldToScreen(position);
		Rectangle bounds = getBounds();
		StdDraw.filledRectangle(screenPoint.x + bounds.halfWidth, screenPoint.y + bounds.halfHeight,
				bounds.halfWidth, bounds.halfHeight);
	}

	private double getJumpSpeed() {
		return jumpSustainSpeed;
	}

}
