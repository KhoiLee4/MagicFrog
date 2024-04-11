package Game;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Car extends GameObject implements gameConfig {

	protected Car(float x, float y, String url, int direction) throws SlickException {
		super(null, x, y, null);

		this.direction = direction;

		this.speed = speedCar;
		
		// Direction of car 
		if (direction == 1) {
			this.img = new Image(url);
		} else {
			this.img = new Image(url).getFlippedCopy(true, false);
		}
		// Create Hitbox
		if (url.equals("Data/Image/Car1.png")) { 
			// Create HitBox blue car
			this.hitbox = new Rectangle( 
					this.pos_x +10,
					this.pos_y + 50,
					this.img.getWidth() - 30,
					this.img.getHeight() - 70);
			
		} else if (url.equals("Data/Image/Car2.png")) { 
			// Create HitBox orange car
			this.hitbox = new Rectangle(
					this.pos_x +10,
					this.pos_y + 50,
					this.img.getWidth() - 30,
					this.img.getHeight() - 70);
			
		}else { 
			// Create HitBox White car
			this.hitbox = new Rectangle(
					this.pos_x +10,
					this.pos_y + 50,
					this.img.getWidth() - 30,
					this.img.getHeight() - 65);
		}

	}

	// Update status car
	public void update(int delta, int check) throws SlickException {
		
		move(delta, check);
		
	}
	// Car movement event (direction 1: to right, -1: to left)	
	private void move(int delta, int check) throws SlickException {
		
		this.pos_x += this.speed * delta * this.direction;	
		this.hitbox.setX(this.hitbox.getX() + this.speed * delta * this.direction);
		
		if (PlayGame.gameContainer.getInput().isKeyDown(Input.KEY_UP) && (check == 1 || check == 3 || check == 4)) {
			this.pos_y += speedMap * delta;
			this.hitbox.setY(this.hitbox.getY() + speedMap * delta);
		}
		
	}

	// Get out => return true
	public boolean checkLocation() {
		
		return (this.direction == 1 && this.pos_x > screenWidth)
				|| (this.direction == -1 && this.pos_x < -this.img.getWidth());
		
	}

	public static boolean checkOnScreen(ArrayList<Car> cars) {
		for (Car c : cars) {
			if (c.pos_x < 0 || c.pos_x > 945 - c.img.getWidth()) {
				return false;
			}
		}
		return true;
	}
}
