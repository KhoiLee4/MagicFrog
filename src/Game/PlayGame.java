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

// Loại Map
enum Type_map {
	WATER, LAND, STREET;

	private static final Type_map[] VALUES = values();
	private static final int SIZE = VALUES.length;
	private static final Random RANDOM = new Random();

	// Lấy ngẫu nhiên 1 loại
	public static Type_map getRandomType() {
		return VALUES[RANDOM.nextInt(SIZE)];
	}
}

public class PlayGame extends BasicGameState implements gameConfig {
	// Ếch
	public Frog frog;

	// Map
	private ArrayList<Map> map;

	//
	public static GameContainer gameContainer;

	// Khởi tạo
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo nhân vật
		frog = new Frog();

		// Tạo Map
		map = new ArrayList<Map>();

		// Khởi tạo màn chơi
		map.add(new MapLand(0));
		while (Map.totalHeight(map) < screenHeight + 620) {
			createMap();
		}

		gameContainer = container;
	}

	// Cập nhật
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		// Tạo Map tự động
		if (Map.totalHeight(map) < screenHeight + 620) {
			createMap();
		}

		// flag = -2 => Dead
		// flag = 1 => move
		// flag = 2 => chạm chiều rộng của hình chữ nhật
		// flag = 3 => chạm chiều dài bên phải của hình chữ nhật
		// flag = 4 => chạm chiều dài bên trái của hình chữ nhật
		// flag = 5 => chạm 2 điểm đặc biệt của hình chữ nhật

		// Cờ trạng thái
		int flag = 1;

		// kiểm tra Map
		for (Map x : map) {
			// check frog return != 1 => touches obstacles
			flag = x.checkFrog(frog.getHitbox());
			if (flag != 1) {
				break;
			}
		}

		// Cập nhật nhân vật theo trạng thái
		frog.update(delta, flag);

		// flag = -2 => drop water, touches car
		if (flag == -2) {
			frog.setAlive(false);
			//
			// Nhân vật chết chuyển sang màn hình GameOver (chơi lại)
			//
		} else {
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

	// Hiển thị
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ Map
		for (int i = 0; i < map.size(); i++) {
			map.get(i).render();
		}

		// Vẽ nhân vật
		frog.render();
	}

	// Tạo Map ngẫu nhiên
	public void createMap() throws SlickException {
		// Lấy ngẫu nhiêu loại Map
		Type_map type = Type_map.getRandomType();

		// Tạo Map theo loại
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

	// Lấy id trạng thái
	@Override
	public int getID() {
		return 1;
	}
}

// LƯU Ý
// xem xét tối ưu cờ trạng thái
// Tạo cơ chế tính điểm 
