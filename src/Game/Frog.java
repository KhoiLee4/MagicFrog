package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Frog extends GameObject implements gameConfig {
	
	protected Frog() throws SlickException {
		super(null, screenWidth / 2, screenHeight - 100, null);

		this.img = new Image("Data/Image/Frog.png");

		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2, this.pos_y - this.img.getHeight() / 2,
				this.img.getWidth(), this.img.getHeight());

	}

	// cập nhật trạng thái của ếch
	public void update() throws SlickException {
		move(1);
	}

	// bắt sự kiện di chuyển của nhân vật
	public void move(float speed) throws SlickException {
		Input input = PlayGame.game_container.getInput();
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			this.pos_x += speed*delta;
		}
		if(input.isKeyDown(Input.KEY_LEFT)) {
			this.pos_x -= speed*delta;
		}
		if(input.isKeyDown(Input.KEY_UP)) {
			this.pos_y -= speed*delta;
		}
		
	}

}
