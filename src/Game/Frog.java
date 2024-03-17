package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Frog extends GameObject implements gameConfig {

	protected Frog() throws SlickException {
		super(null, screenWidth / 2, screenHeight - 100, null);

		this.img = new Image("Data/Image/Frog.png");

		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2, this.pos_y - this.img.getHeight() / 2,
				this.img.getWidth(), this.img.getHeight());

	}

	// cập nhật trạng thái của ếch
	public void update() throws SlickException {

	}

	// bắt sự kiện di chuyển của ếch
	private void move(int speed) throws SlickException {

	}

}
