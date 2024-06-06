package Controller;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class MapStreet extends Map implements gameConfig {
	// Danh sách xe trong Map
	private ArrayList<Car> cars;

	// Biến chọn xe ngẫu nhiên
	private Random randomSpawn;

	// Biến chọn thời gian ngẫu nhiên
	private int randomTime = 0;

	// Khởi tạo
	public MapStreet(float x) throws SlickException {
		// Tạo loại Map
		super(null, x, 0, "street");

		// Đặt hình nền, điều chỉnh vị trí
		this.background = new Image("Data/Image/MapStreet.png");
		this.pos_y = -background.getHeight();

		randomSpawn = new Random();

		cars = new ArrayList<Car>();
	}

	public MapStreet(float x, float y) throws SlickException {
		// Tạo loại Map
		super(null, x, 0, "street");

		// Đặt hình nền, điều chỉnh vị trí
		this.background = new Image("Data/Image/MapStreet.png");
		this.pos_y = y - this.background.getHeight();

		randomSpawn = new Random();

		cars = new ArrayList<Car>();
	}

	// Cập nhật
	public void update(int delta, int check, Frog frog) throws SlickException {
		String url = "Data/Image/Car";

		randomTime += randomSpawn.nextInt(5);

		// Tạo xe ngẫu nhiên theo thời gian
		if (cars.size() <= 3 && Car.checkOnScreen(cars)) {
			if (randomTime == 100) {
				int random = randomSpawn.nextBoolean() ? 1 : -1;
				// 1 là bên trái -1 là bên phải direction
				// 1 là đường trên
				// -1 là đường dưới
				cars.add(new Car((random == 1) ? this.pos_x : this.pos_x + 945,
						this.pos_y + 20 + 125 * ((random == 1) ? 0 : 1), url + (randomSpawn.nextInt(3) + 1) + ".png",
						random));

			}
		}

		if (randomTime > 100) {
			randomTime = 0;
		}

		// Cập nhật xe
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).update(delta, check);

			// Xóa xe
			if (cars.get(i).checkLocation()) {
				cars.remove(i);
			}
		}

		// Cập nhật Map
		if (check == 1 || check == 3 || check == 4) {
			super.update(delta, check, frog);
		}
	}

	@Override
	public void update2(int delta, float distance) throws SlickException {
		// TODO Auto-generated method stub
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).update2(distance);
		}
		super.update2(delta, distance);
	}

	// Vẽ Map
	public void render() {
		super.render();
		for (int i = 0; i < cars.size(); i++) {
			if (i >= 1) {
				if (cars.get(i - 1).getDirection() == cars.get(i).getDirection()) {
					cars.remove(i);
					continue;
				}
			}
			cars.get(i).render();
		}
	}

	// Kiểm tra nhân vật so với Map
	@Override
	public int checkFrog(Shape hitbox) {
		for (Car x : cars) {
			if (x.getHitbox().intersects(hitbox) || x.getHitbox().contains(hitbox)) {
				return -2;
			}
		}
		return 1;
	}

}
