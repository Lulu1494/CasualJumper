package casualjumper;

import java.awt.Color;

public class Player extends PhysicsEntity {

    @Override
    public void update() {
	setVelocity(4 * Input.getAxis(Input.RIGHT, Input.LEFT), getVelocityY());
    }

    @Override
    public void draw(Camera camera) {
	StdDraw.setPenColor(Color.BLUE);
	double screenX = camera.worldToScreenX(getPositionX());
	double screenY = camera.worldToScreenY(getPositionY());
	StdDraw.filledRectangle(screenX, screenY, 16, 16);
    }

}
