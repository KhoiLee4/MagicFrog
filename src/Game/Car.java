package Game;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Car extends GameObject implements gameConfig {

	protected Car(float x, float y, String url, int direction) throws SlickException {
		super(null, x, y, null);

		this.direction = direction;

		this.speed = speedCar;

		if (direction == 1) {
			this.img = new Image(url);
		} else {
			this.img = new Image(url).getFlippedCopy(true, false);
		}

		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2, this.pos_y - this.img.getHeight() / 2,
				this.img.getWidth(), this.img.getHeight());

	}

	// Cập nhật trạng thái của xe
	public void update(int delta, int check) throws SlickException {
		move(delta ,check);
	}

	// Sự kiện di chuyển của xe (1: sang phải, -1: sang trái
	private void move(int delta, int check) throws SlickException {
		this.pos_x += this.speed * delta * this.direction;
		if (PlayGame.gameContainer.getInput().isKeyDown(Input.KEY_UP) && check == 1) {
			this.pos_y += speedFrog * delta;
		}
	}
	// Kiểm tra vị trí (ra ngoài thì trả về true)
	public boolean checkLocation() {
		return (this.direction == 1 && this.pos_x > screenWidth)
				|| (this.direction == -1 && this.pos_x < -this.img.getWidth());
	}

	// kiểm tra các phần tử đã vào màn hình hết chưa
	public static boolean checkOnScreen(ArrayList<Car> cars) {
		for (Car c : cars) {
			if (c.pos_x < 0 || c.pos_x > 945 - c.img.getWidth()) {
				return false;
			}
		}
		return true;
	}
}
