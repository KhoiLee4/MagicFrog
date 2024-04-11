package Game;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class GameMusic implements gameConfig {
	// Nhạc nền
	private Music backgroundMusic;

	// Trạng thái nhạc
	private static boolean checkMusic = Music;

	// Khởi tạo
	public GameMusic() throws SlickException {
		backgroundMusic = new Music("Data/Sound/Background_Music.ogg");
	}

	// Lấy trạng thái nhạc
	public static boolean isCheckMusic() {
		return checkMusic;
	}

	// Phát nhạc
	public void playMusic() {
		checkMusic = true;
		backgroundMusic.loop();
	}

	// Dừng nhạc
	public void stopMusic() {
		checkMusic = false;
		if (backgroundMusic.playing()) {
			backgroundMusic.stop();
		}
	}
}

// LƯU Ý
// điều chỉnh cho biến trạng thái thêm tổng quát 
