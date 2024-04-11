package Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

// Lớp trừu tượng cho các đối tượng 
public abstract class GameObject implements gameConfig {
	// Hình ảnh của đối tượng
	protected Image img;

	// Vị trí của đối tượng (góc trái trên)
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

	// Vẽ đối tượng
	public void render() {
		// Vẽ hình
		if (this.img != null) {
			this.img.draw(pos_x, pos_y);
		}

		// Vẽ hitbox
		if (this.hitbox != null) {
			PlayGame.gameContainer.getGraphics().setColor(Color.black);
			PlayGame.gameContainer.getGraphics().draw(this.hitbox);
		}
	}

	// Lấy hitbox của đối tượng
	public Shape getHitbox() {
		return hitbox;
	}

	// Đặt lại hitbox
	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}

	// Lấy hướng di chuyển
	public int getDirection() {
		return direction;
	}

	// Đặt lại hướng di chuyển
	public void setDirection(int direction) {
		this.direction = direction;
	}

	// Lấy tọa độ, đặt lại tọa độ của đối tượng
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

// LƯU Ý

