package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class MapWater extends Map implements gameConfig {

	private ArrayList<Plank> planks;
	private Random randomSpawn = null;
	int randomTime = 0;

	// Init map water
	protected MapWater(float x) throws SlickException {
		super(null, x, 0, "water");
		this.background = new Image("Data/Image/MapWater.png");
		this.pos_y = -background.getHeight();
		randomSpawn = new Random();
		planks = new ArrayList<Plank>();
	}

	protected MapWater(float x, float y) throws SlickException {
		super(null, x, 0, "water");

		this.background = new Image("Data/Image/MapWater.png");

		this.pos_y = y - this.background.getHeight();

		randomSpawn = new Random();

		planks = new ArrayList<Plank>();

		System.out.println("water map");

	}

	// Update map water
	public void update(int delta,int check, Frog frog) throws SlickException {
		String url = "Data/Image/Plank";
		randomTime += randomSpawn.nextInt(3);
		// Random the planks
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
		int index = -1;
		// move planks and remove it if it go out
		for (int i = 0; i < planks.size(); i++) {
			planks.get(i).update(delta,check);
 			if(planks.get(i).getHitbox().intersects(frog.getHitbox()) || planks.get(i).getHitbox().contains(frog.getHitbox()) ) {
 				index = i;
			}
			if (planks.get(i).checkLocation()) {
				planks.remove(i);
			}
		}
		// Move frog if frog on the planks
		if(index != -1) {
			if(planks.get(index).getDirection() == 1) {
				// (1: to right, -1: to left)
				frog.update(delta, 0);
			}else {
				frog.update(delta, -1);
			}
			
		}
		// Update move map
		if (check == 1 || check == 4 || check == 3) {
			super.update(delta, check, frog);
		}
	}

	// Vẽ map
	public void render() {
		super.render();

		for (int i = 0; i < planks.size(); i++) {
			planks.get(i).render();
		}
	}

	@Override
	public int checkFrog(Shape hitbox) {
		for(Plank x : planks ) {
			if(x.getHitbox().contains(hitbox.getX()+ hitbox.getWidth()/2 , hitbox.getY()+ hitbox.getHeight()/2) || x.getHitbox() .intersects(hitbox) ) {
				return 1;
			}
		}
		if(hitbox.getX()+ hitbox.getWidth()/2 > this.pos_x && hitbox.getX() + hitbox.getWidth()/2 < this.pos_x + this.getImage().getWidth()
				&& hitbox.getY()+ hitbox.getHeight()/2 > this.pos_y + 20 && hitbox.getY() + hitbox.getHeight()/2 < this.pos_y + this.getImage().getHeight()-40) {
			return -2;
		}
		
		
		return 1;
	}
	
}
