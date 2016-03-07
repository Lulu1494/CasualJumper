package casualjumper;

public class Camera extends MapEntity {

	@Override
	public void setBounds(Rectangle bounds) {
		super.setBounds(bounds);
		StdDraw.setCanvasSize(
				(int) bounds.width,
				(int) bounds.height);
		StdDraw.setXscale(0, bounds.width);
		StdDraw.setYscale(0, bounds.height);
	}

	public double worldToScreenX(double x) {
		return x - getPositionX() + getBounds().halfWidth;
	}

	public double worldToScreenY(double y) {
		return y - getPositionY() + getBounds().halfHeight;
	}
}
