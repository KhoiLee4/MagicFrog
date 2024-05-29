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

import GameData.Account;
import GameData.AccountDAO;
import GameData.Detail;
import GameData.DetailDAO;

public class SignUp extends BasicGameState {
	// Nhạc nền, âm thanh hiệu ứng
	public GameMusic music;
	public SoundEffect sound;

	// Tài khoản
	StringBuilder username;
	ArrayList<Image> img_username;

	// Mật khẩu
	StringBuilder password;
	ArrayList<Image> img_password;

	// Xác nhận mật khẩu
	StringBuilder confirmPassword;
	ArrayList<Image> img_confirmPassword;

	// Hình nền
	private Image img_background;

	// Hình các nút
	private Image img_bt_SignUp;

	// Hitbox các nút
	private Rectangle bt_SignUp;

	// Tọa độ các nút
	private int bt_SignUp_X = 405;
	private int bt_SignUp_Y = 790;

	// Hitbox các ô nhập
	private Rectangle box_username;
	private Rectangle box_password;
	private Rectangle box_confirmPassword;

	// Tạo độ các ô nhập
	private int box_username_X = 253;
	private int box_username_Y = 453;

	private int box_password_X = 253;
	private int box_password_Y = 573;

	private int box_confirmPassword_X = 253;
	private int box_confirmPassword_Y = 693;

	// Con trỏ chuột
	private Rectangle cursor;

	// Vị trí con trỏ chuột
	private int cursorPosition = 0;
	boolean flagCursor = true;
	
	// Lưu ý khi tạo mật khẩu
	private Image[] notice_pass;
	private boolean[] show_notice;
	
	private Rectangle bt_back = null;
	private int bt_back_X = 900;
	private int bt_back_Y = 840;

	// Khởi tạo
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo nhạc nền, âm thanh hiệu ứng
		music = new GameMusic();
//		music.playMusic();
		sound = new SoundEffect();

		// Tạo hình ảnh
		img_background = new Image("Data/Image/Background_SignUp.png");
		img_bt_SignUp = new Image("Data/Image/Button_SignUp.png");

		// Tạo hitbox
		bt_SignUp = new Rectangle(bt_SignUp_X, bt_SignUp_Y, 240, 70);
		box_username = new Rectangle(box_username_X, box_username_Y, 500, 38);
		box_password = new Rectangle(box_password_X, box_password_Y, 500, 38);
		box_confirmPassword = new Rectangle(box_confirmPassword_X, box_confirmPassword_Y, 500, 38);

		// Tạo con trỏ
		cursor = new Rectangle(0, 0, 5, 32);

		// Tạo các đối tượng chứa
		username = new StringBuilder();
		password = new StringBuilder();
		confirmPassword = new StringBuilder();
		img_username = new ArrayList<Image>();
		img_password = new ArrayList<Image>();
		img_confirmPassword = new ArrayList<Image>();
		
