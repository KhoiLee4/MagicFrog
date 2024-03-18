package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject {

	protected Image img;
	protected float pos_x;
	protected float pos_y;
	protected Shape hitbox;
	
	
	protected GameObject(Image img, float pos_x,float pos_y, Shape hitbox){
		this.img = img;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.hitbox = hitbox;
	}

	public void render() {
		if (this.img != null) {
			this.img.drawCentered(pos_x, pos_y);
		}
		if (this.hitbox != null) {
			PlayGame.gameContainer.getGraphics().setColor(Color.red);
			PlayGame.gameContainer.getGraphics().draw(this.hitbox);
		}

	}
	

}
