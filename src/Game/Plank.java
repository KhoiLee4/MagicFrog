package Game;

import java.awt.Shape;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Plank extends GameObject {
	// Khởi tạo
	protected Plank(float x, float y, String url, int direction) throws SlickException {
		super(null, x, y, null);

		this.direction = direction;

		this.img = new Image(url);

		this.speed = speedPlank * 0.5f;

		// Đặt lại vị trí cho phù hợp với từng hình
		if (direction == 1) {
			this.pos_x = x - this.img.getWidth();
		}

		// Tạo hitbox
		this.hitbox = new Rectangle(this.pos_x, this.pos_y, this.img.getWidth(), this.img.getHeight() - 20);
	}

	// Cập nhật
	public void update(int delta, int check) throws SlickException {
		move(delta, check);
	}

	public void update2(float distance) {
		move2(distance);
	}

	// Di chuyển ván (1: sang phải, -1: sang trái)
	private void move(int delta, int check) throws SlickException {
		this.pos_x += this.speed * delta * this.direction;
		this.hitbox.setX(this.pos_x);

		if (PlayGame.gameContainer.getInput().isKeyDown(Input.KEY_UP) && (check == 1 || check == 3 || check == 4)) {
			this.pos_y += speedMap * delta;
			this.hitbox.setY(this.pos_y);
		}
	}

	private void move2(float distance) {
		this.pos_y -= distance;
		this.hitbox.setY(this.hitbox.getY() - distance);
	}

	// Kiểm tra vị trí (ra ngoài thì trả về true)
	public boolean checkLocation() {
		return (this.direction == 1 && this.pos_x > screenWidth)
				|| (this.direction == -1 && this.pos_x < -this.img.getWidth());
	}

	// kiểm tra các phần tử đã vào màn hình chưa
	public static boolean checkOnScreen(ArrayList<Plank> planks) {
		for (Plank p : planks) {
			if (p.pos_x < 0 || p.pos_x > 945 - p.img.getWidth()) {
				return false;
			}
		}
		return true;
	}
}

// LƯU Ý 
// chỉnh lại các biến tổng quát