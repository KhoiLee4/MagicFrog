package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

// Đối tượng tường, nhà
public class Obstacles extends GameObject {
	// Khởi tạo
	protected Obstacles(float x, float y, String url) throws SlickException {
		super(null, x, y, null);
		this.img = new Image(url);

		// Tạo hitbox theo đối tượng
		if (url.equals("Data/Image/Obstacles5.png")) {
			this.hitbox = new Rectangle(this.pos_x + 20, this.pos_y + 3 * this.img.getHeight() / 4,
					this.img.getWidth() - 40, this.img.getHeight() / 4);
		}
		if (url.equals("Data/Image/Obstacles2.png")) {
			this.hitbox = new Rectangle(this.pos_x + 10, this.pos_y + 10, this.img.getWidth() - 20,
					this.img.getHeight() - 15);
		}
		if (url.equals("Data/Image/Obstacles1.png")) {
			this.hitbox = new Rectangle(this.pos_x + 10, this.pos_y + 10, this.img.getWidth() - 20,
					this.img.getHeight() - 20);
		}
		if (url.equals("Data/Image/Obstacles4.png")) {
			this.hitbox = new Rectangle(this.pos_x + 15, this.pos_y, this.img.getWidth() - 20,
					this.img.getHeight() - 20);
		}
		if (url.equals("Data/Image/Obstacles3.png")) {
			this.hitbox = new Rectangle(this.pos_x, this.pos_y, this.img.getWidth(), this.img.getHeight());
		}
		if (url.equals("Data/Image/Obstacles7.png")) {
			this.hitbox = new Rectangle(this.pos_x + 25, this.pos_y + 20, this.img.getWidth() - 45,
					this.img.getHeight() - 30);
		}
		if (url.equals("Data/Image/Obstacles6.png")) {
			this.hitbox = new Rectangle(this.pos_x + 20, this.pos_y + 3 * this.img.getHeight() / 4,
					this.img.getWidth() - 25, this.img.getHeight() / 4);
		}
		if (url.equals("Data/Image/Obstacles8.png")) {
			this.hitbox = new Rectangle(this.pos_x, this.pos_y, this.img.getWidth(), this.img.getHeight());
		}
		if (url.equals("Data/Image/Obstacles9.png")) {
			this.hitbox = new Rectangle(this.pos_x, this.pos_y, this.img.getWidth(), this.img.getHeight());
		}
	}

	// Cập nhật đối tượng
	public void update(int delta, int check) throws SlickException {
		if (check == 1 || check == 3 || check == 4) {
			this.move(delta);
		}
	}

	public void update2(int delta) throws SlickException {
		this.move2(delta);
	}

	// Di chuyển đối tượng
	private void move(int delta) throws SlickException {
		Input input = PlayGame.gameContainer.getInput();
		if (input.isKeyDown(Input.KEY_UP)) {
			this.pos_y += speedMap * delta;
			this.hitbox.setY(this.hitbox.getY() + speedMap * delta);
		}
	}

	private void move2(int delta) throws SlickException {
		this.pos_y -= speedMap * delta* 200;
		this.hitbox.setY(this.hitbox.getY() - speedMap * delta*200);

	}
}

// LƯU Ý
//
