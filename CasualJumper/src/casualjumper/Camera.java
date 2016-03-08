package casualjumper;

public class Camera extends MapEntity {

	@Override
	public boolean isDense() {
		return false;
	}

	@Override
	public void setBounds(Rectangle bounds) {
		super.setBounds(bounds);
		StdDraw.setCanvasSize(
				(int) bounds.width,
				(int) bounds.height);
		StdDraw.setXscale(0, bounds.width);
		StdDraw.setYscale(0, bounds.height);
	}

	public Vector2 worldToScreen(Vector2 worldPoint) {
		final Rectangle bounds = getBounds();
		return new Vector2(
				Math.round(worldPoint.x - position.x),
				Math.round(worldPoint.y - position.y));
	}
}
