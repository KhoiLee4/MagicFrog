package Controller;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import View.PlayGame;

// Đối tượng ếch
public class Frog extends GameObject implements gameConfig {
	// Chuyển động của nhân vật
	private Animation jump = null;
	private Animation left = null;
	private Animation right = null;
	private Animation animation = null;
	private int speedAction = 200;
	private boolean animationRunning = false;

	// Mạng của nhân vật
	private boolean alive = true;

	// Khởi tạo
	public Frog(int type) throws SlickException {
		super(null, screenWidth / 2, screenHeight - 100, null);

		// Đặt tốc độ di chuyển
		this.speed = speedFrog;

		// Đặt ảnh
		this.img = new Image("Data/Image/Frog" + type + ".png");

		// Tạo hitbox
		this.hitbox = new Rectangle(this.pos_x + 30, this.pos_y + 40, this.img.getWidth() - 55,
				this.img.getHeight() - 65);

		// Tạo chuyển động
		animation = new Animation(new SpriteSheet(this.img, 120, 122), speedAction);

		jump = new Animation(new SpriteSheet(new Image("Data/Image/MoveUp_Skin" + type + ".png"), 120, 122),
				speedAction);
		left = new Animation(new SpriteSheet(new Image("Data/Image/MoveLeft_Skin" + type + ".png"), 120, 122),
				speedAction);
		right = new Animation(
				new SpriteSheet(new Image("Data/Image/MoveLeft_Skin" + type + ".png").getFlippedCopy(true, false), 120,
						122),
				speedAction);

		jump.setLooping(false);
		left.setLooping(false);
		right.setLooping(false);
	}

	// Cập nhật
	public void update(int delta, int check) throws SlickException {
		// Kiểm tra trạng thái chuyển động của nhân vật
		if (animationRunning) {
			animation.update(delta);
			if (animation.isStopped()) {
				animationRunning = false;
				animation.restart();
			}
		}

		// Kiểm tra vị trí của nhân vật
		if (this.pos_y < screenHeight * 2 / 3) {
			this.pos_y = screenHeight * 2 / 3;
			this.hitbox.setY(screenHeight * 2 / 3 + 40);
		} else if (this.pos_x < 0) {
			this.pos_x = 0;
			this.hitbox.setX(this.pos_x + 30);
		} else if (this.pos_x > screenWidth - this.img.getWidth()) {
			this.pos_x = screenWidth - this.img.getWidth();
			this.hitbox.setX(screenWidth - this.img.getWidth() + 30);
		}
		if(check == -2) {
			return;
		}
		// Di chuyển nhân vật
		// 2 if đầu sử dụng khi nhân vật đứng trên ván
		if (check == -1) {
			moveLeft(delta);
		} else if (check == 0) {
			moveRight(delta);
		} else {
			move(delta, check);
		}
	}

	// Tu dong di chuyen ben trai
	private void moveLeft(int delta) {
		this.pos_x -= this.speed * delta * 0.5f;
		this.hitbox.setX(this.hitbox.getX() - this.speed * delta * 0.5f);
	}

	// Tu dong di chuyen ben phai
	private void moveRight(int delta) {
		this.pos_x += this.speed * delta * 0.5f;
		this.hitbox.setX(this.hitbox.getX() + this.speed * delta * 0.5f);
	}

	// bắt sự kiện di chuyển của ếch
	private void move(int delta, int flag) throws SlickException {
		// flag = 1 => move
		// flag = 2 => cham chieu rong cua hinh chu nhat
		// flag = 3 => cham chieu dai ben phai cua hinh nhat
		// flag = 4 => cham chieu dai ben trai cua hinh nhat
		// flag = 5 => cham 2 diem dac biet cua hcn
		Input input = PlayGame.gameContainer.getInput();
		if (input.isKeyDown(Input.KEY_RIGHT) && input.isKeyDown(Input.KEY_UP)) {
			return;
		}
		if (input.isKeyDown(Input.KEY_LEFT) && input.isKeyDown(Input.KEY_UP)) {
			return;
		}
		if (input.isKeyDown(Input.KEY_LEFT) && input.isKeyDown(Input.KEY_RIGHT)) {
			return;
		}
		
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
				this.pos_y -= this.speed * delta;
				this.hitbox.setY(this.hitbox.getY() - this.speed * delta);
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

	// Vẽ nhân vật
	@Override
	public void render() {
		// Chạy chuyển động nhân vật
		if (animationRunning) {
			animation.draw(pos_x, pos_y);
		} else {
			Input input = PlayGame.gameContainer.getInput();
			// Qua phải
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				animation = right;
				animation.start();
				animationRunning = true;
			}
			// Qua trái
			else if (input.isKeyDown(Input.KEY_LEFT)) {
				animation = left;
				animationRunning = true;
			}
			// Tiến lên
			else if (input.isKeyDown(Input.KEY_UP)) {
				animation = jump;
				animationRunning = true;
			} else {
				this.img.draw(pos_x, pos_y);
			}
		}

		// Vẽ hitbox
//		PlayGame.gameContainer.getGraphics().setColor(Color.black);
//		PlayGame.gameContainer.getGraphics().draw(this.hitbox);
	}

	public void render(boolean isPause) {
		if (isPause) {
			super.render();
		} else {
			render();
		}
	}

	// Kiểm tra nhân vật còn sống hay chết
	public boolean isAlive() {
		return alive;
	}

	// Đặt lại mạng cho nhân vật
	private void setAlive(boolean alive) {
		this.alive = alive;
	}

	// Frog is dead
	public void deathFrog() {
		setAlive(false);
	}

	// Function solve logic of item
	// Bottle HP, getter more life
	public void useItem() {
		setAlive(true);
	}
}

// LƯU Ý
// tinh gọn lại hàm move (kẹp cờ kiểm tra vào if)
