package Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class Map implements gameConfig {

	protected Image background = null;
	protected float pos_x;
	protected float pos_y;
	protected String typeMap;
	protected ArrayList<GameObject> obstacles;
	
	// khai báo
	protected Map(Image img, float pos_x, float pos_y, String typeMap) throws SlickException {
		this.background = img;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.typeMap = typeMap;
	}

	// cập nhật map
	public void update(int delta, int check, Shape hitbox) throws SlickException {
		// Di chuyển map	
			this.move(delta, check, hitbox);


	}

	// vẽ map
	public void render() {
		this.background.draw(pos_x, pos_y);
	}

	public int checkFrog(Shape hitbox) {
		return 1;
	}

	// bắt sự kiện di chuyển của map
	private void move(int delta, int check, Shape hitbox) throws SlickException {
		Input input = PlayGame.gameContainer.getInput();
		// Tiến lên
		if (input.isKeyDown(Input.KEY_UP) && (check == 1)) {
			this.pos_y += speedFrog * delta;
			
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

	public String getTypeMap() {
		return typeMap;
	}
	
}
