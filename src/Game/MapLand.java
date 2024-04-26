package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public final class MapLand extends Map implements gameConfig {
	// Danh sách đối tượng trong Map
	private ArrayList<Obstacles> obstacles;

	// Ếch con
	private BabyFrog babyFrog;

	// Ếch con bị ăn chưa
	private boolean isEat = false;

	private Fruit fruit;

	// Biến chọn map ngẫu nhiên
	private Random randomSpawn;

	// Khởi tạo
	protected MapLand(float x) throws SlickException {
		// Tạo loại Map
		super(null, x, 0, "land");

		// Đặt hình nền, điều chỉnh vị trí
		this.background = new Image("Data/Image/MapLand1.png");
		this.pos_y = screenHeight - background.getHeight();

		randomSpawn = new Random();

		obstacles = new ArrayList<Obstacles>();
		obstacles.add(new Obstacles(this.pos_x + 13, this.pos_y + 172, "Data/Image/Obstacles3.png"));
		obstacles.add(new Obstacles(this.pos_x + 640, this.pos_y + 530, "Data/Image/Obstacles3.png"));
		obstacles.add(new Obstacles(this.pos_x + 904, this.pos_y + 508, "Data/Image/Obstacles7.png"));
		obstacles.add(new Obstacles(this.pos_x + 16, this.pos_y + 510, "Data/Image/Obstacles7.png"));
		obstacles.add(new Obstacles(this.pos_x + 9, this.pos_y + 207, "Data/Image/Obstacles1.png"));
		obstacles.add(new Obstacles(this.pos_x + 122, this.pos_y + 207, "Data/Image/Obstacles1.png"));

		obstacles.add(new Obstacles(this.pos_x + 360, this.pos_y + 460, "Data/Image/Obstacles2.png"));
		obstacles.add(new Obstacles(this.pos_x + 727, this.pos_y + 280, "Data/Image/Obstacles2.png"));
		obstacles.add(new Obstacles(this.pos_x + 331, this.pos_y + 119, "Data/Image/Obstacles2.png"));
		obstacles.add(new Obstacles(this.pos_x + 554, this.pos_y + 282, "Data/Image/Obstacles1.png"));
		obstacles.add(new Obstacles(this.pos_x + 876, this.pos_y + 451, "Data/Image/Obstacles1.png"));
		obstacles.add(new Obstacles(this.pos_x + 733, this.pos_y + 118, "Data/Image/Obstacles1.png"));

		// init tree
		obstacles.add(new Obstacles(this.pos_x + 646, this.pos_y + 14, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 896, this.pos_y + 176, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 659, this.pos_y + 443, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 243, this.pos_y + 377, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 236, this.pos_y + 27, "Data/Image/Obstacles4.png"));

		// House
		obstacles.add(new Obstacles(this.pos_x + 71, this.pos_y + 43, "Data/Image/Obstacles5.png"));

		babyFrog = new BabyFrog(this.pos_y, obstacles);
		fruit = new Fruit(this.pos_y, obstacles);

	}

	protected MapLand(float x, float y) throws SlickException {
		super(null, x, 0, "land");

		// Tạo Map ngẫu nhiên trong kho có sẵn
		randomSpawn = new Random();
		String url = "Data/Image/MapLand" + (randomSpawn.nextInt(2) + 1) + ".png";
		this.background = new Image(url);
		this.pos_y = y - this.background.getHeight();

		obstacles = new ArrayList<Obstacles>();

		if (url.equals("Data/Image/MapLand1.png")) {
			// Draw object mapLand 1
			obstacles.add(new Obstacles(this.pos_x + 13, this.pos_y + 172, "Data/Image/Obstacles3.png"));
			obstacles.add(new Obstacles(this.pos_x + 640, this.pos_y + 530, "Data/Image/Obstacles3.png"));
			obstacles.add(new Obstacles(this.pos_x + 904, this.pos_y + 508, "Data/Image/Obstacles7.png"));
			obstacles.add(new Obstacles(this.pos_x + 16, this.pos_y + 510, "Data/Image/Obstacles7.png"));
			obstacles.add(new Obstacles(this.pos_x + 5, this.pos_y + 207, "Data/Image/Obstacles1.png"));
			obstacles.add(new Obstacles(this.pos_x + 122, this.pos_y + 207, "Data/Image/Obstacles1.png"));
			obstacles.add(new Obstacles(this.pos_x + 360, this.pos_y + 460, "Data/Image/Obstacles2.png"));
			obstacles.add(new Obstacles(this.pos_x + 727, this.pos_y + 280, "Data/Image/Obstacles2.png"));
			obstacles.add(new Obstacles(this.pos_x + 331, this.pos_y + 119, "Data/Image/Obstacles2.png"));
			obstacles.add(new Obstacles(this.pos_x + 554, this.pos_y + 282, "Data/Image/Obstacles1.png"));
			obstacles.add(new Obstacles(this.pos_x + 876, this.pos_y + 451, "Data/Image/Obstacles1.png"));
			obstacles.add(new Obstacles(this.pos_x + 733, this.pos_y + 118, "Data/Image/Obstacles1.png"));
			// init tree
			obstacles.add(new Obstacles(this.pos_x + 646, this.pos_y + 14, "Data/Image/Obstacles4.png"));
			obstacles.add(new Obstacles(this.pos_x + 896, this.pos_y + 176, "Data/Image/Obstacles4.png"));
			obstacles.add(new Obstacles(this.pos_x + 659, this.pos_y + 443, "Data/Image/Obstacles4.png"));
			obstacles.add(new Obstacles(this.pos_x + 243, this.pos_y + 377, "Data/Image/Obstacles4.png"));
			obstacles.add(new Obstacles(this.pos_x + 236, this.pos_y + 27, "Data/Image/Obstacles4.png"));
			// House
			obstacles.add(new Obstacles(this.pos_x + 71, this.pos_y + 43, "Data/Image/Obstacles5.png"));

		} else if (url.equals("Data/Image/MapLand2.png")) {
			// Draw mapLand 2
			obstacles.add(new Obstacles(this.pos_x + 180, this.pos_y + 25, "Data/Image/Obstacles4.png"));
			obstacles.add(new Obstacles(this.pos_x + 285, this.pos_y + 177, "Data/Image/Obstacles2.png"));
			obstacles.add(new Obstacles(this.pos_x + 612, this.pos_y + 65, "Data/Image/Obstacles1.png"));
			obstacles.add(new Obstacles(this.pos_x + 520, this.pos_y + 71, "Data/Image/Obstacles7.png"));
			obstacles.add(new Obstacles(this.pos_x + 585, this.pos_y + 139, "Data/Image/Obstacles4.png"));
			obstacles.add(new Obstacles(this.pos_x + 671, this.pos_y + 172, "Data/Image/Obstacles6.png"));
			obstacles.add(new Obstacles(this.pos_x + 642, this.pos_y + 353, "Data/Image/Obstacles8.png"));
			obstacles.add(new Obstacles(this.pos_x + 872, this.pos_y + 383, "Data/Image/Obstacles9.png"));
			obstacles.add(new Obstacles(this.pos_x + 412, this.pos_y + 560, "Data/Image/Obstacles1.png"));
			obstacles.add(new Obstacles(this.pos_x + 46, this.pos_y + 246, "Data/Image/Obstacles1.png"));
			obstacles.add(new Obstacles(this.pos_x + 6, this.pos_y + 269, "Data/Image/Obstacles3.png"));
			obstacles.add(new Obstacles(this.pos_x + 14, this.pos_y + 384, "Data/Image/Obstacles4.png"));
			obstacles.add(new Obstacles(this.pos_x + 126, this.pos_y + 479, "Data/Image/Obstacles2.png"));
			obstacles.add(new Obstacles(this.pos_x + 471, this.pos_y + 404, "Data/Image/Obstacles7.png"));
			obstacles.add(new Obstacles(this.pos_x + 703, this.pos_y + 451, "Data/Image/Obstacles4.png"));
			obstacles.add(new Obstacles(this.pos_x + 931, this.pos_y + 20, "Data/Image/Obstacles7.png"));
		}

		babyFrog = new BabyFrog(this.pos_y, obstacles);
		fruit = new Fruit(this.pos_y, obstacles);

	}

	// Cập nhật
	@Override
	public void update(int delta, int check, Frog frog) throws SlickException {
		// Kiểm tra cờ trạng thái của nhân vật
		if (check == 1 || check == 3 || check == 4) {
			// Cập nhật đối tượng trong Map
			for (Obstacles x : obstacles) {
				x.update(delta, check);
			}

			// Cập nhật ếch con
			if (!isEat) {
				babyFrog.update(delta, check);
			}

			if (!fruit.getIsEat()) {
				fruit.update(delta, check);
			}

			// Cập nhật Map
			super.update(delta, check, frog);
		}
	}

	// Vẽ Map
	@Override
	public void render() {
		super.render();
		for (Obstacles x : obstacles) {
			x.render();
		}
		if (!isEat) {
			babyFrog.render();
		}
		if (!fruit.getIsEat()) {
			fruit.render();
		}
	}

	// Kiểm tra nhân vật so với Map
	@Override
	public int checkFrog(Shape hitbox) {
		if (!isEat) {
			eatFrog(hitbox);
		}
		if (!fruit.getIsEat()) {
			eatFruit(hitbox);
		}

		for (Obstacles x : obstacles) {
			if (x.getHitbox().intersects(hitbox) || x.getHitbox().contains(hitbox)) {
				// touches the bottom edge of the rectangle => return 2
				if (hitbox.getX() >= x.getHitbox().getX() - hitbox.getWidth()
						&& hitbox.getX() < x.getHitbox().getX() + x.getHitbox().getHeight() + x.getHitbox().getWidth()
						&& hitbox.getY() < x.getHitbox().getY() + x.getHitbox().getHeight()
						&& hitbox.getY() > x.getHitbox().getY() + x.getHitbox().getHeight() / 2) {
					return 2;
				}
				// touches the length on the right side of the rectangle => return 3
				else if (hitbox.getX() <= x.getHitbox().getX() + x.getHitbox().getWidth()
						&& hitbox.getX() > x.getHitbox().getX() + x.getHitbox().getWidth() / 2
						&& hitbox.getY() <= x.getHitbox().getY() + x.getHitbox().getHeight()
						&& hitbox.getY() > x.getHitbox().getY() - hitbox.getHeight()) {
					return 3;
				}
				// touches the length on the left right side of the rectangle => return 4
				else if (hitbox.getX() >= x.getHitbox().getX() - hitbox.getWidth()
						&& hitbox.getX() < x.getHitbox().getX() + x.getHitbox().getWidth() / 2
						&& hitbox.getY() > x.getHitbox().getY() - hitbox.getHeight()
						&& hitbox.getY() < x.getHitbox().getY() + x.getHitbox().getHeight()) {
					return 4;
				}
				// special case
				else if (hitbox.getX() == x.getHitbox().getX()
						&& hitbox.getY() == x.getHitbox().getY() + x.getHitbox().getHeight()
						|| hitbox.getX() == x.getHitbox().getX() + x.getHitbox().getWidth()
								&& hitbox.getY() == x.getHitbox().getY() + x.getHitbox().getHeight()) {
					return 5;
				}
			}
		}
		return 1;
	}

	// Ăn ếch
	public void eatFrog(Shape hitbox) {
		if (babyFrog.getHitbox().intersects(hitbox) || babyFrog.getHitbox().contains(hitbox)) {
			isEat = true;
			PlayGame.score++;
			babyFrog.setPos_x(-30);
			babyFrog.setPos_y(0);
			babyFrog.setHitbox(new Rectangle(babyFrog.getPos_x(), babyFrog.getPos_y(), 30, 29));
		}
	}

	// Ăn trái cây
	public void eatFruit(Shape hitbox) {
		if (fruit.getHitbox().intersects(hitbox) || fruit.getHitbox().contains(hitbox)) {
			fruit.Eat();
			PlayGame.energy += 20;
			if (PlayGame.energy > 100) {
				PlayGame.energy = 100;
			}
			fruit.setPos_x(-30);
			fruit.setPos_y(0);
			fruit.setHitbox(new Rectangle(fruit.getPos_x(), fruit.getPos_y(), 30, 29));
		}
	}
}

// LƯU Ý 
// làm thêm Map khởi đầu
// xem xét biến thời gian ngẫu nhiên
