package casualjumper;

public class PhysicsEntity extends MapEntity {

    private double velocityX = 0, velocityY = 0;

    public double getVelocityX() {
	return velocityX;
    }

    public double getVelocityY() {
	return velocityY;
    }

    public void setVelocity(double x, double y) {
	velocityX = x;
	velocityY = y;
    }

    /**
     * Called every frame to apply velocity to position
     */
    public void physicsUpdate() {
	translate(velocityX, velocityY);
    }
}
