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

public class Setting extends BasicGameState implements gameConfig {
	// Nhạc nền
	public GameMusic music;
	public SoundEffect sound;

	private Image img_background = null;
	private Image[] img_bt_sound;
	private Image[] img_bt_music;
	private Image img_bt_back = null;

	private Rectangle bt_sound = null;
	private Rectangle bt_music = null;
	private Rectangle bt_back = null;

	private int bt_sound_X = 465;
	private int bt_sound_Y = 540;

	private int bt_music_X = 465;
	private int bt_music_Y = 700;

	private int bt_back_X = 900;
	private int bt_back_Y = 840;

	int indexSound = Sound ? 0 : 1;
	int indexMusic = Music ? 0 : 1;

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		music = new GameMusic();
		sound = new SoundEffect();

		img_background = new Image("Data/Image/Setting.png");
		img_bt_sound = new Image[] { new Image("Data/Image/Button_Sound_on.png"),
				new Image("Data/Image/Button_Sound_off.png") };
		img_bt_music = new Image[] { new Image("Data/Image/Button_Music_on.png"),
				new Image("Data/Image/Button_Music_off.png") };
		img_bt_back = new Image("Data/Image/Button_Back.png");

		bt_sound = new Rectangle(bt_sound_X, bt_sound_Y, 130, 140);
		bt_music = new Rectangle(bt_music_X, bt_music_Y, 130, 140);
		bt_back = new Rectangle(bt_back_X, bt_back_Y, 130, 140);

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		// chuyển sang setting

		if ((bt_sound.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_sound
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			if (sound.isCheckSound()) {
				sound.turnOff();
				indexSound = 1;
			} else {
				sound.turnOn();
				indexSound = 0;
			}
//			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}

		// chuyển sang leaderboard
		if ((bt_music.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_music
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			if (music.isCheckMusic()) {
				music.stopMusic();
				indexMusic = 1;
			} else {
				music.playMusic();
				indexMusic = 0;
			}
		}

		// chuyển sang leaderboard
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
		g.setColor(Color.blue);
		g.drawString("Setting", 100, 100);
		g.setColor(Color.transparent);

		g.draw(bt_sound);
		g.draw(bt_music);
		g.draw(bt_back);

		img_background.draw();
		img_bt_sound[indexSound].draw(bt_sound_X, bt_sound_Y);
		img_bt_music[indexMusic].draw(bt_music_X, bt_music_Y);
		img_bt_back.draw(bt_back_X, bt_back_Y);

	}

	@Override
	public int getID() {
		return 2;
	}

}
