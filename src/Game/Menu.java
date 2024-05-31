package Game;

import java.util.ArrayList;

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
	private SoundEffect sound;

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

	// Tọa độ số lượng
	private int[] quantity_XY = { 324, 465, 626, 793, 364, 364, 364, 364 };

	// Hitbox
	ArrayList<Rectangle> bt_use = null;

	// Loại skin
	static int currentType = 0;
	private ArrayList<Integer> type = null;

	// Vị trí skin
	private int skin_X = 219;
	private int skin_Y = 465;

	// Túi
	private boolean isBag = false;

	// Khởi tạo các giá trị
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Đặt icon game
		container.setIcons(new String[] { "Data/Image/Icon.png" });

		// Tạo nhạc nền, âm thanh hiệu ứng
		sound = new SoundEffect();

		// Tạo hình ảnh
		img_background = new Image("Data/Image/Menu.png");
		img_bag = new Image("Data/Image/Bag.png");
		img_bt_setting = new Image("Data/Image/Button_Setting.png");
		img_bt_leaderboard = new Image("Data/Image/Button_Leaderboard.png");
		img_bt_shop = new Image("Data/Image/Button_Shop.png");

		// Tạo hitbox
		bt_setting = new Rectangle(bt_setting_X, bt_setting_Y, 130, 140);
		bt_leaderboard = new Rectangle(bt_leaderboard_X, bt_leaderboard_Y, 130, 140);
		bt_shop = new Rectangle(bt_shop_X, bt_shop_Y, 130, 140);
		bt_bag = new Rectangle(bt_bag_X, bt_bag_Y, 80, 86);

		// Tạo túi đồ
		img_bt_bag = new Image("Data/Image/Button_Bag.png");
		bt_use = new ArrayList<Rectangle>();
		type = new ArrayList<Integer>();
	}

	// Cập nhật
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		if (isBag) {
			// Đổi skin
			for (int i = 0; i < bt_use.size(); i++) {
				if ((bt_use.get(i).intersects(
						new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
						|| bt_use.get(i).contains(
								new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
						&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					sound.click();
					if (type.get(i) != currentType) {
						currentType = type.get(i);
					} 
				}
			}
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
				sbg.getState(1).init(sbg.getContainer(), sbg);
				sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
			}
		}
	}

	// Hiển thị
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ hình nền
		img_background.draw();
		img_bt_setting.draw(bt_setting_X, bt_setting_Y);
		img_bt_leaderboard.draw(bt_leaderboard_X, bt_leaderboard_Y);
		img_bt_shop.draw(bt_shop_X, bt_shop_Y);
		img_bt_bag.draw(bt_bag_X, bt_bag_Y);

		// Vẽ hitbox
		if (isBag) {
			img_bag.draw();
			renderQuantity();
			renderSkin(g);
		} else {
			g.setColor(Color.transparent);
			g.draw(bt_setting);
			g.draw(bt_leaderboard);
			g.draw(bt_shop);
		}
		g.draw(bt_bag);
	}

	// Vẽ số lượng
	public void renderQuantity() throws SlickException {
		ArrayList<Image> quantity = new ArrayList<Image>();
		for (int item : SignIn.acc_detail.Items()) {
			quantity.add(new Image("Data/Image/text_" + item + ".png"));
		}
		for (int i = 0; i < quantity.size(); i++) {
			quantity.get(i).draw(quantity_XY[i], quantity_XY[i + quantity_XY.length / 2]);
		}
	}

	// Vẽ skin
	public void renderSkin(Graphics g) throws SlickException {
		ArrayList<Image> img_bt_use = new ArrayList<Image>();
		ArrayList<Image> img_skin = new ArrayList<Image>();
		ArrayList<Rectangle> hitbox_use = new ArrayList<Rectangle>();
		int x = skin_X;
		for (int i = 0; i < SignIn.acc_detail.Skins().length; i++) {
			if (SignIn.acc_detail.Skins()[i]) {
				img_skin.add(new Image("Data/Image/Skin" + i + ".png"));
				if(i == currentType) {
					img_bt_use.add(new Image("Data/Image/Button_Use_yes.png"));
				}else {
					img_bt_use.add(new Image("Data/Image/Button_Use_no.png"));
				}
				hitbox_use.add(new Rectangle(x + 11, skin_Y + img_skin.get(i).getHeight() + 10, 78, 30));
				x += img_skin.get(i).getWidth() + 30;
				type.add(i);
			}
		}
		bt_use = hitbox_use;
		x = skin_X;
		for (int i = 0; i < img_skin.size(); i++) {
			img_skin.get(i).draw(x, skin_Y);
			img_bt_use.get(i).draw(hitbox_use.get(i).getX(), hitbox_use.get(i).getY());
			g.setColor(Color.transparent);
			g.draw(hitbox_use.get(i));
			x += img_skin.get(i).getWidth() + 30;
		}
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

// 700 496 612 112
// 219 300 
