package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class SoundEffect implements gameConfig {
	private Sound soundClick;
	private Sound soundLevelUp;
	private static boolean checkSound = Sound;

	public SoundEffect() throws SlickException {
		soundClick = new Sound("Data/Sound/Click2.ogg");
		soundLevelUp = new Sound("Data/Sound/LevelUp1.ogg");
	}

	public void click() {
		if (checkSound) {
			soundClick.play();
		}
	}

	public static boolean isCheckSound() {
		return checkSound;
	}

	public void levelUp() {
		if (checkSound) {
			soundLevelUp.play();
		}
	}

	public void turnOn() {
		checkSound = true;
	}
	
	public void turnOff() {
		checkSound = false;
	}
}
