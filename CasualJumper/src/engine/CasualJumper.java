package engine;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import math.Rectangle;
import platformer.Enemy;
import platformer.Player;
import tilemap.Tile;
import tilemap.Tilemap;

public class CasualJumper {

	public static int tickLag = 17;

	public static Color backgroundColor = Color.WHITE;

	public static final Set<Entity> ENTITIES = new HashSet<>();

	public static Camera mainCamera;
	public static Player player;
	public static boolean running;
	public static Tilemap tilemap;
	public static Tile.Start start;
	public static Tile.Goal goal;
	public static boolean won = false;

	public static void main(String[] args) {
		start();
	}

	public static boolean addEntity(Entity e) {
		e.enable();
		return ENTITIES.add(e);
	}

	public static boolean removeEntity(Entity e) {
		e.disable();
		return ENTITIES.remove(e);
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
			if (won) {
				StdDraw.setFont(new Font("Consolas", Font.PLAIN, 20));
				StdDraw.setPenColor(Color.black);
				StdDraw.text(mainCamera.getBounds().halfWidth, mainCamera.getBounds().halfHeight + 32, "YOU WIN");
				running = false;
			}
			StdDraw.show(tickLag);
		}
	}

	public static void initializeWorld() {
		String[] mapString = new String[]{
			"[]. . . . . . . . . . . . . . . []",
			"[]. . . . . . . . . . . . . . . []",
			"[]. . . . . . . . . . . . . . . []",
			"[][]<>. . . . . []. . . . . ><[][]",
			"[][][][][][][][][][][][][][][][][]"
		};
		Map<String, Tile> mapKey = new HashMap<>();
		mapKey.put(". ", new Tile.Empty());
		mapKey.put("[]", new Tile.Wall());
		mapKey.put("<>", new Tile.Start());
		mapKey.put("><", new Tile.Goal());
		tilemap = Tilemap.loadFromStrings(32, 32, 2, mapKey, mapString);

		Enemy enemy = new Enemy();
		enemy.setBounds(new Rectangle(16, 16));
		enemy.setName("Enemy");
		enemy.setPosition(tilemap.tileAt(15, 4).position);
		addEntity(enemy);

		player = new Player();
		player.setBounds(new Rectangle(16, 16));
		player.setName("Player");
		player.reset();
		addEntity(player);

		mainCamera = new ChaseCamera(player);
		mainCamera.setBounds(new Rectangle(1280, 720));
		mainCamera.setName("Main Camera");
		addEntity(mainCamera);
	}

	public static void win() {
		if (!won) {
			won = true;
			System.out.println("WIN");
		}
	}
}
