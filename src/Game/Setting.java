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

import GameData.Detail;
import GameData.DetailDAO;

public class Setting extends BasicGameState implements gameConfig {
	
	// Nhạc nền, âm thanh hiệu ứng
	private GameMusic music;
	private SoundEffect sound;

	// Hình nền
	private Image img_background = null;
	// Hình các nút
	private Image[] img_bt_sound;
	private Image[] img_bt_music;
	private Image img_bt_back = null;

	// Hitbox các nút
	private Rectangle bt_sound = null;
	private Rectangle bt_music = null;
	private Rectangle bt_back = null;

	// Tọa độ các nút
	private int bt_sound_X = 465;
	private int bt_sound_Y = 540;

	private int bt_music_X = 465;
	private int bt_music_Y = 700;

	private int bt_back_X = 900;
	private int bt_back_Y = 840;

	// Trạng thái nút
	private int indexSound = Sound ? 0 : 1;
	private int indexMusic = Music ? 0 : 1;

	// Từ đâu đến
	static boolean isMenu = true;
	
	Detail acc_detail = null;
	// Khởi tạo
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo nhạc nền, âm thanh hiệu ứng
		music = new GameMusic();
		sound = new SoundEffect();


		// Tạo hình ảnh
		img_background = new Image("Data/Image/Setting.png");
		img_bt_sound = new Image[] { new Image("Data/Image/Button_Sound_on.png"),
				new Image("Data/Image/Button_Sound_off.png") };
		img_bt_music = new Image[] { new Image("Data/Image/Button_Music_on.png"),
				new Image("Data/Image/Button_Music_off.png") };
		img_bt_back = new Image("Data/Image/Button_Back.png");

		// Tạo hitbox
		bt_sound = new Rectangle(bt_sound_X, bt_sound_Y, 130, 140);
		bt_music = new Rectangle(bt_music_X, bt_music_Y, 130, 140);
		bt_back = new Rectangle(bt_back_X, bt_back_Y, 130, 140);
	}

	// Cập nhật
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		
		
		
		// Bật/tắt âm thanh hiệu ứng
		if ((bt_sound.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_sound
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			if (SoundEffect.isCheckSound()) {
				sound.turnOff();
				acc_detail.setSoundEffect(false);
				indexSound = 1;
			} else {
				sound.turnOn();
				acc_detail.setSoundEffect(true);
				indexSound = 0;
			}
		}

		// Bật/tắt nhạc nền
		if ((bt_music.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_music
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();

			if (GameMusic.isCheckMusic()) {
				music.stopMusic();
				acc_detail.setGameMusic(false);
				indexMusic = 1;
			} else {
				music.playMusic();
				acc_detail.setGameMusic(true);
				indexMusic = 0;
			}
		}
		
		DetailDAO.getInstance().update(acc_detail);

		// Quay lại
		if ((bt_back.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_back
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			if (isMenu) {
				sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
			} else {
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
		g.setColor(Color.transparent);
		g.draw(bt_sound);
		g.draw(bt_music);
		g.draw(bt_back);
		
		acc_detail = SignIn.acc_detail;
		if(acc_detail.isGameMusic()) {
			indexMusic = 0;
		}else {
			indexMusic = 1;
		}
		
		if(acc_detail.isSoundEffect()) {
			indexSound = 0;
		} else {
			indexSound = 1;
		}
		
		// Vẽ nút
		img_bt_sound[indexSound].draw(bt_sound_X, bt_sound_Y);
		img_bt_music[indexMusic].draw(bt_music_X, bt_music_Y);
		img_bt_back.draw(bt_back_X, bt_back_Y);
	}

	// Lấy id trạng thái
	@Override
	public int getID() {
		return 2;
	}
}

// LƯU Ý
// xem xét lại biến music, sound cho tổng quát và hàm lấy static
