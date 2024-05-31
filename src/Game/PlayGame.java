package Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
	private SoundEffect sound;

	// Ếch
	private Frog frog;

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
	private int indexTutorial = 1;

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

	// Thông báo Item
	private Image img_notice_item = null;
	private Image img_bt_yes = null;
	private Image img_bt_no = null;
	private Image img_item = null;
	private Image img_item1 = null;
	private Image img_item2 = null;
	private Image img_item3 = null;
	private Image img_item4 = null;

	private Rectangle bt_yes = null;
	private Rectangle bt_no = null;

	private int bt_yes_X = 293;
	private int bt_yes_Y = 572;

	private int bt_no_X = 632;
	private int bt_no_Y = 572;

	private int item_X = 475;
	private int item_Y = 431;

	// Cờ kiểm tra
	private boolean isPause = false;
	private boolean isTutorial = true;
	private boolean isNotice = false;
	private boolean isUseItem = false;

	// Init Item
	private static int itemShield = 1;
	private static int itemBottelHp = 1;
	private static int itemEnergyBar = 1;
	private static int itemCrown = 1;

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

		// Tạo thông báo
		img_notice_item = new Image("Data/Image/Notice_Item.png");
		img_bt_yes = new Image("Data/Image/Button_Yes.png");
		img_bt_no = new Image("Data/Image/Button_No.png");
		img_item1 = new Image("Data/Image/Item1.png");
		img_item2 = new Image("Data/Image/Item2.png");
		img_item3 = new Image("Data/Image/Item3.png");
		img_item4 = new Image("Data/Image/Item4.png");
		img_item = img_item1;

		bt_yes = new Rectangle(bt_yes_X, bt_yes_Y, 128, 70);
		bt_no = new Rectangle(bt_no_X, bt_no_Y, 128, 70);

		// Tạo thanh năng lượng
		img_energy = new Image("Data/Image/Energy.png");
		img_energy_border = new Image("Data/Image/Energy_Border.png");
		energy_X = energy_border_X = screenWidth / 2 - img_energy_border.getWidth() / 2;
		energy_Y = energy_border_Y = 15;

		// Tạo hướng dẫn
		img_tutorial = new Image("Data/Image/Tutorial" + indexTutorial + ".png");
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
					indexTutorial++;
					img_tutorial = new Image("Data/Image/Tutorial" + indexTutorial + ".png");
				}
				if (indexTutorial >= 4) {
					isTutorial = false;
				}
			} else {
				// Giảm năng lượng
				if (frog.isAlive()) {
					energy -= energyReduction * (int) time;
					time += (delta / 100000.0f);
				}

				// Tạo Map tự động
				if (Map.totalHeight(map) < screenHeight + 1620) {
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
				int indexMap = 0;
				// kiểm tra Map
				for (Map x : map) {
					// check frog return != 1 => touches obstacles
					flag = x.checkFrog(frog.getHitbox());
					if (flag != 1) {
						break;
					}
					indexMap++;
				}

				// Cập nhật nhân vật theo trạng thái
				frog.update(delta, flag);

				// flag = -2 => drop water, touches car
				if ((flag == -2 || energy <= 0)) {
					frog.deathFrog();
				}
				if (!frog.isAlive()) {

					// Sử dụng item
					useItem(container, flag);

					// Không dùng item
//					if (!isUseItem && !isNotice) {
					if (!isNotice && !frog.isAlive()) {
						// Nhân vật chết
						sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());
					} else if (!isNotice && frog.isAlive()) {

						System.out.println(10);
						System.out.println(indexMap);
						System.out.println(map.size());
						System.out.println(frog.getPos_x());
						System.out.println(frog.getPos_y());

						isUseItem = false;
						for (int i = 0; i < map.size(); i++) {
							// Cập nhật map
							map.get(i).update2(delta);
						}
						// Chance position of frog
						if (indexMap == map.size()) {
							indexMap--;
						}
						frog.setPos_y(map.get(indexMap).pos_y + map.get(indexMap).getImage().getHeight()
								- frog.getHitbox().getHeight());
						frog.getHitbox().setY(frog.getPos_y());

					}

				} else {
					// && (frog.getPos_y() <= 7*screenHeight/8)
					if (frog.isAlive()) {
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
		if(isNotice) {
			img_notice_item.draw();
			img_item.draw(item_X, item_Y);
			g.draw(bt_yes);
			g.draw(bt_no);

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

	// Sử dụng item
	public void useItem(GameContainer container, int flag) {
		// Dùng năng lượng
		if (energy <= 0 && itemEnergyBar > 0) {
			System.out.println("Bạn có muốn dùng sấm sét không 1/0");
			img_item = img_item2;
			 isNotice = true;

			// Đồng ý
			if ((bt_yes.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_yes.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				frog.useItem();
				isUseItem = true;
				energy = 100;
				itemEnergyBar--;
				isNotice = false;
			}
		}
		// Dùng khiên
		else if (flag == -2 && itemShield > 0) {
			System.out.println("Bạn có muốn dùng khien không 1/0");
			img_item = img_item3;
			isNotice = true;
			boolean isChoice = false;
			// Đồng ý
			if ((bt_yes.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_yes.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				frog.useItem();
				isUseItem = true;
				itemShield--;
				isNotice = false;
				isChoice = true;
				return;
			}
			if ((bt_no.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_no.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				System.out.println(1);
				isUseItem = false;
				isNotice = false;
				isChoice = true;
			}
			if (isNotice && !isChoice ) {
				return;
			}

		}
		if (itemBottelHp > 0 && !isUseItem  ) { // Dùng bình máu
			 System.out.println("Bạn có muốn dùng thuoc hoi sinh không 1/0");
			img_item = img_item1;
			isNotice = true;
			// Đồng ý
			if ((bt_yes.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_yes.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				frog.useItem();
				isUseItem = true;
				energy = 100;
				itemBottelHp--;
				isNotice = false;
			}else if ((bt_no.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_no.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) { // Không đồng ý
				sound.click();
				isNotice = false;
			}
			return;

		}
		// solve item crown
		// Dùng tăng điểm
		else if (!frog.isAlive() && itemCrown > 0 ) {
			// System.out.println("Bạn có muốn nhan doi diem không 1/0");
			img_item = img_item4;
			isNotice = true;
			// Đồng ý
			if ((bt_yes.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_yes.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				itemCrown--;
				score *= 2;
				isNotice = false;
			}else if ((bt_no.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_no.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) { // Không đồng ý
				sound.click();
				isNotice = false;
			}
			return;
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
			map.add(new MapLand(map.get(map.size() - 1).pos_x, map.get(map.size() - 1).pos_y, energy));
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
