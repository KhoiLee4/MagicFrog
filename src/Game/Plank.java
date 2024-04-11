package Game;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

// Đối tượng tấm ván gỗ
public class Plank extends GameObject {

	protected Plank(float x, float y, String url, int direction) throws SlickException {
		super(null, x, y, null);

		this.direction = direction;

		this.img = new Image(url);

		this.speed = speedPlank;

		// đặt lại vị trí cho phù hợp với từng hình
		if (direction == 1) {
			this.pos_x = x - this.img.getWidth();
		}

		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2, this.pos_y - this.img.getHeight() / 2,
				this.img.getWidth(), this.img.getHeight());

	}

	// Cập nhật trạng thái của tấm ván
	public void update(int delta,int check) throws SlickException {
		move(delta,check);
	}
	
	

	// Sự kiện di chuyển của tấm ván (1: sang phải, -1: sang trái)
	private void move(int delta,int check) throws SlickException {
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
	public static boolean checkOnScreen(ArrayList<Plank> planks) {
		for (Plank p : planks) {
			if (p.pos_x < 0 || p.pos_x > 945 - p.img.getWidth()) {
				return false;
			}
		}
		return true;
	}
}
