package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Frog extends GameObject implements gameConfig {

	protected Frog() throws SlickException {
		super(null, screenWidth / 2, screenHeight - 100);

		this.img = new Image("C:/Users/ADMIN/Desktop/MagicFrog/Data/Image/Frog.png");

	}

	// cập nhật trạng thái nhân vật
	public void update() throws SlickException {

	}

	// bắt sự kiện di chuyển của nhân vật
	private void move(int speed) throws SlickException {

	}

}
