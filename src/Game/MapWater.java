package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MapWater extends Map implements gameConfig {

	private ArrayList<Plank> planks;
//	private int countPlank = 4;
	private Random randomSpawn = null;
	int randomTime = 0;

	// Khởi tạo
	protected MapWater(float x) throws SlickException {
		super(null, x, 0);

		this.background = new Image("Data/Image/MapWater.png");
		this.pos_y = -background.getHeight();

		randomSpawn = new Random();

		planks = new ArrayList<Plank>();


	}

	protected MapWater(float x, float y) throws SlickException {
		super(null, x, 0);

		this.background = new Image("Data/Image/MapWater.png");

		this.pos_y = y - this.background.getHeight();

		randomSpawn = new Random();

		planks = new ArrayList<Plank>();

	}

	// Cập nhật map
	public void update(int delta) throws SlickException {
		String url = "Data/Image/Plank";

		randomTime += randomSpawn.nextInt(3);

		// Tạo ngẫu nhiên các tấm ván
		if (planks.size() <= 3 && Plank.checkOnScreen(planks)) {
			if (randomTime == 10) {
				int random = randomSpawn.nextBoolean() ? 1 : -1;
				planks.add(new Plank((random == 1) ? this.pos_x : this.pos_x + 945,
						this.pos_y + 45 + 80 * ((random == 1) ? 0 : 1), url + randomSpawn.nextInt(1, 3) + ".png",
						random));
			}
		}

		if (randomTime > 10) {
			randomTime = 0;
		}

		// Di chuyển và xóa các tấm ván
		for (int i = 0; i < planks.size(); i++) {
			planks.get(i).update(delta);

			if (planks.get(i).checkLocation()) {
				planks.remove(i);
			}
		}

		// Di chuyển map
		super.update(delta);
	}

	// Vẽ map
	public void render() {
//		GameContainer g = PlayGame.gameContainer;
		super.render();

		for (int i = 0; i < planks.size(); i++) {
			planks.get(i).render();
		}
	}
}
