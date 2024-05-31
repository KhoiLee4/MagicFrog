package Game;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class GameMusic implements gameConfig {
	// Nhạc nền
	private Music backgroundMusic;

	// Trạng thái nhạc
	private static volatile boolean checkMusic = Music;

	// Khởi tạo
	public GameMusic() throws SlickException {
		backgroundMusic = new Music("Data/Sound/Background_Music.ogg");
		
	}

	// Lấy trạng thái nhạc
	public static boolean isCheckMusic() {
		return checkMusic;
	}

	// Phát nhạc
	public synchronized void playMusic() {
		checkMusic = true;
		backgroundMusic.loop();
	}

	// Dừng nhạc
	public void stopMusic() {
		if (backgroundMusic.playing()) {
			backgroundMusic.stop();
			System.out.println("Music stopped");
		}
		checkMusic = false;
	}

	
	public void setMusic(boolean isTurn) {
		checkMusic = isTurn;
		if (checkMusic) {
			backgroundMusic.loop();
		}else {
			backgroundMusic.stop();
		}
	}
}
