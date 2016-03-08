package engine;

import engine.MapEntity;
import engine.CasualJumper;
import platformer.Physics;
import math.Rectangle;
import math.Vector2;

public abstract class PhysicsEntity extends MapEntity {

	public Vector2 velocity = Vector2.ZERO;

	public double getGravityScale() {
		return 1;
	}

	public boolean canMove(Vector2 offset) {
		if (!isDense()) {
			return true;
		}
		Rectangle bounds = getBounds().translate(offset);
		return !CasualJumper.MAP_ENTITIES.stream()
				.anyMatch(e
						-> !this.equals(e)
						&& e.isDense()
						&& bounds.intersects(e.getBounds())
				);
	}

	/**
	 * Called every frame to apply gravity and integrate velocity into position
	 */
	public void physicsUpdate() {
		if (canMove(Vector2.DOWN)) {
			velocity = velocity.add(Physics.gravity.multiply(getGravityScale()));
		}
		translate(velocity);
	}
}
