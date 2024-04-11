package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

// Đối tượng tường, nhà
public class Obstacles extends GameObject {
	protected Obstacles(float x, float y, String url) throws SlickException {
		super(null, x, y, null);
		this.img = new Image(url);

		if(url.equals("Data/Image/Obstacles5.png")) {
			this.hitbox = new Rectangle(this.pos_x + 20  , this.pos_y +3*this.img.getHeight()/4 ,
					this.img.getWidth()- 40, this.img.getHeight()/4);
		}
		if(url.equals("Data/Image/Obstacles2.png")) {
			this.hitbox = new Rectangle(this.pos_x + 10 , this.pos_y,
					this.img.getWidth()-20, this.img.getHeight()-5);
		}
		if(url.equals("Data/Image/Obstacles1.png")) {
			this.hitbox = new Rectangle(this.pos_x + 10 , this.pos_y,
					this.img.getWidth()-20, this.img.getHeight()-5);
		}
		if(url.equals("Data/Image/Obstacles4.png")) {
			this.hitbox = new Rectangle(this.pos_x , this.pos_y,
					this.img.getWidth(), this.img.getHeight() - 20);
		}
		if(url.equals("Data/Image/Obstacles3.png")) {
			this.hitbox = new Rectangle(this.pos_x , this.pos_y,
					this.img.getWidth(), this.img.getHeight() );
		}
		if(url.equals("Data/Image/Obstacles7.png")) {
			this.hitbox = new Rectangle(this.pos_x , this.pos_y,
					this.img.getWidth(), this.img.getHeight() - 20);
		}
		
	//	super.setHitBox(this.hitbox);
	}

	// cập nhật trạng thái của ếch
	public void update(int delta,int check) throws SlickException {
	
			float speed = 0.2f;
			this.move(speed, delta);
			
		
		
	}
	
	// bắt sự kiện di chuyển của ếch
	private void move(float speed, int delta) throws SlickException {
		Input input = PlayGame.gameContainer.getInput();
		if (input.isKeyDown(Input.KEY_UP)) {
			this.pos_y += speed * delta;
			this.hitbox.setY(this.hitbox.getY() + speed * delta );
		}

	}
	
}
