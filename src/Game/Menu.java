package Game;

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

	private Image button_start = null;
	private Image button_setting = null;
	private Image button_leaderboard = null;

	private Rectangle bt_start = null;
	private Rectangle bt_setting = null;
	private Rectangle bt_leaderboard = null;

	private int bt_start_X = 100;
	private int bt_start_Y = 100;

	private int bt_setting_X = 200;
	private int bt_setting_Y = 100;

	private int bt_leaderboard_X = 300;
	private int bt_leaderboard_Y = 100;

	// khởi tạo các giá trị
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		button_start = new Image("C:/Users/ADMIN/Desktop/MagicFrog/Data/Image/button.png");
		button_setting = new Image("C:/Users/ADMIN/Desktop/MagicFrog/Data/Image/button.png");
		button_leaderboard = new Image("C:/Users/ADMIN/Desktop/MagicFrog/Data/Image/button.png");

		bt_start = new Rectangle(bt_start_X, bt_start_Y, button_start.getWidth(), button_start.getHeight());
		bt_setting = new Rectangle(bt_setting_X, bt_setting_Y, button_setting.getWidth(), button_setting.getHeight());
		bt_leaderboard = new Rectangle(bt_leaderboard_X, bt_leaderboard_Y, button_leaderboard.getWidth(),
				button_leaderboard.getHeight());

	}

	// cập nhật game sau mỗi chu kì
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {

		// bắt đầu chơi
		if ((bt_start.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_start.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}

		// chuyển sang setting
		if ((bt_setting.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_setting.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}

		// chuyển sang leaderboard
		if ((bt_leaderboard.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_leaderboard.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
		}

	}

	// hiển thị game lên màn hình
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawString("Menu", 50, 50);
		g.setColor(Color.transparent);

		g.draw(bt_start);
		g.draw(bt_setting);
		g.draw(bt_leaderboard);

		button_start.draw(bt_start_X, bt_start_Y);
		button_setting.draw(bt_setting_X, bt_setting_Y);
		button_leaderboard.draw(bt_leaderboard_X, bt_leaderboard_Y);

	}

	// lấy id trạng thái
	@Override
	public int getID() {
		return 0;
	}

}
