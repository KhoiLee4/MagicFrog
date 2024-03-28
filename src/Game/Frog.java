package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

// Đối tượng ếch
public class Frog extends GameObject implements gameConfig {

	// khởi tạo
	protected Frog() throws SlickException {
		super(null, screenWidth / 2, screenHeight - 100, null);

		this.speed = speedFrog;

		this.img = new Image("Data/Image/Frog.png");

		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2, this.pos_y - this.img.getHeight() / 2,
				this.img.getWidth(), this.img.getHeight());

	}

	// cập nhật trạng thái của ếch
	public void update(int delta) throws SlickException {
		move(delta);
	}

	// bắt sự kiện di chuyển của ếch
	private void move(int delta) throws SlickException {
		if (this.pos_y < screenHeight * 2 / 3) {
			this.pos_y = screenHeight * 2 / 3;
		} else if (this.pos_x < 0) {
			this.pos_x = 0;
			System.out.println(1);
		} else if (this.pos_x > screenWidth - this.img.getWidth()) {
			this.pos_x = screenWidth - this.img.getWidth();
			System.out.println(2);
		} else {
			Input input = PlayGame.gameContainer.getInput();
			// Qua phải
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				this.pos_x += this.speed * delta;
			}
			// Qua trái
			else if (input.isKeyDown(Input.KEY_LEFT)) {
				this.pos_x -= this.speed * delta;
			}
			// Tiến lên
			else if (input.isKeyDown(Input.KEY_UP)) {
				this.pos_y -= this.speed * delta;
			}
		}
	}
}
