package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject {

	protected Image img;
	protected int pos_x;
	protected int pos_y;
	protected Shape hitbox;

	protected GameObject(Image img, int pos_x, int pos_y, Shape hitbox){
		this.img = img;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.hitbox = hitbox;
	}

	public void render() {
		if (this.img != null) {
			this.img.drawCentered(pos_x, pos_y);
		}

	}
	

}
