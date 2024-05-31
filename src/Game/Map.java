package Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class Map implements gameConfig {
	// Hình nền của Map
	protected Image background = null;

	// Vị trí của Map (góc trái trên)
	protected float pos_x;
	protected float pos_y;

	// Loại Map
	protected String typeMap;

	// Khởi tạo
	protected Map(Image img, float pos_x, float pos_y, String typeMap) throws SlickException {
		this.background = img;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.typeMap = typeMap;
	}

	// Cập nhật
	public void update(int delta, int check, Frog frog) throws SlickException {
		// Di chuyển map
		this.move(delta, check, frog.getHitbox());
	}

	public void update2(int delta) throws SlickException {
		// Di chuyển map
		this.moveMap2(delta);
	}
	// Vẽ Map
	public void render() {
		this.background.draw(pos_x, pos_y);
	}

	// Kiểm tra nhân vật
	public int checkFrog(Shape hitbox) {
		return 1;
	}
 
	// Di chuyển Map
	private void move(int delta, int check, Shape hitbox) throws SlickException {
		Input input = PlayGame.gameContainer.getInput();

		// Kiểm tra cờ trạng thái của nhân vật
		// Tiến lên
		if (input.isKeyDown(Input.KEY_UP) && (check == 1 || check == 3 || check == 4)) {
			this.pos_y += speedFrog * delta;
		}
	}
	// Map movements similar to those of a frog
		private void moveMap2(int delta) throws SlickException {
				this.pos_y -= speedFrog * delta*200;		
		}

	// Kiểm tra vị trí (ra ngoài thì trả về true)
	public boolean checkLocation() {
		if (this.pos_y > screenHeight + 1000) {
			return true;
		}
		return false;
	}

	// Tính tổng chiều cao của các map
	public static float totalHeight(ArrayList<Map> map) {
		float total = 0;
		for (int i = 0; i < map.size(); i++) {
			total += map.get(i).getImage().getHeight();
		}
		return total;
	}
	
	// Lấy hình nền
	public Image getImage() {
		return this.background;
	}

	// Lấy loại của Map
	public String getTypeMap() {
		return typeMap;
	}
}

// LƯU Ý
// xem xét kiểu dữ liệu của biến loại Map
