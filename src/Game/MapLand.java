package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public final class MapLand extends Map implements gameConfig {

	private ArrayList<Obstacles> obstacles;
//	private int countPlank = 4;
	private Random randomSpawn = null;
	int randomTime = 0;

	// Khởi tạo
	protected MapLand(float x) throws SlickException {
		super(null, x, 0, "land");

		this.background = new Image("Data/Image/MapLand.png");
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

		// Ngoi nha
		obstacles.add(new Obstacles(this.pos_x + 71, this.pos_y + 43, "Data/Image/Obstacles5.png"));
		// System.out.println(obstacles.size());
		System.out.println("land map");

	}

	protected MapLand(float x, float y) throws SlickException {
		super(null, x, 0, "land");

		randomSpawn = new Random();
		String url = "Data/Image/MapLand" + (randomSpawn.nextInt(0, 2) + 1) + ".png";
		this.background = new Image(url);

		this.pos_y = y - this.background.getHeight();

		obstacles = new ArrayList<Obstacles>();
	}

	// Cập nhật map
	public void update(int delta) throws SlickException {

		// xử lí các đối tượng cảu Land Map
		
		// Di chuyển map
		super.update(delta);
	
		if (url.equals("Data/Image/MapLand1.png")) {
			// Khoi tao doi tuong cua mapLand1
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
			// Ngoi nha
			obstacles.add(new Obstacles(this.pos_x + 71, this.pos_y + 43, "Data/Image/Obstacles5.png"));

		} else {

		}

		// System.out.println(obstacles.size());
		System.out.println("land map");

		// System.out.println("land map");

	}


	// Vẽ map
	public void render() {
		GameContainer g = PlayGame.gameContainer;
		super.render();
		for (Obstacles x : obstacles) {
			x.render();
		}

	}

	@Override
	public void update(int delta, int check, Shape hitbox) throws SlickException {
		// TODO Auto-generated method stub
		for (Obstacles x : obstacles) {
			x.update(delta, check);
		}
		if (check == 1) {
			// Di chuyển map
			super.update(delta, check, hitbox);
		}
	
	}

	@Override
	public int checkFrog(Shape hitbox) {
		for (Obstacles x : obstacles) {
			if (x.getHitbox().intersects(hitbox) || x.getHitbox().contains(hitbox)) {
				// Cham chieu rong cua hinh chu nhat tra ve 2
//				System.out.println("Vao intersects");
				if (hitbox.getX() >= x.getHitbox().getX() - hitbox.getWidth()
						&& hitbox.getX() < x.getHitbox().getX() + x.getHitbox().getHeight() + x.getHitbox().getWidth()
						&& hitbox.getY() < x.getHitbox().getY() + x.getHitbox().getHeight()
						&& hitbox.getY() > x.getHitbox().getY() + x.getHitbox().getHeight() / 2) {
//					System.out.println("Tra ve 2");
					return 2;
					// cham chieu dai ben phai cua hinh chu nhat tra ve 3
				} else if (hitbox.getX() <= x.getHitbox().getX() + x.getHitbox().getWidth()
						&& hitbox.getX() > x.getHitbox().getX() + x.getHitbox().getWidth() / 2
						&& hitbox.getY() <= x.getHitbox().getY() + x.getHitbox().getHeight()
						&& hitbox.getY() > x.getHitbox().getY() - hitbox.getHeight()) {
//					System.out.println("Tra ve 3");
					return 3;
					// cham chieu dai ben trai cua hinh chu nhat tra ve 4
				} else if (hitbox.getX() >= x.getHitbox().getX() - hitbox.getWidth()
						&& hitbox.getX() < x.getHitbox().getX() + x.getHitbox().getWidth() / 2
						&& hitbox.getY() > x.getHitbox().getY() - hitbox.getHeight()
						&& hitbox.getY() < x.getHitbox().getY() + x.getHitbox().getHeight()) {
//					System.out.println("Tra ve 4");
					return 4;
				} else if (hitbox.getX() == x.getHitbox().getX()
						&& hitbox.getY() == x.getHitbox().getY() + x.getHitbox().getHeight()
						|| hitbox.getX() == x.getHitbox().getX() + x.getHitbox().getWidth()
								&& hitbox.getY() == x.getHitbox().getY() + x.getHitbox().getHeight()) {
//					System.out.println("Tra ve 5");
					return 5;
				}
			}
		}
//		System.out.println("Tra ve 1 ngoai for");
		return 1;

	}

}