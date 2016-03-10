/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import math.Rectangle;
import math.Vector2;

/**
 *
 * @author Davidson
 */
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
		if(target != null) {
			Rectangle bounds = getBounds();
			position = target.position.subtract(new Vector2(bounds.halfWidth, bounds.halfHeight));
		}
	}

}
