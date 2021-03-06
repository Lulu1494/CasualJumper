package platformer;

import engine.Camera;
import math.Rectangle;
import math.Vector2;
import engine.StdDraw;
import engine.CasualJumper;
import engine.Entity;
import java.awt.Color;

public class Player extends Entity {

	private boolean resetting;

	private final double moveSpeed = 3;
	public int moveDirection;

	public boolean jumping;
	private final double jumpSustainSpeed = .33;
	private final double jumpStartSpeed = 3;
	public boolean jumpSustain;
	private long jumpEndTime;
	private final long jumpDuration = 200;

	private boolean canJump() {
		return !canMove(Vector2.DOWN);
	}

	@Override
	public void update() {
		resetting = Input.isKeyPressed(Input.RESET);
		moveDirection = Input.getAxis(Input.RIGHT, Input.LEFT);

		if (Input.isKeyPressed(Input.JUMP)) {
			jump(jumpStartSpeed);
		}
		if (jumping) {
			jumpSustain = System.currentTimeMillis() < jumpEndTime;
			if (!Input.isKeyDown(Input.JUMP) || !jumpSustain) {
				jumping = false;
			}
		}
		if (position.y < -1000) {
			position = new Vector2(position.x, position.y + 2000);
		}
	}

	public void jump(double speed) {
		if (canJump()) {
			jumping = true;
			jumpSustain = true;
			jumpEndTime = jumpDuration + System.currentTimeMillis();
			velocity = new Vector2(velocity.x, speed);
		}
	}

	public void reset() {
		setPosition(CasualJumper.start.position);
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
			CasualJumper.ENTITIES.stream()
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
