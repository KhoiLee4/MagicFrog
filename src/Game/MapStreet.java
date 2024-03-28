package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MapStreet extends Map implements gameConfig {

	private ArrayList<Car> cars;
//	private int countPlank = 4;
	private Random randomSpawn = null;
	int randomTime = 0;

	// Khởi tạo
	protected MapStreet(float x) throws SlickException {
		super(null, x, 0);

		this.background = new Image("Data/Image/MapStreet.png");
		this.pos_y = -background.getHeight();

		randomSpawn = new Random();

		cars = new ArrayList<Car>();

		System.out.println("water map");

	}

	protected MapStreet(float x, float y) throws SlickException {
		super(null, x, 0);

		this.background = new Image("Data/Image/MapStreet.png");

		this.pos_y = y - this.background.getHeight();

		randomSpawn = new Random();

		cars = new ArrayList<Car>();

		System.out.println("street map");

	}

	// Cập nhật map
	public void update(int delta) throws SlickException {
		String url = "Data/Image/Car";

		randomTime += randomSpawn.nextInt(5);

		// Tạo ngẫu nhiên các tấm ván
		if (cars.size() <= 3 && Car.checkOnScreen(cars)) {
			if (randomTime == 10) {
				int random = randomSpawn.nextBoolean() ? 1 : -1;
				cars.add(new Car((random == 1) ? this.pos_x : this.pos_x + 945,
						this.pos_y + 20 + 125 * ((random == 1) ? 0 : 1), url + randomSpawn.nextInt(1, 3) + ".png",
						random));
				System.out.println("car");

			}
		}
		if (randomTime > 10) {
			randomTime = 0;
		}

		// Di chuyển và xóa các tấm ván
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).update(delta);

			if (cars.get(i).checkLocation()) {
				cars.remove(i);
			}
		}

		// Di chuyển map
		super.update(delta);
	}

	// Vẽ map
	public void render() {
//		GameContainer g = PlayGame.gameContainer;
		super.render();
//		this.background.draw(pos_x, pos_y);

		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).render();
		}

//		System.out.println("render water map");
	}

	// Di chuyển map

}