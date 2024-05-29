package Game;

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

public class Leaderboard extends BasicGameState {
	// Nhạc nền, âm thanh hiệu ứng
	private SoundEffect sound;

	// Hình nền
	private Image img_background = null;

	// Hình các nút
	private Image img_bt_back = null;

	// Hitbox các nút
	private Rectangle bt_back = null;

	// Tọa độ các nút
	private int bt_back_X = 30;
	private int bt_back_Y = 830;

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo nhạc nền, âm thanh hiệu ứng
		sound = new SoundEffect();

		// Tạo hình ảnh
		img_background = new Image("Data/Image/Leaderboard.png");
		img_bt_back = new Image("Data/Image/Button_Back.png");

		// Tạo hitbox
		bt_back = new Rectangle(bt_back_X, bt_back_Y, 130, 140);

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		// Chuyển sang setting
		if ((bt_back.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_back
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ hình nền
		img_background.draw();
		img_bt_back.draw(bt_back_X, bt_back_Y);

		// Vẽ hitbox
		g.setColor(Color.transparent);
		g.draw(bt_back);

	}

	@Override
	public int getID() {
		return 3;
	}

}
