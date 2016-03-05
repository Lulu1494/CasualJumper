package casualjumper;

public class MapEntity extends Entity {

    private double positionX = 0, positionY = 0;

    private Rectangle bounds;

    public Rectangle getBounds() {
	return bounds;
    }

    public void setBounds(Rectangle bounds) {
	this.bounds = bounds;
    }

    public double getPositionX() {
	return positionX;
    }

    public double getPositionY() {
	return positionY;
    }

    public Vector2 getPosition() {
	return new Vector2(getPositionX(), getPositionY());
    }

    public void setPosition(double x, double y) {
	positionX = x;
	positionY = y;
    }

    public void setPosition(Vector2 position) {
	setPosition(position.x, position.y);
    }

    public void translate(double x, double y) {
	setPosition(getPositionX() + x, getPositionY() + y);
    }

    public void translate(Vector2 displacement) {
	translate(displacement.x, displacement.y);
    }

    public String getImageFilename() {
	return null;
    }

    public void draw(Camera camera) {
	String imageFilename = getImageFilename();
	if (imageFilename != null) {
	    StdDraw.picture(
		    camera.worldToScreenX(getPositionX()),
		    camera.worldToScreenY(getPositionY()),
		    getImageFilename());
	}
    }
}
