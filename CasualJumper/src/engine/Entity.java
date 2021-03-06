package engine;

import java.util.HashSet;
import java.util.Set;
import math.Rectangle;
import math.Vector2;
import platformer.Physics;

public abstract class Entity {

	private boolean enabled = false;
	private String name = "Entity";

	public Vector2 position = Vector2.ZERO;

	public Vector2 velocity = Vector2.ZERO;
	private Rectangle bounds;
	private boolean dense = true;

	// <editor-fold desc="basic entity stuff">
	@Override
	public String toString() {
		return getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void update() {
	}

	public void lateUpdate() {
	}
	// </editor-fold>

	// <editor-fold desc="visible entity stuff">
	public void setPosition(Vector2 newPosition) {
		position = newPosition;
	}

	public void translate(Vector2 displacement) {
		setPosition(position.add(displacement));
	}

	public String getImageFilename() {
		return null;
	}

	public void draw(Camera camera) {
		String imageFilename = getImageFilename();
		if (imageFilename != null) {
			Vector2 screenPoint = camera.worldToScreen(position);
			StdDraw.picture(screenPoint.x, screenPoint.y, getImageFilename());
		}
	}
	// </editor-fold>

	// <editor-fold desc="physical entity stuff">
	public Rectangle getBounds() {
		return bounds.translate(position);
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean isDense() {
		return dense;
	}

	public void setDense(boolean dense) {
		this.dense = dense;
	}

	public double getGravityScale() {
		return 1;
	}

	/**
	 *
	 * @param offset
	 * @param ignore
	 * @return true if this entity wouldn't be intersecting any solid entities
	 * after possibly being moved by the offset
	 */
	public boolean canMove(Vector2 offset, Set<Entity> ignore) {
		if (!isDense()) {
			return true;
		}
		Rectangle offsetBounds = getBounds().translate(offset);
		return !CasualJumper.ENTITIES.stream()
				.filter(e -> ignore == null || !ignore.contains(e))
				.anyMatch(e
						-> !this.equals(e)
						&& e.isDense()
						&& offsetBounds.intersects(e.getBounds())
				);
	}

	public boolean canMove(Vector2 offset) {
		return canMove(offset, (Set<Entity>) null);
	}

	public boolean canMove(Vector2 offset, Entity ignore) {
		Set<Entity> ignoreSet = new HashSet<>();
		ignoreSet.add(ignore);
		return canMove(offset, ignoreSet);
	}

	/**
	 * Called every frame to apply gravity and integrate velocity into position
	 */
	public void physicsUpdate() {
		final double gravityScale = getGravityScale();
		if (gravityScale != 0 && canMove(Vector2.DOWN)) {
			velocity = velocity.add(Physics.gravity.multiply(gravityScale));
		}
		if (!velocity.isZero()) {
			translate(velocity);
		}
	}
	// </editor-fold>
}