		//Thông báo khi tạo mật khẩu
		notice_pass = new Image[] {new Image("Data/Image/Notice_pass.png"),
									new Image("Data/Image/Notice_pass2.png")};
		show_notice = new boolean[] {false, false};
		bt_back = new Rectangle(bt_back_X, bt_back_Y, 130, 140);
	}

	// Cập nhật
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			// Trỏ vào ô username
			if (box_username
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| box_username.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))) {
				cursorPosition = 1;
			}
			// Trỏ vào ô password
			else if (box_password
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| box_password.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))) {
				cursorPosition = 2;
			}
			// Trỏ vào ô confirm password
			else if (box_confirmPassword
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| box_confirmPassword.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))) {
				cursorPosition = 3;
			}
			// Bỏ trỏ chuột
			else {
				cursorPosition = 0;
			}
		}

		// Bấm nút đăng ký
		if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && (bt_SignUp
				.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_SignUp.contains(
						new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))) {
			// Kiểm tra tài khoản, mật khẩu
			sound.click();
			if (checkPassword()) {
				Account acc = new Account(username.toString(), password.toString());
				Detail acc_detail = new Detail(username.toString());
				
				AccountDAO.getInstance().insert(acc);
				DetailDAO.getInstance().insert(acc_detail);
				
				sbg.enterState(6, new FadeOutTransition(), new FadeInTransition());
			}
		}

		// Nhập username
		if (cursorPosition == 1) {
			input(container, username, img_username);
		}

		// Nhập password
		if (cursorPosition == 2) {
			input(container, password, img_password);
		}

		// Nhập confirm password
		if (cursorPosition == 3) {
			input(container, confirmPassword, img_confirmPassword);
		}
		
		
		
	}

	// Hiển thị
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ hình nền
		img_background.draw();
		img_bt_SignUp.draw(bt_SignUp_X, bt_SignUp_Y);

		g.setColor(Color.transparent);

		// Vẽ hitbox
		g.draw(bt_SignUp);
		g.draw(box_username);
		g.draw(box_password);
		g.draw(box_confirmPassword);

		// Vẽ chữ
		int x1 = box_username_X + 5, y1 = box_username_Y + 3, x2 = box_password_X + 5, y2 = box_password_Y + 3,
				x3 = box_confirmPassword_X + 5, y3 = box_confirmPassword_Y + 3;
		// tài khoản
		if (img_username != null) {
			for (Image img : img_username) {
				img.draw(x1, y1);
				x1 += img.getWidth();
			}
		}

		// Mật khẩu
		if (img_password != null) {
			for (Image img : img_password) {
				img.draw(x2, y2);
				x2 += img.getWidth();
			}
		}

		// Xác nhận mật khẩu
		if (img_confirmPassword != null) {
			for (Image img : img_confirmPassword) {
				img.draw(x3, y3);
				x3 += img.getWidth();
			}
		}

		// Vẽ con trỏ chuột
		g.setColor(Color.yellow);
		g.fill(cursor);
		if (cursorPosition == 1) {
			cursor.setLocation(x1, y1);
			g.draw(cursor);
		} else if (cursorPosition == 2) {
			cursor.setLocation(x2, y2);
			g.draw(cursor);
		} else if (cursorPosition == 3) {
			cursor.setLocation(x3, y3);
			g.draw(cursor);
		} else {
			cursor.setLocation(-cursor.getWidth(), -cursor.getHeight());
		}
		
		for(int i = 0; i < 2; i++) {
			if(show_notice[i]) {
				notice_pass[i].draw();
				if ((bt_back.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
						|| bt_back
								.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
						&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					sound.click();
					show_notice[i] = false;
				}
				
			}
		}
	}

	// Kiểm tra mật khẩu
	public boolean checkPassword() {
		if (password.length() < 8) {
	        System.out.println("Password must be at least 8 characters long.");
	        show_notice[0] = true;
	        return false;
	    }

	    // Kiểm tra mật khẩu và xác nhận mật khẩu có khớp nhau không
	    if (!password.toString().equals(confirmPassword.toString())) {
	        System.out.println("Passwords do not match.");
	        show_notice[1] = true;
	        return false;
	    }

//	    // Kiểm tra mật khẩu có chứa ít nhất một ký tự đặc biệt
//	    if (!password.matches(".*[!@#$%^&*()].*")) {
//	        System.out.println("Password must contain at least one special character.");
//	        return false;
//	    }
//
//	    // Kiểm tra mật khẩu có chứa ít nhất một chữ cái viết hoa
//	    if (!password.matches(".*[A-Z].*")) {
//	        System.out.println("Password must contain at least one uppercase letter.");
//	        return false;
//	    }
//
//	    // Kiểm tra mật khẩu có chứa ít nhất một chữ cái viết thường
//	    if (!password.matches(".*[a-z].*")) {
//	        System.out.println("Password must contain at least one lowercase letter.");
//	        return false;
//	    }
//
//	    // Kiểm tra mật khẩu có chứa ít nhất một chữ số
//	    if (!password.matches(".*[0-9].*")) {
//	        System.out.println("Password must contain at least one digit.");
//	        return false;
//	    }

	    // Nếu tất cả các kiểm tra đều thành công
	    return true;
	}

	// Nhập kí tự vào
	public void input(GameContainer container, StringBuilder inputTest, ArrayList<Image> image) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_A)) {
			inputTest.append('A');
			image.add(new Image("Data/Image/text_A.png"));
		} else if (input.isKeyPressed(Input.KEY_B)) {
			inputTest.append('B');
			image.add(new Image("Data/Image/text_B.png"));
		} else if (input.isKeyPressed(Input.KEY_C)) {
			inputTest.append('C');
			image.add(new Image("Data/Image/text_C.png"));
		} else if (input.isKeyPressed(Input.KEY_D)) {
			inputTest.append('D');
			image.add(new Image("Data/Image/text_D.png"));
		} else if (input.isKeyPressed(Input.KEY_E)) {
			inputTest.append('E');
			image.add(new Image("Data/Image/text_E.png"));
		} else if (input.isKeyPressed(Input.KEY_F)) {
			inputTest.append('F');
			image.add(new Image("Data/Image/text_F.png"));
		} else if (input.isKeyPressed(Input.KEY_G)) {
			inputTest.append('G');
			image.add(new Image("Data/Image/text_G.png"));
		} else if (input.isKeyPressed(Input.KEY_H)) {
			inputTest.append('H');
			image.add(new Image("Data/Image/text_H.png"));
		} else if (input.isKeyPressed(Input.KEY_I)) {
			inputTest.append('I');
			image.add(new Image("Data/Image/text_I.png"));
		} else if (input.isKeyPressed(Input.KEY_J)) {
			inputTest.append('J');
			image.add(new Image("Data/Image/text_J.png"));
		} else if (input.isKeyPressed(Input.KEY_K)) {
			inputTest.append('K');
			image.add(new Image("Data/Image/text_K.png"));
		} else if (input.isKeyPressed(Input.KEY_L)) {
			inputTest.append('L');
			image.add(new Image("Data/Image/text_L.png"));
		} else if (input.isKeyPressed(Input.KEY_M)) {
			inputTest.append('M');
			image.add(new Image("Data/Image/text_M.png"));
		} else if (input.isKeyPressed(Input.KEY_N)) {
			inputTest.append('N');
			image.add(new Image("Data/Image/text_N.png"));
		} else if (input.isKeyPressed(Input.KEY_O)) {
			inputTest.append('O');
			image.add(new Image("Data/Image/text_O.png"));
		} else if (input.isKeyPressed(Input.KEY_P)) {
			inputTest.append('P');
			image.add(new Image("Data/Image/text_P.png"));
		} else if (input.isKeyPressed(Input.KEY_Q)) {
			inputTest.append('Q');
			image.add(new Image("Data/Image/text_Q.png"));
		} else if (input.isKeyPressed(Input.KEY_R)) {
			inputTest.append('R');
			image.add(new Image("Data/Image/text_R.png"));
		} else if (input.isKeyPressed(Input.KEY_S)) {
			inputTest.append('S');
			image.add(new Image("Data/Image/text_S.png"));
		} else if (input.isKeyPressed(Input.KEY_T)) {
			inputTest.append('T');
			image.add(new Image("Data/Image/text_T.png"));
		} else if (input.isKeyPressed(Input.KEY_U)) {
			inputTest.append('U');
			image.add(new Image("Data/Image/text_U.png"));
		} else if (input.isKeyPressed(Input.KEY_V)) {
			inputTest.append('V');
			image.add(new Image("Data/Image/text_V.png"));
		} else if (input.isKeyPressed(Input.KEY_W)) {
			inputTest.append('W');
			image.add(new Image("Data/Image/text_W.png"));
		} else if (input.isKeyPressed(Input.KEY_X)) {
			inputTest.append('X');
			image.add(new Image("Data/Image/text_X.png"));
		} else if (input.isKeyPressed(Input.KEY_Y)) {
			inputTest.append('Y');
			image.add(new Image("Data/Image/text_Y.png"));
		} else if (input.isKeyPressed(Input.KEY_Z)) {
			inputTest.append('Z');
			image.add(new Image("Data/Image/text_Z.png"));
		} else if (input.isKeyPressed(Input.KEY_0)) {
			inputTest.append('0');
			image.add(new Image("Data/Image/text_0.png"));
		} else if (input.isKeyPressed(Input.KEY_1)) {
			inputTest.append('1');
			image.add(new Image("Data/Image/text_1.png"));
		} else if (input.isKeyPressed(Input.KEY_2)) {
			inputTest.append('2');
			image.add(new Image("Data/Image/text_2.png"));
		} else if (input.isKeyPressed(Input.KEY_3)) {
			inputTest.append('3');
			image.add(new Image("Data/Image/text_3.png"));
		} else if (input.isKeyPressed(Input.KEY_4)) {
			inputTest.append('4');
			image.add(new Image("Data/Image/text_4.png"));
		} else if (input.isKeyPressed(Input.KEY_5)) {
			inputTest.append('5');
			image.add(new Image("Data/Image/text_5.png"));
		} else if (input.isKeyPressed(Input.KEY_6)) {
			inputTest.append('6');
			image.add(new Image("Data/Image/text_6.png"));
		} else if (input.isKeyPressed(Input.KEY_7)) {
			inputTest.append('7');
			image.add(new Image("Data/Image/text_7.png"));
		} else if (input.isKeyPressed(Input.KEY_8)) {
			inputTest.append('8');
			image.add(new Image("Data/Image/text_8.png"));
		} else if (input.isKeyPressed(Input.KEY_9)) {
			inputTest.append('9');
			image.add(new Image("Data/Image/text_9.png"));
		} else if (input.isKeyPressed(Input.KEY_BACK)) {
			if (inputTest.length() > 0) {
				inputTest.deleteCharAt(inputTest.length() - 1);
				image.remove(image.size() - 1);
			}
		}
		if (inputTest.length() > 12 || image.size() > 12) {
			inputTest.deleteCharAt(inputTest.length() - 1);
			image.remove(image.size() - 1);
		}
	}

	// Lấy id trạng trái
	@Override
	public int getID() {
		return 7;
	}
}
