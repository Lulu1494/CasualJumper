/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.Camera;
import math.Rectangle;
import math.Vector2;
import engine.MapEntity;

/**
 *
 * @author Davidson
 */
public class ChaseCamera extends Camera {
	
	private MapEntity target;

	public ChaseCamera(MapEntity target) {
		this.target = target;
	}
	
	@Override
	public void update() {
		if(target != null) {
			Rectangle bounds = getBounds();
			position = target.position.subtract(new Vector2(bounds.halfWidth, bounds.halfHeight));
		}
	}

}
