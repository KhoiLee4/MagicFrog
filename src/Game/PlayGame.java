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

		// khởi tạo màn chơi
		map.add(new MapLand(0));
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
		// flag = 1 => move
		// flag = 2 => cham chieu rong cua hinh chu nhat
		// flag = 3 => cham chieu dai ben phai cua hinh nhat
		// flag = 4 => cham chieu dai ben trai cua hinh nhat
		// flag = 5 => cham 2 diem dac biet cua hcn
		int flag = 1, index = -1;
		boolean test = true;
		for (Map x : map) {
			index++;
			// check frog return != 1 => trung vat can
			flag = x.checkFrog(frog.getHitbox());
			if (flag != 1) {
//				System.out.println("Roi vong lap");
				test = false;
				break;

			}
		}
		if (test) {
			System.out.println("Chay het map");
		}
		System.out.println(flag + " Play game ");

		frog.update(delta, flag);

		if (flag == 1) {
			// Di chuyen thi tat ca dc di chuyen
			for (int i = 0; i < map.size(); i++) {
				map.get(i).update(delta, flag, frog.getHitbox());
				if (map.get(i).checkLocation()) {
					map.remove(i);
				}
			}
		} else { 
			// Khong di chuyen dc thi chi co xe, tam van dc di chuyen
			//(map.get(i).getTypeMap().equals("land") && index != i)
			for (int i = 0; i < map.size(); i++) {
				if (map.get(i).getTypeMap().equals("water") ||
						map.get(i).getTypeMap().equals("street")
						) {
					map.get(i).update(delta, flag, frog.getHitbox());
				}
			//	map.get(index).update(delta, flag, frog.getHitbox());
				
			}

		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		// g.drawString("Play", 100, 100);

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