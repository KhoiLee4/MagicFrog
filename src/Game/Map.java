package Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Map implements gameConfig {
	protected Image background = null;
	protected float pos_x;
	protected float pos_y;

	// khai báo
	protected Map(Image img, float pos_x, float pos_y) throws SlickException {
		this.background = img;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	// cập nhật map
	public void update(int delta) throws SlickException {
		// Di chuyển map
		this.move(delta);
	}
	
	// vẽ map
	public void render() {
		this.background.draw(pos_x, pos_y);
	}

	// bắt sự kiện di chuyển của map
	private void move(int delta) throws SlickException {
		Input input = PlayGame.gameContainer.getInput();
		if (pos_x > 0) {
			pos_x = 0;
		} else if (pos_x < (screenWidth - background.getWidth())) {
			pos_x = screenWidth - background.getWidth();
		} else {
			// Qua phải
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				this.pos_x -= speedFrog * delta;
			}
			// Qua trái
			else if (input.isKeyDown(Input.KEY_LEFT)) {
				this.pos_x += speedFrog * delta;
			}
			// Tiến lên
			else if (input.isKeyDown(Input.KEY_UP)) {
				this.pos_y += speedFrog * delta;
			}
		}
	}

	// Kiểm tra vị trí (ra ngoài thì trả về true)
	public boolean checkLocation() {
		if (this.pos_y > screenHeight) {
			return true;
		}
		return false;
	}

	// trả về bachground
	public Image getImage() {
		return this.background;
	}

	// tính tổng chiều cao của các map
	public static float totalHeight(ArrayList<Map> map) {
		float total = 0;
		for (int i = 0; i < map.size(); i++) {
			total += map.get(i).getImage().getHeight();
		}
		return total;
	}
}
