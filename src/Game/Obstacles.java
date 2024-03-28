package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

// Đối tượng tường, nhà
public class Obstacles extends GameObject {
	protected Obstacles(float x, float y, String url) throws SlickException {
		super(null, x, y, null);

		this.img = new Image(url);

		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2, this.pos_y - this.img.getHeight() / 2,
				this.img.getWidth(), this.img.getHeight());

	}

	// cập nhật trạng thái của ếch
	public void update(int delta) throws SlickException {
		float speed = 0.2f;
		move(speed, delta);
	}

	// bắt sự kiện di chuyển của ếch
	private void move(float speed, int delta) throws SlickException {
		Input input = PlayGame.gameContainer.getInput();
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			this.pos_x += speed * delta;
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			this.pos_x -= speed * delta;
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			this.pos_y -= speed * delta;
		}

	}
}
