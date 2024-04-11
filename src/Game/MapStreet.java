package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class MapStreet extends Map implements gameConfig {

	private ArrayList<Car> cars;
//	private int countPlank = 4;
	private Random randomSpawn = null;
	int randomTime = 0;

	// Khởi tạo
	protected MapStreet(float x) throws SlickException {
		super(null, x, 0,"street");

		this.background = new Image("Data/Image/MapStreet.png");
		this.pos_y = -background.getHeight();

		randomSpawn = new Random();

		cars = new ArrayList<Car>();

		System.out.println("water map");

	}

	protected MapStreet(float x, float y) throws SlickException {
		super(null, x, 0,"street");

		this.background = new Image("Data/Image/MapStreet.png");

		this.pos_y = y - this.background.getHeight();

		randomSpawn = new Random();

		cars = new ArrayList<Car>();


	}

	// Cập nhật map
	public void update(int delta, int check, Frog frog) throws SlickException {
		String url = "Data/Image/Car";

		randomTime += randomSpawn.nextInt(5);

		// Tạo ngẫu nhiên xe
		if (cars.size() <= 3 && Car.checkOnScreen(cars)) {
			if (randomTime == 10) {
				int random = randomSpawn.nextBoolean() ? 1 : -1;
				cars.add(new Car((random == 1) ? this.pos_x : this.pos_x + 945,
						this.pos_y + 20 + 125 * ((random == 1) ? 0 : 1), url + randomSpawn.nextInt(1, 4) + ".png",
						random));
				

			}
		}
		if (randomTime > 10) {
			randomTime = 0;
		}
		// Di chuyển và xóa các chiếc xe
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).update(delta, check);
			
			if (cars.get(i).checkLocation()) {
				cars.remove(i);
			}
		}
		// Di chuyển map
		if (check == 1 || check == 3 || check == 4) {
			
			super.update(delta, check, frog);
		}

	}

	// Vẽ map
	public void render() {
		super.render();
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).render();
		}

	}

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