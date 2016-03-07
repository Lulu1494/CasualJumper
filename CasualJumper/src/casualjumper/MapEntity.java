package casualjumper;

public abstract class MapEntity extends Entity {

	private Vector2 position = Vector2.ZERO;

	private Rectangle bounds;

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public double getPositionX() {
		return position.x;
	}

	public double getPositionY() {
		return position.y;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(double x, double y) {
		setPosition(new Vector2(x, y));
	}

	public void setPosition(Vector2 position) {
		this.position = position;
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
