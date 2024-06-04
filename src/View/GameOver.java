package View;

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

import Controller.SoundEffect;

public class GameOver extends BasicGameState {
	// Nhạc nền, âm thanh hiệu ứng
	private SoundEffect sound;

	// Hình nền
	private Image img_background;

	// Hình các nút
	private Image img_bt_menu;
	private Image img_bt_playagain;

	// Hitbox các nút
	private Rectangle bt_menu = null;
	private Rectangle bt_playagain = null;

	// Tọa độ các nút
	private int bt_menu_X = 365;
	private int bt_menu_Y = 600;

	private int bt_playagain_X = 578;
	private int bt_playagain_Y = 600;

	// Khởi tạo
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo nhạc nền, âm thanh hiệu ứng
		sound = new SoundEffect();

		// Tạo hình ảnh
		img_background = new Image("Data/Image/GameLose.png");
		img_bt_menu = new Image("Data/Image/Button_Menu.png");
		img_bt_playagain = new Image("Data/Image/Button_PlayAgain.png");

		// Tạo hitbox
		bt_menu = new Rectangle(bt_menu_X, bt_menu_Y, 130, 140);
		bt_playagain = new Rectangle(bt_playagain_X, bt_playagain_Y, 130, 140);

	}

	// Cập nhật
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		// Chuyển sang setting
		if ((bt_menu.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_menu
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			// Load lại trạng thái ban đầu
			sbg.getState(1).init(sbg.getContainer(), sbg);
			// Chuyển đổi đến trạng thái ban đầu
			sbg.enterState(1);
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}

		// Chuyển sang leaderboard
		if ((bt_playagain
				.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_playagain
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			// Load lại trạng thái ban đầu
			sbg.getState(1).init(sbg.getContainer(), sbg);
			// Chuyển đổi đến trạng thái ban đầu
			sbg.enterState(1);
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
	}

	// Hiển thị
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ hình nền
		img_background.draw();

		// Vẽ hitbox
		g.setColor(Color.transparent);
		g.draw(bt_menu);
		g.draw(bt_playagain);
	}

	// Lấy id trạng trái
	@Override
	public int getID() {
		return 5;
	}
}

// LƯU Ý
//
