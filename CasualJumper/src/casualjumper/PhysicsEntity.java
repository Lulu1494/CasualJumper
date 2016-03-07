package casualjumper;

public abstract class PhysicsEntity extends MapEntity {

	private Vector2 velocity = Vector2.ZERO;

	public double getVelocityX() {
		return velocity.x;
	}

	public double getVelocityY() {
		return velocity.y;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void setVelocity(double x, double y) {
		setVelocity(new Vector2(x, y));
	}

	public double getGravityScale() {
		return 1;
	}

	public boolean isGrounded() {
		return true;
	}

	public boolean canMoveLeft() {
		return true;   
	}

	public boolean canMoveRight() {
		return true;
	}
}

/**
 * Called every frame to apply gravity and integrate velocity into position
 */
public void physicsUpdate() {
		velocity = velocity.add(Physics.gravity.multiply(getGravityScale()));
		translate(velocity);
	}
}
