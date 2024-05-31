package Game;

import java.util.ArrayList;
import java.util.Arrays;

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

import GameData.DetailDAO;

public class Shop extends BasicGameState implements gameConfig {
	// Nhạc nền, âm thanh hiệu ứng
	private SoundEffect sound;

	// Tiền
	private int money;

	// Hình nền
	private Image img_background = null;
	private Image img_notice_shop = null;
	private Image img_notice_price = null;

	// Hình các nút
	private Image img_bt_back = null;
	private Image img_bt_buy_no = null;
	private Image img_bt_buy_yes = null;
	private Image img_bt_yes = null;
	private Image img_bt_no = null;

	// Hitbox các nút
	private Rectangle bt_back = null;
	private Rectangle bt_yes = null;
	private Rectangle bt_no = null;
	private ArrayList<Rectangle> bt_buy;

	// Tọa độ các nút
	private int bt_back_X = 11;
	private int bt_back_Y = 851;

	private int bt_yes_X = 293;
	private int bt_yes_Y = 572;

	private int bt_no_X = 632;
	private int bt_no_Y = 572;

	// Tọa độ các nút Buy
	private int[] bt_buy_XY = { 221, 399, 576, 753, 221, 398, 576, 753, 552, 552, 552, 552, 753, 753, 753, 753 };

	// Giá đồ
	private int[] prices = { 100, 100, 0, 0, 50, 30, 30, 50 };

	// Cờ kiểm tra
	private int isNotice = -1;
	
	// Mảng lưu skins và items
	
	private static boolean[] skins = new boolean[4];
	private static int[] items = new int[4];

	// Khởi tạo các giá trị
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo âm thanh hiệu ứng
		sound = new SoundEffect();

		// Tạo hình ảnh
		img_background = new Image("Data/Image/Shop.png");
		img_notice_shop = new Image("Data/Image/Notice_Shop.png");
		img_notice_price = new Image("Data/Image/Notice_Price.png");
		img_bt_back = new Image("Data/Image/Button_Back.png");
		img_bt_buy_no = new Image("Data/Image/Buy_no.png");
		img_bt_buy_yes = new Image("Data/Image/Buy_yes.png");
		img_bt_yes = new Image("Data/Image/Button_Yes.png");
		img_bt_no = new Image("Data/Image/Button_No.png");

		// Tạo hitbox
		bt_back = new Rectangle(bt_back_X, bt_back_Y, 130, 140);
		bt_yes = new Rectangle(bt_yes_X, bt_yes_Y, 128, 70);
		bt_no = new Rectangle(bt_no_X, bt_no_Y, 128, 70);
		bt_buy = new ArrayList<Rectangle>();
		for (int i = 0; i < bt_buy_XY.length / 2; i++) {
			bt_buy.add(new Rectangle(bt_buy_XY[i], bt_buy_XY[i + bt_buy_XY.length / 2], 78, 30));
		}
		
