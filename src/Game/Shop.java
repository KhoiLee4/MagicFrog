package Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Shop extends BasicGameState {
	// Nhạc nền, âm thanh hiệu ứng
	public SoundEffect sound;

	// Hình nền
	private Image img_background = null;
	// Hình các nút
	private Image img_bt_back = null;
	private Image img_bt_buy_no = null;
	private Image img_bt_buy_yes = null;

	// Hitbox các nút
	private Rectangle bt_back = null;
	private ArrayList<Rectangle> bt_buy;

	// Tọa độ các nút
	private int bt_back_X = 11;
	private int bt_back_Y = 851;
	// Tọa độ các nút Buy
	private int[] bt_buy_XY = { 221, 399, 576, 753, 221, 398, 576, 753, 552, 552, 552, 552, 753, 753, 753, 753 };

	// Khởi tạo các giá trị
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo âm thanh hiệu ứng
		sound = new SoundEffect();

		// Tạo hình ảnh
		img_background = new Image("Data/Image/Shop.png");
		img_bt_back = new Image("Data/Image/Button_Back.png");
		img_bt_buy_no = new Image("Data/Image/Buy_no.png");
		img_bt_buy_yes = new Image("Data/Image/Buy_yes.png");

		// Tạo hitbox
		bt_back = new Rectangle(bt_back_X, bt_back_Y, 130, 140);
		bt_buy = new ArrayList<Rectangle>();
		for (int i = 0; i < bt_buy_XY.length / 2; i++) {
			bt_buy.add(new Rectangle(bt_buy_XY[i], bt_buy_XY[i + bt_buy_XY.length / 2], 78, 30));
		}

		//
	}

	// Cập nhật
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		// Quay lại
		if ((bt_back.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_back
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}

	}

	// Hiển thị
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ hình nền
		img_background.draw();

		// Vẽ hitbox
		g.setColor(Color.transparent);
		for (Rectangle bt : bt_buy) {
			g.draw(bt);
		}
		g.draw(bt_back);

		// Vẽ nút
		// Xử lí vẽ nút theo giá trị của account
//		for (int i = 0; i < bt_buy_XY.length / 2; i++) {
//			img_bt_buy_yes.draw(bt_buy_XY[i], bt_buy_XY[i + bt_buy_XY.length / 2]);
//		}
		
		img_bt_back.draw(bt_back_X, bt_back_Y);
	}

	// Lấy id trạng thái
	@Override
	public int getID() {
		return 8;
	}
}
