package casualjumper;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class CasualJumper {

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
		mainCamera = new ChaseCamera();
		mainCamera.setBounds(new Rectangle(0, 0, 1280, 720));
		addEntity(mainCamera);

		player = new Player();
		addEntity(player);

		Box box = new Box(Color.RED, new Rectangle(32, 32));
		box.setPosition(64, 0);
		addEntity(box);
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
			StdDraw.filledRectangle(
					camera.worldToScreenX(getPositionX()),
					camera.worldToScreenY(getPositionY()),
					getBounds().halfWidth,
					getBounds().halfHeight);
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

			StdDraw.show(17);
		}
	}

}
