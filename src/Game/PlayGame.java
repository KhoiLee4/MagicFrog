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
	// Character frog
	public Frog frog = null;
	// Map
	private ArrayList<Map> map;

	public static GameContainer gameContainer = null;

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		frog = new Frog();
		map = new ArrayList<Map>();

		//initialize the game screen
		map.add(new MapLand(0));
		while (Map.totalHeight(map) < screenHeight + 620) {
			createMap();
		}

		gameContainer = container;

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		// Create new map
		if (Map.totalHeight(map) < screenHeight + 620) {
			createMap();
		}
		// flag = -2 => Dead
		// flag = 1 => move
		// flag = 2 => cham chieu rong cua hinh chu nhat
		// flag = 3 => cham chieu dai ben phai cua hinh nhat
		// flag = 4 => cham chieu dai ben trai cua hinh nhat
		// flag = 5 => cham 2 diem dac biet cua hcn
		int flag = 1;
	
		for (Map x : map) {
			// check frog return != 1 => touches obstacles
			flag = x.checkFrog(frog.getHitbox());
			if (flag != 1) {
				break;
			}
		}
		
		frog.update(delta, flag);
		//flag = -2 => drop water, touches car
		if(flag == -2) {
			frog.setAlive(false);
			// Create play again
			System.out.println("Làm play again "+frog.isAlive());
		}else {		
			for (int i = 0; i < map.size(); i++) {
				// Cập nhật map
				map.get(i).update(delta, flag, frog);
				// Xóa map đã đi qua
				if (map.get(i).checkLocation()) {
					map.remove(i);
				}
			}
		}
		
		

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		for (int i = 0; i < map.size(); i++) {
			map.get(i).render();
		}

		frog.render();

	}

	public void createMap() throws SlickException {
		Type_map type = Type_map.getRandomType();
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