		money = 0;
		skins = new boolean[] {false, false, false, false};
        items = new int[] {0, 0, 0, 0};
	}

	// Cập nhật
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		money = SignIn.acc_detail.getMoney();
		
		skins = SignIn.acc_detail.Skins();
		items = SignIn.acc_detail.Items();
		
		// Thông mua hàng
		if (isNotice >= 0) {
			// Mua hàng
			if ((bt_yes.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_yes.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				if (money >= prices[isNotice]) {
					money -= prices[isNotice];
					
					// Thay đổi món đồ thành sở hữu
					processNotice();
					
				} else {
					// Thông báo không đủ tiền
					bt_yes.setLocation(bt_yes_X, 613);
					bt_no.setLocation(bt_no_X, 613);
					isNotice = -2;
				}
			}
			// Không mua hàng
			if ((bt_no.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_no.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				isNotice = -1;
			}
		}
		// Thông báo nạp thêm tiền
		else if (isNotice == -2) {
			// Nạp thêm tiền
			if ((bt_yes.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_yes.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				isNotice = -1;
			}
			// Không nạp thêm
			if ((bt_no.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_no.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				isNotice = -1;
			}
		}
		// Cửa hàng
		else {
			// Quay lại
			if ((bt_back
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_back.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
			}

			// Mua hàng
			for (int i = 0; i < bt_buy.size(); i++) {
				if ((bt_buy.get(i).intersects(
						new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
						|| bt_buy.get(i).contains(
								new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
						&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					sound.click();
					// Kiểm tra đã sở hữu chưa
					if((i >= 0 && i <= 3 && !skins[i]) || (i >= 4 && i <= 7)) {
						isNotice = i;
						bt_yes.setLocation(bt_yes_X, bt_yes_Y);
						bt_no.setLocation(bt_no_X, bt_no_Y);
					}
				}
			}

		}
		
		if(money != SignIn.acc_detail.getMoney()) {
			SignIn.acc_detail.setMoney(money);
			DetailDAO.getInstance().update(SignIn.acc_detail);
		}
	}

	// Hiển thị
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ hình nền
		img_background.draw();
		renderMoney();

		// Vẽ nút
		img_bt_back.draw(bt_back_X, bt_back_Y);

		// Xử lí vẽ nút theo giá trị của account
		for (int i = 0; i < bt_buy_XY.length / 2; i++) {
			// Kiểm tra có vật phẩm hay chưa
//			if() {
//				img_bt_buy_yes.draw(bt_buy_XY[i], bt_buy_XY[i + bt_buy_XY.length / 2]);
//			}else {
			img_bt_buy_no.draw(bt_buy_XY[i], bt_buy_XY[i + bt_buy_XY.length / 2]);
//			}
		}

		// Vẽ thông báo
		if (isNotice >= 0) {
			img_notice_shop.draw();
			renderPrice();
		}

		if (isNotice == -2) {
			img_notice_price.draw();
		}

		// Vẽ hitbox
		g.setColor(Color.red);
		if (isNotice >= 0 || isNotice == -2) {
			g.draw(bt_yes);
			g.draw(bt_no);
		} else {
			for (Rectangle bt : bt_buy) {
				g.draw(bt);
			}
			g.draw(bt_back);
		}

	}

	// Vẽ giá
	public void renderPrice() throws SlickException {
		int number = prices[isNotice];
		int width = 0;
		ArrayList<Image> img = new ArrayList<Image>();
		while (number > 0) {
			img.add(new Image("Data/Image/text_" + (number % 10) + ".png"));
			width += img.get(img.size() - 1).getWidth();
			number = number / 10;
		}
		int x = screenWidth / 2 - width / 2;
		int y = screenHeight / 2 - 16;
		for (int i = img.size() - 1; i >= 0; i--) {
			img.get(i).draw(x, y);
			x += img.get(i).getWidth();
		}
	}

	// Vẽ tiền
	public void renderMoney() throws SlickException {
		int number = money;
		int width = 0;
		ArrayList<Image> img = new ArrayList<Image>();
		while (number > 0) {
			img.add(new Image("Data/Image/text_" + (number % 10) + ".png"));
			width += img.get(img.size() - 1).getWidth();
			number = number / 10;
		}
		int x = 800 - width;
		int y = 851;
		for (int i = img.size() - 1; i >= 0; i--) {
			img.get(i).draw(x, y);
			x += img.get(i).getWidth();
		}
	}
	
	public void processNotice() {

        if (isNotice >= 0 && isNotice <= 3) {
            skins[isNotice] = true;
            String skinsCombined = Arrays.toString(skins).replaceAll("true", "1")
										                    .replaceAll("false", "0")
										                    .replaceAll("[\\[\\],]", "")
										                    .trim();
            SignIn.acc_detail.setSkins(skinsCombined);
        } else if (isNotice >= 4 && isNotice <= 7) {
            items[isNotice - 4]++;
            String itemsCombined = Arrays.toString(items).replaceAll("[\\[\\],]", "");
            SignIn.acc_detail.setItems(itemsCombined);
        }
        DetailDAO.getInstance().update(SignIn.acc_detail);
    }

	// Lấy id trạng thái
	@Override
	public int getID() {
		return 8;
	}
}
//851