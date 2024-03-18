package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Car extends GameObject implements gameConfig {

	// chưa sửa
	protected Car() throws SlickException {
		super(null, screenWidth / 2, screenHeight - 100, null);

		this.img = new Image("C:/Users/ADMIN/Desktop/MagicFrog/Data/Image/Frog.png");

		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2, this.pos_y - this.img.getHeight() / 2,
				this.img.getWidth(), this.img.getHeight());

	}

	// cập nhật trạng thái xe
	public void update() throws SlickException {

	}

	// bắt sự kiện di chuyển của xe
	private void move(int speed) throws SlickException {

	}

}