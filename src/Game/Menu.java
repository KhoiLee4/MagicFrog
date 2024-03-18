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

	private Image img_background = null;

	private Rectangle bt_setting = null;
	private Rectangle bt_leaderboard = null;

	private int bt_setting_X = 150;
	private int bt_setting_Y = 670;

	private int bt_leaderboard_X = 310;
	private int bt_leaderboard_Y = 670;

	// khởi tạo các giá trị
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		img_background = new Image("Data/Image/Menu.png");

		bt_setting = new Rectangle(bt_setting_X, bt_setting_Y, 118, 128);
		bt_leaderboard = new Rectangle(bt_leaderboard_X, bt_leaderboard_Y, 118, 128);

	}

	// cập nhật game sau mỗi chu kì
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {

		// chuyển sang setting
		if ((bt_setting.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_setting
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}

		// chuyển sang leaderboard
		if ((bt_leaderboard
				.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_leaderboard
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
		}

		// bắt đầu chơi
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
	}

	// hiển thị game lên màn hình
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawString("Menu", 50, 50);
		g.setColor(Color.transparent);

		g.draw(bt_setting);
		g.draw(bt_leaderboard);

		img_background.draw();

	}

	// lấy id trạng thái
	@Override
	public int getID() {
		return 0;
	}

}
