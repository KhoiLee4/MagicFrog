package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class SoundEffect implements gameConfig {
	// Âm thanh nhấn
	private Sound soundClick;

	// Âm thanh lên cấp (ăn)
	private Sound soundLevelUp;

	// Trạng thái của âm thanh
	private static boolean checkSound = Sound;

	// Khởi tạo
	public SoundEffect() throws SlickException {
		soundClick = new Sound("Data/Sound/Click2.ogg");
		soundLevelUp = new Sound("Data/Sound/LevelUp1.ogg");
	}

	// Phát âm thanh nhấn
	public void click() {
		if (checkSound) {
			soundClick.play();
		}
	}

	// Lấy trạng thái âm thanh
	public static boolean isCheckSound() {
		return checkSound;
	}

	// Phát âm thanh lên cấp (ăn)
	public void levelUp() {
		if (checkSound) {
			soundLevelUp.play();
		}
	}

	// Bật âm thanh
	public void turnOn() {
		checkSound = true;
	}

	// Tắt âm thanh
	public void turnOff() {
		checkSound = false;
	}
}

//LƯU Ý
//điều chỉnh cho biến trạng thái thêm tổng quát 
