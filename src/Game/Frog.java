package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Frog extends GameObject implements gameConfig {
	
	protected Frog() throws SlickException {
		super(null, screenWidth / 2, screenHeight - 100);

		this.img = new Image("Data/Image/Frog.png");

	}

	// cập nhật trạng thái nhân vật
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
