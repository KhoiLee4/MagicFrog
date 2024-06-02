package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class BabyFrog extends GameObject implements gameConfig {
	// Chuyển động của nhân vật
	private Animation animation = null;
	private int speedAction = 200;
	
	// Biến tạo ngẫu nhiên
	private Random randomSpawn;

	// Khởi tạo
	protected BabyFrog(float y) throws SlickException {
		super(null, 0, 0, null);

		animation = new Animation(new SpriteSheet(new Image("Data/Image/BabyFrog.png"), 30, 29), speedAction);

		randomSpawn = new Random();

		this.pos_x = randomSpawn.nextInt(screenWidth - this.animation.getWidth());
		this.pos_y = randomSpawn.nextInt(622 - this.animation.getHeight()) + y;

		// Tạo hitbox
		this.hitbox = new Rectangle(this.pos_x, this.pos_y, 30, 29);	
		this.hitbox.setLocation(this.pos_x, this.pos_y);
	
	}

	public Animation getAnimation() {
		return animation;
	}


	// Cập nhật
	public void update(int delta, int check) throws SlickException {
		// Kiểm tra trạng thái chuyển động của nhân vật
		animation.update(delta);
		move(delta, check);
	}

	public void update2(int delta) throws SlickException {
		// Kiểm tra trạng thái chuyển động của nhân vật
		animation.update(delta);
		move2(delta);
	}

	// bắt sự kiện di chuyển của ếch
	private void move(int delta, int flag) throws SlickException {
		Input input = PlayGame.gameContainer.getInput();
		if (input.isKeyDown(Input.KEY_UP) && (flag == 1 || flag == 3 || flag == 4)) {
			this.pos_y += speedFrog * delta;
			this.hitbox.setY(this.pos_y);
		}
	}

	private void move2(int delta) throws SlickException {

		this.pos_y -= speedFrog * delta*100;
		this.hitbox.setY(this.pos_y);

	}

	// Vẽ nhân vật
	@Override
	public void render() {
		// Chạy chuyển động nhân vật
		animation.draw(this.pos_x, this.pos_y);

		// Vẽ hitbox
		PlayGame.gameContainer.getGraphics().setColor(Color.black);
		PlayGame.gameContainer.getGraphics().draw(this.hitbox);
	}

	
}

// LƯU Ý
// Chưa dừng chuyển động khi pause
