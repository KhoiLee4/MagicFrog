package Controller;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import View.PlayGame;

public class Fruit extends GameObject implements gameConfig {
	// Biến tạo ngẫu nhiên
	private Random randomSpawn;

	// Trái cây đã bị ăn chưa
	private boolean isEat;
	// Khởi tạo

	protected Fruit(float y) throws SlickException {
		super(null, 0, 0, null);
		isEat = false;

		randomSpawn = new Random();

		this.img = new Image("Data/Image/Fruit" + randomSpawn.nextInt(4) + ".png");
//		this.img = new Image("Data/Image/Fruit0.png");

		this.pos_x = randomSpawn.nextInt(screenWidth - this.img.getWidth());
		this.pos_y = randomSpawn.nextInt(622 - this.img.getHeight() - 5) + y;

		// Tạo hitbox
		this.hitbox = new Rectangle(this.pos_x, this.pos_y, this.img.getWidth(), this.img.getHeight());
		this.hitbox.setLocation(this.pos_x, this.pos_y);

	}

	// Cập nhật
	public void update(int delta, int check) throws SlickException {
		// Kiểm tra trạng thái chuyển động của nhân vật
		move(delta, check);
	}

	public void update2(float distance) throws SlickException {
		move2(distance);
	}

	// bắt sự kiện di chuyển của ếch
	private void move(int delta, int flag) throws SlickException {
		Input input = PlayGame.gameContainer.getInput();
		if (input.isKeyDown(Input.KEY_UP) && (flag == 1 || flag == 3 || flag == 4)) {
			this.pos_y += speedFrog * delta;
			this.hitbox.setY(this.pos_y);
		}
	}

	private void move2(float distance) throws SlickException {
		this.pos_y -= distance;
		this.hitbox.setY(this.pos_y);
	}

	// Vẽ trái cây
	@Override
	public void render() {
		if (!isEat) {
			this.img.draw(pos_x, pos_y);
//			PlayGame.gameContainer.getGraphics().setColor(Color.black);
//			PlayGame.gameContainer.getGraphics().draw(this.hitbox);
		}
	}

	// Lấy biến kiểm tra
	public boolean getIsEat() {
		return isEat;
	}

	// Trái cây đã bị ăn
	public void Eat() {
		isEat = true;
	}
}

// LƯU Ý
// Bị đè hinh tọa độ ở ngoài map
