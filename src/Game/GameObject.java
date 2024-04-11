package Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

// Lớp trừu tượng cho các đối tượng 
public abstract class GameObject implements gameConfig {
	// Hình ảnh của đối tượng
	protected Image img;
	// Vị trí cảu nhân vật (góc trái trên)
	protected float pos_x;
	protected float pos_y;
	// Viền va chạm
	protected Shape hitbox;
	// Hướng di chuyển
	protected int direction;
	// Tốc độ của đối tượng
	protected float speed;

	// Khởi tạo
	protected GameObject(Image img, float pos_x, float pos_y, Shape hitbox) {
		this.img = img;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.hitbox = hitbox;
		this.direction = 0;
		this.speed = 0;
	}
	
	public Shape getHitbox() {
		return hitbox;
	}

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}

	// Vẽ đối tượng
	public void render() {
		if (this.img != null) {
			this.img.draw(pos_x, pos_y);
		}
		if (this.hitbox != null) {
			PlayGame.gameContainer.getGraphics().setColor(Color.black);
			PlayGame.gameContainer.getGraphics().draw(this.hitbox);
		}
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getPos_x() {
		return pos_x;
	}

	public void setPos_x(float pos_x) {
		this.pos_x = pos_x;
	}

	public float getPos_y() {
		return pos_y;
	}

	public void setPos_y(float pos_y) {
		this.pos_y = pos_y;
	}

	
}
