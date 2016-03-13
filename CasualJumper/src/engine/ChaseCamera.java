package engine;

import math.Rectangle;
import math.Vector2;

public class ChaseCamera extends Camera {

	private Entity target;

	public ChaseCamera(Entity target) {
		this.target = target;
	}

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	@Override
	public void lateUpdate() {
		if (target != null) {
			Rectangle bounds = getBounds();
			position = target.position.subtract(new Vector2(-target.getBounds().halfWidth + bounds.halfWidth, -target.getBounds().halfHeight + bounds.halfHeight));
		}
	}

}
