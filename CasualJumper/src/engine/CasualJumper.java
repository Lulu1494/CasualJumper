package engine;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import platformer.Player;
import math.Rectangle;
import math.Vector2;

public class CasualJumper {

	public static int tickLag = 17;

	public static Color backgroundColor = Color.WHITE;

	public static final Set<Entity> ENTITIES = new HashSet<>();

	public static Camera mainCamera;
	public static Player player;
	public static boolean running;

	public static boolean addEntity(Entity e) {
		e.enable();
		return ENTITIES.add(e);
	}
	public static boolean removeEntity(Entity e) {
		e.disable();
		return ENTITIES.remove(e);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		start();
	}

	public static void initializeWorld() {
		player = new Player();
		player.setBounds(new Rectangle(16, 16));
		player.setName("Player");
		player.reset();
		addEntity(player);

		mainCamera = new ChaseCamera(player);
		mainCamera.setBounds(new Rectangle(1280, 720));
		mainCamera.setName("Main Camera");
		addEntity(mainCamera);

		int gridWidth = 100, gridHeight = 25;
		for (int x = 0; x < gridWidth; x++) {
			if (x != gridWidth / 2) {
				for (int y = 0; y < gridHeight; y++) {
					if (Math.random() <= .3) {
						Box tile = new Box(Color.orange, new Rectangle(32, 32));
						tile.position = new Vector2(32 * (x - gridWidth / 2), 32 * (y - gridHeight / 2));
						tile.setName("Tile " + x + ", " + y);
						addEntity(tile);
					}
				}
			}
		}
	}

	public static class Box extends Entity {

		public Color color;

		public Box(Color color, Rectangle bounds) {
			this.color = color;
			setBounds(bounds);
		}

		@Override
		public double getGravityScale() {
			return 0;
		}

		@Override
		public void draw(Camera camera) {
			StdDraw.setPenColor(color);
			Vector2 screenPoint = camera.worldToScreen(position);
			Rectangle bounds = getBounds();
			StdDraw.filledRectangle(screenPoint.x + bounds.halfWidth, screenPoint.y + bounds.halfHeight,
					bounds.halfWidth, bounds.halfHeight);
		}
	}

	public static void start() {
		initializeWorld();
		StdDraw.show(0);
		running = true;
		while (running) {
			StdDraw.clear(backgroundColor);
			ENTITIES.removeIf(e -> !e.isEnabled());
			ENTITIES.stream().forEach((Entity e) -> e.update());
			ENTITIES.stream().forEach((Entity e) -> e.physicsUpdate());
			ENTITIES.stream().forEach((Entity e) -> e.lateUpdate());
			ENTITIES.stream().forEach((Entity e) -> e.draw(mainCamera));
			StdDraw.show(tickLag);
		}
	}

}
