package Controller;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import View.PlayGame;

public final class MapLand extends Map implements gameConfig {
	// Âm thanh hiệu ứng
	private SoundEffect sound;

	// Danh sách đối tượng trong Map
	private ArrayList<Obstacles> obstacles;

	// Ếch con
	private BabyFrog babyFrog;

	// Ếch con bị ăn chưa
	private boolean isEat = false;

	private Fruit fruit;

	// Biến chọn map ngẫu nhiên
	private Random randomSpawn;
	private int randomNumber;

	// Flag render of frog and fruit
	private boolean isRenderFrogBaby = false;
	private boolean isRenderFruit = false;

	// Count random again
	private int cntChildFrog = 0;
	private int cntFruit = 0;

	// Khởi tạo
	public MapLand(float x) throws SlickException {
		// Tạo loại Map
		super(null, x, 0, "land");

		// Tạo âm thanh hiệu ứng
		sound = new SoundEffect();

		// Đặt hình nền, điều chỉnh vị trí
		this.background = new Image("Data/Image/MapLand.png");
		this.pos_y = screenHeight - background.getHeight();

		randomSpawn = new Random();

		obstacles = new ArrayList<Obstacles>();
		// default CreateMap 3
		createMap3();
//		babyFrog = new BabyFrog(this.pos_y, obstacles);
//		isRenderFrogBaby = true;
//		fruit = new Fruit(this.pos_y, obstacles);
//		isRenderFruit = true;
	}

	public MapLand(float x, float y, double energy) throws SlickException {
		// Tạo loại Map
		super(null, x, 0, "land");

		// Tạo âm thanh hiệu ứng
		sound = new SoundEffect();

		// Tạo Map ngẫu nhiên trong kho có sẵn
		randomSpawn = new Random();
		String url = "Data/Image/MapLand" + (randomSpawn.nextInt(6) + 1) + ".png";
		this.background = new Image(url);
		this.pos_y = y + 1 - this.background.getHeight();

		obstacles = new ArrayList<Obstacles>();

		if (url.equals("Data/Image/MapLand1.png")) {
			// Draw mapLand 1

			createMap1();
		} else if (url.equals("Data/Image/MapLand2.png")) {
			// Draw mapLand 2
			System.out.println("Map 2");
			createMap2();

		} else if (url.equals("Data/Image/MapLand3.png")) {
			System.out.println("Map 3");
			createMap3();
		} else if (url.equals("Data/Image/MapLand4.png")) {
			System.out.println("Map 4");
			createMap4();
		} else if (url.equals("Data/Image/MapLand5.png")) {
			System.out.println("Map 5");
			createMap5();
		} else if (url.equals("Data/Image/MapLand6.png")) {
			System.out.println("Map 6");
			createMap6();
		}
		randomNumber = (randomSpawn.nextInt(20));
		if (randomNumber <= 10) {
			babyFrog = new BabyFrog(this.pos_y);
			isRenderFrogBaby = true;
			changePosChildFrog(obstacles);
		}

		if (randomNumber >= 15 || energy <= 50) {
			fruit = new Fruit(this.pos_y);
			isRenderFruit = true;
			changePosFruit(obstacles);
		}

	}

	public void changePosChildFrog(ArrayList<Obstacles> obstacles) {
		boolean finalCheck = false;
		while (!finalCheck) {
			// Find the obstacle
			boolean check = true;
			int i = 0;
			for (Obstacles obstacle : obstacles) {
				if (obstacle.getHitbox().intersects(babyFrog.getHitbox())
						|| obstacle.getHitbox().contains(babyFrog.getHitbox())) {
					check = false;
					// System.out.println("Random again children frog");
					break;
				}
				i++;
			}

			if (!check) {
				// System.out.println("Baby Frog before: " + babyFrog.getPos_x() + " " +
				// babyFrog.getPos_y());
				// Example 1 : the obstacle in 1/2 side left
				if (obstacles.get(i).getPos_x() < 1 / 2 * screenWidth) {
					// System.out.println("Enter EX 1");
					babyFrog.setPos_x(obstacles.get(i).getPos_x() + obstacles.get(i).getHitbox().getWidth() + 10);
					babyFrog.hitbox.setX(babyFrog.getPos_x());
				} else {// Example 2 : the obstacle in 1/2 side right
					// System.out.println("Enter EX 2");
					babyFrog.setPos_x(obstacles.get(i).getPos_x() - babyFrog.getHitbox().getWidth());
					babyFrog.hitbox.setX(babyFrog.getPos_x());
				}
				cntChildFrog++;
				if (this.pos_y - babyFrog.hitbox.getY() >= 0 || cntChildFrog >= 10) {
					babyFrog.setPos_x(babyFrog.getPos_x() + screenWidth);
					babyFrog.getHitbox().setX(babyFrog.getPos_x());
					finalCheck = true;
				}
				// System.out.println("Baby Frog after: " + babyFrog.getPos_x() + " " +
				// babyFrog.getPos_y());
			} else {
				finalCheck = true;
			}
		}
	}

