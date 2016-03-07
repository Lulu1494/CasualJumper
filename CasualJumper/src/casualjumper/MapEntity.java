package casualjumper;

public abstract class MapEntity extends Entity {

	public Vector2 position = Vector2.ZERO;

	private Rectangle bounds;

	public Rectangle getBounds() {
		return bounds.translate(position);
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void translate(Vector2 displacement) {
		position = position.add(displacement);
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

	public boolean isDense() {
		return true;
	}
}
