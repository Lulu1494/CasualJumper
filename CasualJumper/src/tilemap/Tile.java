package tilemap;

import engine.Camera;
import engine.CasualJumper;
import engine.Entity;
import engine.StdDraw;
import java.awt.Color;
import math.Rectangle;
import math.Vector2;

public abstract class Tile extends Entity {

	private Color color;
	private boolean visible = true;

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public double getGravityScale() {
		return 0;
	}

	@Override
	public void draw(Camera camera) {
		if (isVisible()) {
			Rectangle bounds = getBounds();
			if (bounds.intersects(CasualJumper.mainCamera.getBounds())) {
				StdDraw.setPenColor(color);
				Vector2 screenPoint = camera.worldToScreen(position);
				StdDraw.filledRectangle(
						screenPoint.x + bounds.halfWidth,
						screenPoint.y + bounds.halfHeight,
						bounds.halfWidth,
						bounds.halfHeight);
			}
		}
	}

	public abstract Tile make();

	public static class Empty extends Tile {

		@Override
		public Tile make() {
			Tile tile = new Empty();
			tile.setName("Empty");
			tile.setDense(false);
			tile.setVisible(false);
			return tile;
		}
	}

	public static class Wall extends Tile {

		@Override
		public Tile make() {
			Tile tile = new Wall();
			tile.setName("Wall");
			tile.setDense(true);
			tile.setColor(Color.orange);
			return tile;
		}
	}

	public static class Start extends Tile {

		@Override
		public Tile make() {
			Start tile = new Start();
			tile.setName("Start");
			tile.setDense(false);
			tile.setVisible(false);
			CasualJumper.start = tile;
			return tile;
		}

	}

	public static class Goal extends Tile {

		@Override
		public Tile make() {
			Goal tile = new Goal();
			tile.setName("Goal");
			tile.setDense(false);
			tile.setColor(new Color(255, 255, 0, 128));
			CasualJumper.goal = tile;
			return tile;
		}

		@Override
		public void update() {
			if (CasualJumper.player.getBounds().intersects(getBounds())) {
				CasualJumper.win();
			}
		}

	}
}
