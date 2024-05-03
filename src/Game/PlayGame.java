package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

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
	// Âm thanh hiệu ứng
	public SoundEffect sound;

	// Ếch
	public Frog frog;

	// Điểm
	static int score;

	// Năng lượng
	private Image img_energy;
	private Image img_energy_border;

	private float energy_X;
	private float energy_Y;

	private int energy_border_X;
	private int energy_border_Y;

	static double energy;

	// Thời gian chạy
	private float time = 1;

	// Map
	private ArrayList<Map> map;

	//
	public static GameContainer gameContainer;

	// Hướng dẫn chơi
	private Image img_tutorial;
	private Image img_bt_nextr;
	private Rectangle bt_next;
	private int bt_next_X = screenWidth / 2 - 65 / 2;
	private int bt_next_Y = 670;
	int index = 1;

	// Nút tạm dừng
	private Image pause_background;
	private Image img_bt_pauseOff;
	private Image img_bt_pauseOn;
	private Image img_bt_pauseSetting;
	private Image img_bt_pauseAgain;

	private Rectangle bt_pause;
	private Rectangle bt_pauseSetting;
	private Rectangle bt_pauseAgain;

	private int bt_pause_X = screenWidth - 10 - 55;
	private int bt_pause_Y = 10;

	private int bt_pauseSetting_X = screenWidth - (10 + 55) * 2;
	private int bt_pauseSetting_Y = 10;

	private int bt_pauseAgain_X = screenWidth - (10 + 55) * 3;
	private int bt_pauseAgain_Y = 10;

	// Cờ kiểm tra
	private boolean isPause = false;
	private boolean isTutorial = true;

	// Khởi tạo
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo âm thanh hiệu ứng
		sound = new SoundEffect();

		// Điểm
		score = 0;

		// Năng lượng
		energy = 100;

		// Tạo nút dừng
		pause_background = new Image("Data/Image/Pause.png");
		img_bt_pauseOff = new Image("Data/Image/Pause_off.png");
		img_bt_pauseOn = new Image("Data/Image/Pause_on.png");
		img_bt_pauseSetting = new Image("Data/Image/Pause_setting.png");
		img_bt_pauseAgain = new Image("Data/Image/Pause_again.png");

		bt_pause = new Rectangle(bt_pause_X, bt_pause_Y, 55, 57);
		bt_pauseSetting = new Rectangle(bt_pauseSetting_X, bt_pauseSetting_Y, 55, 57);
		bt_pauseAgain = new Rectangle(bt_pauseAgain_X, bt_pauseAgain_Y, 55, 57);

		// Tạo thanh năng lượng
		img_energy = new Image("Data/Image/Energy.png");
		img_energy_border = new Image("Data/Image/Energy_Border.png");
		energy_X = energy_border_X = screenWidth / 2 - img_energy_border.getWidth() / 2;
		energy_Y = energy_border_Y = 15;

		// Tạo hướng dẫn
		img_tutorial = new Image("Data/Image/Tutorial" + index + ".png");
		img_bt_nextr = new Image("Data/Image/Button_Continue.png");
		bt_next = new Rectangle(bt_next_X, bt_next_Y, 65, 70);

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
		// Bấm tạm dừng
		if ((bt_pause.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_pause
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			togglePause(container);
		}

		if (!isPause) {
			// Đọc hướng dẫn
			if (isTutorial) {
				if ((bt_next.intersects(
						new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
						|| bt_next.contains(
								new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
						&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					sound.click();
					index++;
					img_tutorial = new Image("Data/Image/Tutorial" + index + ".png");
				}
				if (index >= 4) {
					isTutorial = false;
				}
			} else {
				// Giảm năng lượng
				energy -= energyReduction * (int) time;
				time += (delta / 100000.0f);

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
				if (flag == -2 || energy <= 0) {
					frog.setAlive(false);
					
					System.out.println(score);

					// Nhân vật chết
					sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());

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
		} else {
			// Chuyển sang cài đặt
			if ((bt_pauseSetting
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_pauseSetting.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				Setting.isMenu = false;
				sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
			}
			// Chơi lại
			if ((bt_pauseAgain
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_pauseAgain.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				// Bỏ tạm dừng
				isPause = false;
				// Load lại trạng thái ban đầu
				sbg.getState(1).init(sbg.getContainer(), sbg);
				// Chuyển đổi đến trạng thái ban đầu
				sbg.enterState(1);
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

		// Vẽ thanh năng lượng
		// +15 là viền
		img_energy.getSubImage(0, 0, (int) (img_energy.getWidth() * energy / 100) + 15, img_energy.getHeight())
				.draw(energy_X, energy_Y);
		img_energy_border.draw(energy_border_X, energy_border_Y);

		// Vẽ nhân vật
		frog.render(isPause);

		// Vẽ hướng dẫn
		if (isTutorial) {
			img_tutorial.draw(screenWidth / 2 - img_tutorial.getCenterOfRotationX(),
					screenHeight / 3 - img_tutorial.getCenterOfRotationY() / 2);
			img_bt_nextr.draw(bt_next_X, bt_next_Y, 0.5f);
			g.draw(bt_next);
		}

		// Vẽ nút dừng
		if (isPause) {
			pause_background.draw();
			img_bt_pauseOn.draw(bt_pause_X, bt_pause_Y);
			img_bt_pauseSetting.draw(bt_pauseSetting_X, bt_pauseSetting_Y);
			img_bt_pauseAgain.draw(bt_pauseAgain_X, bt_pauseAgain_Y);
			g.draw(bt_pause);
			g.draw(bt_pauseSetting);
			g.draw(bt_pauseAgain);
		} else {
			img_bt_pauseOff.draw(bt_pause_X, bt_pause_Y);
			g.draw(bt_pause);
		}
	}

	// Tạm dừng trò chơi
	public void togglePause(GameContainer gc) {
		if (isPause) {
			isPause = false;
		} else {
			isPause = true;
		}
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
// 

