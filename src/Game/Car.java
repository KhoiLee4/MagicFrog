package Game;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Car extends GameObject implements gameConfig {
	// Khởi tạo
	protected Car(float x, float y, String url, int direction) throws SlickException {
		super(null, x, y, null);

		this.direction = direction;

		this.speed = speedCar;

		// Đặt hình phù hợp với hướng di chuyển của xe
		if (direction == 1) {
			this.img = new Image(url);
		} else {
			this.img = new Image(url).getFlippedCopy(true, false);
		}

		// Tạo hitbox
		if (url.equals("Data/Image/Car1.png")) {
			// Create HitBox blue car
			this.hitbox = new Rectangle(this.pos_x + 10, this.pos_y + 50, this.img.getWidth() - 30,
					this.img.getHeight() - 70);

		} else if (url.equals("Data/Image/Car2.png")) {
			// Create HitBox orange car
			this.hitbox = new Rectangle(this.pos_x + 10, this.pos_y + 50, this.img.getWidth() - 30,
					this.img.getHeight() - 70);

		} else {
			// Create HitBox White car
			this.hitbox = new Rectangle(this.pos_x + 10, this.pos_y + 50, this.img.getWidth() - 30,
					this.img.getHeight() - 65);
		}
	}

	// Cập nhật
	public void update(int delta, int check) throws SlickException {
		move(delta, check);
	}

	public void update2(float distance) throws SlickException {
		move2(distance);
	}

	// Di chuyển xe (1: sang phải, -1: sang trái)
	private void move(int delta, int check) throws SlickException {
		this.pos_x += this.speed * delta * this.direction;
		this.hitbox.setX(this.hitbox.getX() + this.speed * delta * this.direction);

		if (PlayGame.gameContainer.getInput().isKeyDown(Input.KEY_UP) && (check == 1 || check == 3 || check == 4)) {
			this.pos_y += speedMap * delta;
			this.hitbox.setY(this.hitbox.getY() + speedMap * delta);
		}
	}

	private void move2(float distance) throws SlickException {
		this.pos_y -= distance;
		this.hitbox.setY(this.hitbox.getY() - distance);
	}

	// Kiểm tra vị trí (ra ngoài thì trả về true)
	public boolean checkLocation() {
		return (this.direction == 1 && this.pos_x > screenWidth)
				|| (this.direction == -1 && this.pos_x < -this.img.getWidth());
	}

	// kiểm tra các phần tử đã vào màn hình chưa
	public static boolean checkOnScreen(ArrayList<Car> cars) {
		for (Car c : cars) {
			if (c.pos_x < 0 || c.pos_x > 945 - c.img.getWidth()) {
				return false;
			}
		}
		return true;
	}
}

// LƯU Ý
// chỉnh lại tạo hitbox tránh dư thừa
