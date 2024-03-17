package Game;

import org.newdawn.slick.Image;

public abstract class GameObject {

	protected Image img;
	protected int pos_x;
	protected int pos_y;

	protected GameObject(Image img, int pos_x, int pos_y) {
		this.img = img;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	public void render() {
		if (this.img != null) {
			this.img.drawCentered(pos_x, pos_y);
		}

	}

}
