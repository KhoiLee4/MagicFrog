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
	Animation animation = null;
	int speedAction = 200;
	// Biến tạo ngẫu nhiên
	private Random randomSpawn;

	// Khởi tạo
	protected BabyFrog(float y, ArrayList<Obstacles> obstacles) throws SlickException {
		super(null, 0, 0, null);

		animation = new Animation(new SpriteSheet(new Image("Data/Image/BabyFrog.png"), 30, 29), speedAction);

		randomSpawn = new Random();

		this.pos_x = randomSpawn.nextInt(screenWidth - this.animation.getWidth());
		this.pos_y = randomSpawn.nextInt(622 - this.animation.getHeight()) + y;

		// Tạo hitbox
		this.hitbox = new Rectangle(this.pos_x, this.pos_y, 30, 29);

		// Đổi tọa độ nếu trùng vật
		for (Obstacles obstacle : obstacles) {
			if (obstacle.getHitbox().intersects(this.hitbox) || obstacle.getHitbox().contains(this.hitbox)) {
				if (randomSpawn.nextBoolean()) {
					if (randomSpawn.nextBoolean()) {
						this.setPos_y(randomSpawn.nextBoolean() ? obstacle.getPos_y() - this.animation.getHeight()
								: obstacle.getPos_y() + obstacle.getHitbox().getHeight());
					}
					this.setPos_x((randomSpawn.nextBoolean() ? obstacle.getPos_x() - this.animation.getWidth()
							: obstacle.getPos_x() + obstacle.getHitbox().getWidth()));
				} else {
					this.setPos_y(randomSpawn.nextBoolean() ? obstacle.getPos_y() - this.animation.getHeight()
							: obstacle.getPos_y() + obstacle.getHitbox().getHeight());
				}
				if (y - this.hitbox.getY() >= 0) {
					this.setPos_x(this.getPos_y() + screenWidth);

				}
				this.hitbox.setLocation(this.pos_x, this.pos_y);
				break;
			}
		}
	}

	// Cập nhật
	public void update(int delta, int check) throws SlickException {
		// Kiểm tra trạng thái chuyển động của nhân vật
		animation.update(delta);
		move(delta, check);
	}

	// bắt sự kiện di chuyển của ếch
	private void move(int delta, int flag) throws SlickException {
		Input input = PlayGame.gameContainer.getInput();
		if (input.isKeyDown(Input.KEY_UP) && (flag == 1 || flag == 3 || flag == 4)) {
			this.pos_y += speedFrog * delta;
			this.hitbox.setY(this.pos_y);
		}
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

//	public void render(boolean isPause) {
//		if (isPause) {
//			super.render();
//		} else {
//			render();
//		}
//	}
}

// LƯU Ý
// Chưa dừng chuyển động khi pause
