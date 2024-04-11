package Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

// Đối tượng ếch
public class Frog extends GameObject implements gameConfig {

	Animation jump = null;
	Animation left = null;
	Animation right = null;
	Animation animation = null;
	int speedAction = 200;
	boolean animationRunning = false;

	// khởi tạo
	protected Frog() throws SlickException {
		super(null, screenWidth / 2, screenHeight - 100, null);

		this.speed = speedFrog;

		this.img = new Image("Data/Image/Frog.png");

		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2 + 40,
				this.pos_y - this.img.getHeight() / 2 + 45, this.img.getWidth() - 10, this.img.getHeight() - 20);

		animation = new Animation(new SpriteSheet(this.img, 120, 101), speedAction);

		jump = new Animation(new SpriteSheet(new Image("Data/Image/MoveUp.png"), 120, 101), speedAction);
		left = new Animation(new SpriteSheet(new Image("Data/Image/MoveLeft.png"), 120, 101), speedAction);
		right = new Animation(
				new SpriteSheet(new Image("Data/Image/MoveLeft.png").getFlippedCopy(true, false), 120, 101),
				speedAction);

		jump.setLooping(false);
		left.setLooping(false);
		right.setLooping(false);
	}

	// cập nhật trạng thái của ếch

	public void update(int delta, int check) throws SlickException {
		if (animationRunning) {
			animation.update(delta);
			if (animation.isStopped()) {
				animationRunning = false;
				animation.restart();
			}
		}
		move(delta, check);

	}

	// bắt sự kiện di chuyển của ếch
	private void move(int delta, int flag) throws SlickException {
		if (this.pos_y < screenHeight * 2 / 3) {
			this.pos_y = screenHeight * 2 / 3;
			this.hitbox.setY(screenHeight * 2 / 3);

		} else if (this.pos_x < 0) {
			this.pos_x = 0;
			this.hitbox.setX(0);
			// System.out.println(1);
		} else if (this.pos_x > screenWidth - this.img.getWidth()) {
			this.pos_x = screenWidth - this.img.getWidth();
			this.hitbox.setX(screenWidth - this.img.getWidth());

			// System.out.println(2);
		}
		// flag = 1 => move
		// flag = 2 => cham chieu rong cua hinh chu nhat
		// flag = 3 => cham chieu dai ben phai cua hinh nhat
		// flag = 4 => cham chieu dai ben trai cua hinh nhat
		// flag = 5 => cham 2 diem dac biet cua hcn
		Input input = PlayGame.gameContainer.getInput();
		if (flag == 1) {
			// Qua phải
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				this.pos_x += this.speed * delta;
				this.hitbox.setX(this.hitbox.getX() + this.speed * delta);
			}
			// Qua trái
			if (input.isKeyDown(Input.KEY_LEFT)) {
				this.pos_x -= this.speed * delta;
				this.hitbox.setX(this.hitbox.getX() - this.speed * delta);
			}
			// Tiến lên
			if (input.isKeyDown(Input.KEY_UP)) {
				this.pos_y -= this.speed * delta;
				this.hitbox.setY(this.hitbox.getY() - this.speed * delta);

			}
		} else if (flag == 2) {
			// Qua phải
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				this.pos_x += this.speed * delta;
				this.hitbox.setX(this.hitbox.getX() + this.speed * delta);
			}
			// Qua trái
			if (input.isKeyDown(Input.KEY_LEFT)) {

				this.pos_x -= this.speed * delta;
				this.hitbox.setX(this.hitbox.getX() - this.speed * delta);
			}

		} else if (flag == 3) {
			// Qua phải
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				this.pos_x += this.speed * delta;
				this.hitbox.setX(this.hitbox.getX() + this.speed * delta);
			}
			// Tiến lên
			if (input.isKeyDown(Input.KEY_UP)) {
				this.pos_y -= this.speed * delta;
				this.hitbox.setY(this.hitbox.getY() - this.speed * delta);

			}
		} else if (flag == 4) {
			// Qua trái
			if (input.isKeyDown(Input.KEY_LEFT)) {
				this.pos_x -= this.speed * delta;
				this.hitbox.setX(this.hitbox.getX() - this.speed * delta);
			}
			// Tiến lên
			if (input.isKeyDown(Input.KEY_UP)) {
				System.out.println("Before: " + this.pos_y + " After: " + this.pos_x);
				this.pos_y -= this.speed * delta;
				this.hitbox.setY(this.hitbox.getY() - this.speed * delta);
				System.out.println("Len tren trong flag = 4");
			}
		} else {
			// Qua phải
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				this.pos_x += this.speed * delta;
				this.hitbox.setX(this.hitbox.getX() + this.speed * delta);
			}
			// Qua trái
			if (input.isKeyDown(Input.KEY_LEFT)) {
				this.pos_x -= this.speed * delta;
				this.hitbox.setX(this.hitbox.getX() - this.speed * delta);
			}

		}
	}

	@Override
	public void render() {
		if (animationRunning) {
			animation.draw(pos_x, pos_y);
		} else {
			Input input = PlayGame.gameContainer.getInput();
			// Qua phải
			if (input.isKeyPressed(Input.KEY_RIGHT)) {
				animation = right;
				animation.start();
				animationRunning = true;
			}
			// Qua trái
			else if (input.isKeyPressed(Input.KEY_LEFT)) {
				animation = left;
				animationRunning = true;
			}
			// Tiến lên
			else if (input.isKeyPressed(Input.KEY_UP)) {
				animation = jump;
				animationRunning = true;
			} else {
				this.img.draw(pos_x, pos_y);
			}

		}

	}

}
