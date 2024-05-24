package Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState {
	// Nhạc nền, âm thanh hiệu ứng
	public GameMusic music;
	public SoundEffect sound;

	// Hình nền
	private Image img_background = null;
	private Image img_bag = null;
	// Hình các nút
	private Image img_bt_setting = null;
	private Image img_bt_leaderboard = null;
	private Image img_bt_shop = null;
	private Image img_bt_bag = null;

	// Hitbox các nút
	private Rectangle bt_setting = null;
	private Rectangle bt_leaderboard = null;
	private Rectangle bt_shop = null;
	private Rectangle bt_bag = null;

	// Tọa độ các nút
	private int bt_setting_X = 290;
	private int bt_setting_Y = 700;

	private int bt_leaderboard_X = 460;
	private int bt_leaderboard_Y = 700;

	private int bt_shop_X = 630;
	private int bt_shop_Y = 700;

	private int bt_bag_X = 940;
	private int bt_bag_Y = 884;

	// Túi
	private boolean isBag = false;

	// Khởi tạo các giá trị
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Đặt icon game
		container.setIcons(new String[] { "Data/Image/Icon.png" });

		// Tạo nhạc nền, âm thanh hiệu ứng
		music = new GameMusic();
//		music.playMusic();
		sound = new SoundEffect();

		// Tạo hình ảnh
		img_background = new Image("Data/Image/Menu.png");
		img_bag = new Image("Data/Image/Bag.png");
		img_bt_setting = new Image("Data/Image/Button_Setting.png");
		img_bt_leaderboard = new Image("Data/Image/Button_Leaderboard.png");
		img_bt_shop = new Image("Data/Image/Button_Shop.png");
		img_bt_bag = new Image("Data/Image/Button_Bag.png");

		// Tạo hitbox
		bt_setting = new Rectangle(bt_setting_X, bt_setting_Y, 130, 140);
		bt_leaderboard = new Rectangle(bt_leaderboard_X, bt_leaderboard_Y, 130, 140);
		bt_shop = new Rectangle(bt_shop_X, bt_shop_Y, 130, 140);
		bt_bag = new Rectangle(bt_bag_X, bt_bag_Y, 80, 86);
	}

	// Cập nhật
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		if (isBag) {
			// Đóng túi đồ
			if ((bt_bag.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_bag.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				toggle(container);
			}
		} else {
			// Chuyển sang setting
			if ((bt_setting
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_setting.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				Setting.isMenu = true;
				sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
			}

			// Chuyển sang leaderboard
			if ((bt_leaderboard
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_leaderboard.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
			}

			// Chuyển sang shop
			if ((bt_shop
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_shop.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				sbg.enterState(8, new FadeOutTransition(), new FadeInTransition());
			}

			// Mở túi đồ
			if ((bt_bag.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_bag.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				toggle(container);
			}

			// Bắt đầu chơi
			if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
			}
		}
	}

	// Hiển thị
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ hình nền
		img_background.draw();

		// Vẽ hitbox
		if (isBag) {
			img_bag.draw();
		} else {
			g.setColor(Color.transparent);
			g.draw(bt_setting);
			g.draw(bt_leaderboard);
			g.draw(bt_shop);
		}
		g.draw(bt_bag);
	}

	// Mở đóng túi
	public void toggle(GameContainer gc) {
		if (isBag) {
			isBag = false;
		} else {
			isBag = true;
		}
	}

	// Lấy id trạng thái
	@Override
	public int getID() {
		return 0;
	}
}

// LƯU Ý 
// chỉnh lại hiệu ứng nút
// căn chỉnh lại hình
