package tilemap;

import engine.CasualJumper;
import java.util.Map;
import math.Rectangle;
import math.Vector2;

public class Tilemap {

	public final int tileWidth, tileHeight;
	public final int tileMapWidth, tileMapHeight;
	public final int pixelMapWidth, pixelMapHeight;
	public final java.util.Map<String, Tile> mapKey;
	public final String[] mapString;
	public final Tile[] grid;

	private Tilemap(int tileWidth, int tileHeight, Map<String, Tile> mapKey, String[] mapString) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.mapKey = mapKey;
		this.mapString = mapString;
		tileMapHeight = mapString.length;
		tileMapWidth = mapString[0].length() / 2;
		pixelMapWidth = tileWidth * tileMapWidth;
		pixelMapHeight = tileHeight * tileMapHeight;
		grid = new Tile[coordToIndex(tileMapWidth, tileMapHeight)];
	}

	public static Tilemap loadFromStrings(int tileWidth, int tileHeight, int idLength, Map<String, Tile> mapKey, String[] mapString) {
		Tilemap map = new Tilemap(tileWidth, tileHeight, mapKey, mapString);
		Rectangle tileBounds = new Rectangle(tileWidth, tileHeight);
		for (int x = 0; x < map.tileMapWidth; x++) {
			int pos = x * idLength, afterPos = pos + idLength;
			for (int y = 0; y < map.tileMapHeight; y++) {
				String tileID = mapString[map.tileMapHeight - 1 - y].substring(pos, afterPos);
				Tile tile = mapKey.get(tileID).make();
				if (tile == null) {
					System.out.println("Unrecognized tile ID (" + tileID + ").");
				} else {
					tile.setBounds(tileBounds);
					CasualJumper.addEntity(tile);
					map.setTile(x, y, tile);
				}
			}
		}
		return map;
	}

	public final int coordToIndex(int x, int y) {
		return x + y * tileMapWidth;
	}

	public Tile tileAt(int x, int y) {
		return grid[coordToIndex(x, y)];
	}

	public void setTile(int x, int y, Tile t) {
		Vector2 position = new Vector2(x * tileWidth - pixelMapWidth / 2, y * tileHeight - pixelMapHeight / 2);
		t.setPosition(position);
		grid[coordToIndex(x, y)] = t;
	}
}
