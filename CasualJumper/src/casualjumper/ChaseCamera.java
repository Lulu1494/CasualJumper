/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casualjumper;

/**
 *
 * @author Davidson
 */
public class ChaseCamera extends Camera {

	@Override
	public void update() {
		setPosition(CasualJumper.player.getPosition());
	}

}
