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
	// Nhạc nền
	public GameMusic music;
	// Âm thanh hiệu ứng
	public SoundEffect sound;

	public Animation animationSetting;
	public Animation animation = null;

	private Image img_background = null;
	private Image[] img_bt_setting;
	private Image img_bt_leaderboard = null;

	private Rectangle bt_setting = null;
	private Rectangle bt_leaderboard = null;

	private int bt_setting_X = 382;
	private int bt_setting_Y = 705;

	private int bt_leaderboard_X = 550;
	private int bt_leaderboard_Y = 705;

	int checkAnimation = 0;

	// khởi tạo các giá trị
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		container.setIcons(new String[] { "Data/Image/Icon.png" });

		music = new GameMusic();
//		music.playMusic();
		sound = new SoundEffect();

		img_background = new Image("Data/Image/Menu.png");
		img_bt_setting = new Image[] { new Image("Data/Image/Button_Setting.png"),
				new Image("Data/Image/Push_Setting.png") };
		img_bt_leaderboard = new Image("Data/Image/Button_Leaderboard.png");

		bt_setting = new Rectangle(bt_setting_X, bt_setting_Y, 130, 140);
		bt_leaderboard = new Rectangle(bt_leaderboard_X, bt_leaderboard_Y, 130, 140);

		animationSetting = new Animation(img_bt_setting, 80, false);
	}

	// cập nhật game sau mỗi chu kì
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		System.out.println(checkAnimation);
		// Thực hiện animation
		if (checkAnimation != 0 && animation != null) {
			animation.update(delta);
			if (animation.getFrame() == animation.getFrameCount() - 1) {
				if (checkAnimation == 1) {
					sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());

				} else if (checkAnimation == 2) {
					sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
				}
				checkAnimation = 0;
			}
		}

		// chuyển sang setting
		if ((bt_setting.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_setting
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			animation = animationSetting;
			checkAnimation = 1;
		}

		// chuyển sang leaderboard
		if ((bt_leaderboard
				.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_leaderboard
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			checkAnimation = 2;
		}

		// bắt đầu chơi
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
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
		if (animation != null) {
			if (checkAnimation != 0) {
				animation.draw(bt_setting_X, bt_setting_Y);

			} else {
//				animation.setCurrentFrame(0);
//				animation.draw(bt_setting_X, bt_setting_Y);
				animation.getCurrentFrame().draw(bt_setting_X, bt_setting_Y);
			}
		}

	}

	// lấy id trạng thái
	@Override
	public int getID() {
		return 0;
	}

}
