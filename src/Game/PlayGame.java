package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

enum Type_map {
	WATER, LAND, STREET;

	private static final Type_map[] VALUES = values();
	private static final int SIZE = VALUES.length;
	private static final Random RANDOM = new Random();

	public static Type_map getRandomType() {
		return VALUES[RANDOM.nextInt(SIZE)];
	}
}

public class PlayGame extends BasicGameState implements gameConfig {
	// Nhân vật ếch
	public Frog frog = null;
	// Map
	private ArrayList<Map> map;

	public static GameContainer gameContainer = null;

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		frog = new Frog();
		map = new ArrayList<Map>();

		// khởi tạo màng chơi
		map.add(new MapLand(0, screenHeight));
		while (Map.totalHeight(map) < screenHeight + 620) {
			createMap();
		}

		gameContainer = container;

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {

		if (Map.totalHeight(map) < screenHeight + 620) {
			createMap();
		}

		for (int i = 0; i < map.size(); i++) {
			map.get(i).update(delta);

			if (map.get(i).checkLocation()) {
				map.remove(i);
			}
		}

		frog.update(delta);

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawString("Play", 100, 100);

		for (int i = 0; i < map.size(); i++) {
			map.get(i).render();
		}

		frog.render();

	}

	public void createMap() throws SlickException {
		Type_map type = Type_map.getRandomType();
//		while(map.get(map.size() - 1))
		switch (type) {
		case WATER:
			map.add(new MapWater(map.get(map.size() - 1).pos_x, map.get(map.size() - 1).pos_y));
			break;

		case STREET:
			map.add(new MapStreet(map.get(map.size() - 1).pos_x, map.get(map.size() - 1).pos_y));
			break;

		case LAND:
			map.add(new MapLand(map.get(map.size() - 1).pos_x, map.get(map.size() - 1).pos_y));
			break;
			
		default:
			break;
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}