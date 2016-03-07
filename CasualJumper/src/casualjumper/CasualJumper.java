package casualjumper;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class CasualJumper {

	public static int tickLag = 17;

	public static Color backgroundColor = Color.WHITE;

	public static final Set<Entity> ENTITIES = new HashSet<>();
	public static final Set<MapEntity> MAP_ENTITIES = new HashSet<>();
	public static final Set<PhysicsEntity> PHYSICS_ENTITIES = new HashSet<>();

	public static Camera mainCamera;
	public static Player player;
	public static boolean running;

	public static boolean addEntity(Entity e) {
		e.enable();
		return ENTITIES.add(e);
	}

	public static boolean addEntity(MapEntity m) {
		m.enable();
		return MAP_ENTITIES.add(m);
	}

	public static boolean addEntity(PhysicsEntity p) {
		p.enable();
		return PHYSICS_ENTITIES.add(p);
	}

	public static boolean removeEntity(Entity e) {
		e.disable();
		return ENTITIES.remove(e);
	}

	public static boolean removeEntity(MapEntity m) {
		m.disable();
		return MAP_ENTITIES.remove(m);
	}

	public static boolean removeEntity(PhysicsEntity p) {
		p.disable();
		return PHYSICS_ENTITIES.remove(p);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		start();
	}

	public static void initializeWorld() {
		player = new Player();
		player.setBounds(new Rectangle(16, 24));
		player.setName("Player");
		addEntity(player);

		mainCamera = new ChaseCamera(player);
		mainCamera.setBounds(new Rectangle(1280, 720));
		mainCamera.setName("Main Camera");
		addEntity(mainCamera);

		Box box = new Box(Color.RED, new Rectangle(32, 32));
		box.position = new Vector2(31, 0);
		box.setName("Box");
		addEntity(box);

		Box floor = new Box(Color.GREEN, new Rectangle(256, 32));
		floor.position = new Vector2(-100, -32);
		floor.setName("Floor");
		addEntity(floor);

		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				if (Math.random() < .3) {
					Box tile = new Box(Color.orange, new Rectangle(32, 32));
					tile.position = new Vector2(32 * (x - 5 - 2), 32 * (-y - 5));
					tile.setName("Tile " + x + ", " + y);
					addEntity(tile);
				}
			}
		}
	}

	public static class Box extends MapEntity {

		public Color color;

		public Box(Color color, Rectangle bounds) {
			this.color = color;
			setBounds(bounds);
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

			PHYSICS_ENTITIES.stream().filter(Entity::isEnabled).forEach(PhysicsEntity::physicsUpdate);

			Set<Entity> updatedEntities = new HashSet<>(ENTITIES);
			updatedEntities.addAll(MAP_ENTITIES);
			updatedEntities.addAll(PHYSICS_ENTITIES);
			updatedEntities.stream().filter(Entity::isEnabled).forEach(Entity::update);

			Set<MapEntity> drawnEntities = new HashSet<>(MAP_ENTITIES);
			drawnEntities.addAll(PHYSICS_ENTITIES);
			drawnEntities.stream().filter(MapEntity::isEnabled).forEach(m -> m.draw(mainCamera));

			ENTITIES.removeIf(e -> !e.isEnabled());
			MAP_ENTITIES.removeIf(e -> !e.isEnabled());
			PHYSICS_ENTITIES.removeIf(e -> !e.isEnabled());

			StdDraw.show(tickLag);
		}
	}

}