	public void changePosFruit(ArrayList<Obstacles> obstacles) {
		boolean finalCheck = false;
		while (!finalCheck) {
			// Find the obstacle
			boolean check = true;
			int i = 0;
			for (Obstacles obstacle : obstacles) {
				if (obstacle.getHitbox().intersects(fruit.getHitbox())
						|| obstacle.getHitbox().contains(fruit.getHitbox())) {
					check = false;
					// System.out.println("Random again Fruit");
					break;
				}
				i++;
			}
			if (!check) {
				// System.out.println("Fruit before: " + fruit.getPos_x() + " " +
				// fruit.getPos_y());
				// Example 1 : the obstacle in 1/2 side left
				if (obstacles.get(i).getPos_x() < 1 / 2 * screenWidth) {
					// System.out.println("Enter EX 1");
					fruit.setPos_x(obstacles.get(i).getPos_x() + obstacles.get(i).getHitbox().getWidth() + 10);
					fruit.getHitbox().setX(fruit.getPos_x());
				} else {// Example 2 : the obstacle in 1/2 side right
					// System.out.println("Enter EX 2");
					fruit.setPos_x(obstacles.get(i).getPos_x() - fruit.getHitbox().getWidth() - 1);
					fruit.getHitbox().setX(fruit.getPos_x());
				}
				cntFruit++;
				if (this.pos_y - fruit.hitbox.getY() >= 0 || cntFruit >= 10) {
					fruit.setPos_x(fruit.getPos_x() + screenWidth);
					fruit.getHitbox().setX(fruit.getPos_x());
					finalCheck = true;
				}
				// System.out.println("Fruit after: " + fruit.getPos_x() + " " +
				// fruit.getPos_y());
			} else {
				finalCheck = true;
			}
		}
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
			if (isRenderFrogBaby) {
				if (!isEat) {
					babyFrog.update(delta, check);
				}
			}

			if (isRenderFruit) {
				if (!fruit.getIsEat()) {
					fruit.update(delta, check);
				}
			}

			// Cập nhật Map
			super.update(delta, check, frog);
		}
	}

	// Update 2
	@Override
	public void update2(int delta, float distance) throws SlickException {
		// TODO Auto-generated method stub
		// Cập nhật đối tượng trong Map
		for (Obstacles x : obstacles) {
			x.update2(distance);
		}

		// Cập nhật ếch con
		if (isRenderFrogBaby) {
			if (!isEat) {
				babyFrog.update2(delta, distance);
			}
		}

		if (isRenderFruit) {
			if (!fruit.getIsEat()) {
				fruit.update2(distance);
			}
		}

		super.update2(delta, distance);
	}

	// Vẽ Map
	@Override
	public void render() {
		super.render();
		for (Obstacles x : obstacles) {
			x.render();
		}
		if (isRenderFrogBaby) {
			if (!isEat) {
				babyFrog.render();
			}

		}
		if (isRenderFruit) {
			if (!fruit.getIsEat()) {
				fruit.render();
			}
		}
	}

	// Kiểm tra nhân vật so với Map
	@Override
	public int checkFrog(Shape hitbox) {
		if (isRenderFrogBaby) {
			if (!isEat) {
				eatFrog(hitbox);
			}
		}
		if (isRenderFruit) {
			if (!fruit.getIsEat()) {
				eatFruit(hitbox);
			}
		}

		for (Obstacles x : obstacles) {
			if (x.getHitbox().intersects(hitbox) || x.getHitbox().contains(hitbox)) {
				// touches the bottom edge of the rectangle => return 2
				if (hitbox.getX() >= x.getHitbox().getX() - hitbox.getWidth()
						&& hitbox.getX() < x.getHitbox().getX() + x.getHitbox().getHeight() + x.getHitbox().getWidth()
						&& hitbox.getY() < x.getHitbox().getY() + x.getHitbox().getHeight()
						&& hitbox.getY() > x.getHitbox().getY() + 3 * x.getHitbox().getHeight() / 5) {
					// System.out.println(2);
					return 2;
				}
				// touches the length on the right side of the rectangle => return 3
				else if (hitbox.getX() <= x.getHitbox().getX() + x.getHitbox().getWidth()
						&& hitbox.getX() > x.getHitbox().getX() + x.getHitbox().getWidth() / 2
						&& hitbox.getY() <= x.getHitbox().getY() + x.getHitbox().getHeight()
						&& hitbox.getY() > x.getHitbox().getY() - hitbox.getHeight()) {
					// System.out.println(3);
					return 3;
				}
				// touches the length on the left right side of the rectangle => return 4
				else if (hitbox.getX() >= x.getHitbox().getX() - hitbox.getWidth()
						&& hitbox.getX() < x.getHitbox().getX() + x.getHitbox().getWidth() / 2
						&& hitbox.getY() > x.getHitbox().getY() - hitbox.getHeight()
						&& hitbox.getY() < x.getHitbox().getY() + x.getHitbox().getHeight()) {
					// System.out.println(4);
					return 4;
				}
				// special case
				else if (hitbox.getX() == x.getHitbox().getX()
						&& hitbox.getY() == x.getHitbox().getY() + x.getHitbox().getHeight()
						|| hitbox.getX() == x.getHitbox().getX() + x.getHitbox().getWidth()
								&& hitbox.getY() == x.getHitbox().getY() + x.getHitbox().getHeight()) {
					// System.out.println(5);
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
			sound.levelUp();
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
			sound.levelUp();
			PlayGame.energy += 20;
			if (PlayGame.energy > 100) {
				PlayGame.energy = 100;
			}
			fruit.setPos_x(-30);
			fruit.setPos_y(0);
			fruit.setHitbox(new Rectangle(fruit.getPos_x(), fruit.getPos_y(), 30, 29));
		}
	}

	private void createMap1() throws SlickException {
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
		obstacles.add(new Obstacles(this.pos_x + 290, this.pos_y + 377, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 236, this.pos_y + 27, "Data/Image/Obstacles4.png"));
		// House
		obstacles.add(new Obstacles(this.pos_x + 41, this.pos_y + 43, "Data/Image/Obstacles5.png"));

	}

	private void createMap2() throws SlickException {
		obstacles.add(new Obstacles(this.pos_x + 180, this.pos_y + 25, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 285, this.pos_y + 137, "Data/Image/Obstacles2.png"));
		obstacles.add(new Obstacles(this.pos_x + 612, this.pos_y + 65, "Data/Image/Obstacles1.png"));
		// obstacles.add(new Obstacles(this.pos_x + 520, this.pos_y + 71,
		// "Data/Image/Obstacles7.png"));
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

	private void createMap3() throws SlickException {
		// Init tree
		obstacles.add(new Obstacles(this.pos_x + 54, this.pos_y + 502, "Data/Image/Obstacles3.png"));
		obstacles.add(new Obstacles(this.pos_x + 213, this.pos_y + 502, "Data/Image/Obstacles3.png"));
		obstacles.add(new Obstacles(this.pos_x + 885, this.pos_y + 502, "Data/Image/Obstacles3.png"));
		obstacles.add(new Obstacles(this.pos_x + 703, this.pos_y + 502, "Data/Image/Obstacles3.png"));

		obstacles.add(new Obstacles(this.pos_x + 155, this.pos_y + 267, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 520, this.pos_y + 267, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 800, this.pos_y + 267, "Data/Image/Obstacles4.png"));

		obstacles.add(new Obstacles(this.pos_x + 74, this.pos_y + 99, "Data/Image/Obstacles7.png"));
		obstacles.add(new Obstacles(this.pos_x + 620, this.pos_y + 99, "Data/Image/Obstacles7.png"));
		obstacles.add(new Obstacles(this.pos_x + 800, this.pos_y + 99, "Data/Image/Obstacles7.png"));

		obstacles.add(new Obstacles(this.pos_x + 700, this.pos_y + 20, "Data/Image/Obstacles2.png"));
	}

	private void createMap4() throws SlickException {
		// Init tree
		obstacles.add(new Obstacles(this.pos_x + 104, this.pos_y + 46, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 104, this.pos_y + 239, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 104, this.pos_y + 420, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 800, this.pos_y + 46, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 800, this.pos_y + 239, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 800, this.pos_y + 420, "Data/Image/Obstacles4.png"));

		// Init small tree
		obstacles.add(new Obstacles(this.pos_x + 215, this.pos_y + 450, "Data/Image/Obstacles7.png"));

		// init fence
		obstacles.add(new Obstacles(this.pos_x + 270, this.pos_y + 56, "Data/Image/Obstacles1.png"));
		obstacles.add(new Obstacles(this.pos_x + 270, this.pos_y + 500, "Data/Image/Obstacles1.png"));

		// init house
		obstacles.add(new Obstacles(this.pos_x + 275, this.pos_y + 100, "Data/Image/Obstacles5.png"));

	}

	private void createMap5() throws SlickException {
		// init fence
		obstacles.add(new Obstacles(this.pos_x + 166, this.pos_y + 234, "Data/Image/Obstacles1.png"));
		obstacles.add(new Obstacles(this.pos_x + 780, this.pos_y + 289, "Data/Image/Obstacles1.png"));

		// init wall
		obstacles.add(new Obstacles(this.pos_x + 456, this.pos_y + 419, "Data/Image/Obstacles2.png"));
		obstacles.add(new Obstacles(this.pos_x + 815, this.pos_y + 441, "Data/Image/Obstacles2.png"));
		obstacles.add(new Obstacles(this.pos_x + 110, this.pos_y + 543, "Data/Image/Obstacles2.png"));
		obstacles.add(new Obstacles(this.pos_x + 762, this.pos_y + 540, "Data/Image/Obstacles2.png"));

		// init tree
		obstacles.add(new Obstacles(this.pos_x + 100, this.pos_y + 42, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 140, this.pos_y + 350, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 570, this.pos_y + 60, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 400, this.pos_y + 260, "Data/Image/Obstacles4.png"));

		// init small tree
		obstacles.add(new Obstacles(this.pos_x + 330, this.pos_y + 65, "Data/Image/Obstacles3.png"));
		obstacles.add(new Obstacles(this.pos_x + 940, this.pos_y + 80, "Data/Image/Obstacles3.png"));

	}

	private void createMap6() throws SlickException {

		// init wall
		obstacles.add(new Obstacles(this.pos_x + 700, this.pos_y + 58, "Data/Image/Obstacles2.png"));
		obstacles.add(new Obstacles(this.pos_x + 764, this.pos_y + 285, "Data/Image/Obstacles2.png"));

		// init tree
		obstacles.add(new Obstacles(this.pos_x + 420, this.pos_y + 80, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 310, this.pos_y + 382, "Data/Image/Obstacles4.png"));
		obstacles.add(new Obstacles(this.pos_x + 350, this.pos_y + 177, "Data/Image/Obstacles4.png"));

		// init small tree
		obstacles.add(new Obstacles(this.pos_x + 80, this.pos_y + 442, "Data/Image/Obstacles8.png"));
		obstacles.add(new Obstacles(this.pos_x + 255, this.pos_y + 450, "Data/Image/Obstacles3.png"));
		obstacles.add(new Obstacles(this.pos_x + 731, this.pos_y + 448, "Data/Image/Obstacles9.png"));
		obstacles.add(new Obstacles(this.pos_x + 920, this.pos_y + 155, "Data/Image/Obstacles3.png"));
		obstacles.add(new Obstacles(this.pos_x + 905, this.pos_y + 446, "Data/Image/Obstacles3.png"));

	}
}

// LƯU Ý 
// làm thêm Map khởi đầu
// xem xét biến thời gian ngẫu nhiên
