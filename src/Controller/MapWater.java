package Controller;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class MapWater extends Map implements gameConfig {
	// Danh sách ván trong Map
	private ArrayList<Plank> planks;

	// Biến chọn ván ngẫu nhiên
	private Random randomSpawn;

	// Biến chọn thời gian ngẫu nhiên
	private int randomTime = 0;

	// Khởi tạo
	public MapWater(float x) throws SlickException {
		// Tạo loại Map
		super(null, x, 0, "water");

		// Đặt hình nền, điều chỉnh vị trí
		this.background = new Image("Data/Image/MapWater.png");
		this.pos_y = -background.getHeight();

		randomSpawn = new Random();

		planks = new ArrayList<Plank>();
	}

	public MapWater(float x, float y) throws SlickException {
		// Tạo loại Map
		super(null, x, 0, "water");

		// Đặt hình nền, điều chỉnh vị trí
		this.background = new Image("Data/Image/MapWater.png");
		this.pos_y = y - this.background.getHeight();

		randomSpawn = new Random();

		planks = new ArrayList<Plank>();
	}

	// Cập nhật
	public void update(int delta, int check, Frog frog) throws SlickException {
		String url = "Data/Image/Plank";

		randomTime += randomSpawn.nextInt(3);

		// Tạo ván ngẫu nhiên theo thời gian
		if (planks.size() <= 3 && Plank.checkOnScreen(planks)) {
			if (randomTime == 10) {
				int random = randomSpawn.nextBoolean() ? 1 : -1;
				planks.add(new Plank((random == 1) ? this.pos_x : this.pos_x + 945,
						this.pos_y + 45 + 80 * ((random == 1) ? 0 : 1), url + (randomSpawn.nextInt(3) + 1) + ".png",
						random));
			}
		}

		if (randomTime > 10) {
			randomTime = 0;
		}

		// Vị trí ván nhân vật đứng
		int index = -1;

		// Di chuyển ván
		for (int i = 0; i < planks.size(); i++) {
//			planks.get(i).update(delta, check);

			// Kiểm tra tấm ván có nhân vật
			if (planks.get(i).getHitbox().intersects(frog.getHitbox())
					|| planks.get(i).getHitbox().contains(frog.getHitbox())) {
				if (frog.getHitbox().getX() < planks.get(i).getHitbox().getX()
						+ planks.get(i).getHitbox().getWidth() - 30
						&& frog.getHitbox().getX() > planks.get(i).getHitbox().getX()

						&& frog.getHitbox().getY() <= planks.get(i).getHitbox().getY()
								+ planks.get(i).getHitbox().getHeight() - 20
						&& frog.getHitbox().getY() >= planks.get(i).getHitbox().getY() - frog.getHitbox().getHeight()) {

					index = i;
				}

			}
		}

		// Di chuyển nhân vật theo ván
		if (index != -1) {

			// (1: to right, -1: to left)
			if (planks.get(index).getDirection() == 1) {
				frog.update(delta, 0);
			} else {
				frog.update(delta, -1);
			}
		}
		for (int i = 0; i < planks.size(); i++) {
			planks.get(i).update(delta, check);
			// Xóa ván
			if (planks.get(i).checkLocation()) {
				planks.remove(i);
			}
		}

		// Cập nhật Map
		if (check == 1 || check == 4 || check == 3) {
			super.update(delta, check, frog);
		}
	}

	@Override
	public void update2(int delta, float distance) throws SlickException {
		// TODO Auto-generated method stub
		for (int i = 0; i < planks.size(); i++) {
			planks.get(i).update2(distance);
		}
		super.update2(delta, distance);
	}

	// Vẽ map
	public void render() {
		super.render();

		for (int i = 0; i < planks.size(); i++) {
			planks.get(i).render();
		}
	}

	// Kiểm tra nhân vật so với Map
	@Override
	public int checkFrog(Shape hitbox) {

		for (Plank x : planks) {
			if (x.getHitbox().contains(hitbox.getX() + hitbox.getWidth() / 2, hitbox.getY() + hitbox.getHeight() / 2)
					|| x.getHitbox().intersects(hitbox)) {
				return 1;
			}
		}
		if (hitbox.getX() + hitbox.getWidth() / 2 > this.pos_x
				&& hitbox.getX() + hitbox.getWidth() / 2 < this.pos_x + this.getImage().getWidth()
				&& hitbox.getY() + hitbox.getHeight() / 2 > this.pos_y + 20
				&& hitbox.getY() + hitbox.getHeight() / 2 < this.pos_y + this.getImage().getHeight() - 108) {
			return -2;
		}
		return 1;
	}

}

// LƯU Ý
// xử lý khởi tạo ván chồng lên nhau
