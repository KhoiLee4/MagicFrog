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

public class SignIn extends BasicGameState {
	// Nhạc nền, âm thanh hiệu ứng
	private GameMusic music;
	private SoundEffect sound;

	// Tài khoản
	static StringBuilder username;
	private ArrayList<Image> img_username;

	// Mật khẩu
	private StringBuilder password;
	private ArrayList<Image> img_password;

	// Hình nền
	private Image img_background;

	// Hình các nút
	private Image img_bt_SignIn;
	private Image img_bt_SignUp;

	// Hitbox các nút
	private Rectangle bt_SignIn;
	private Rectangle bt_SignUp;

	// Tọa độ các nút
	private int bt_SignIn_X = 580;
	private int bt_SignIn_Y = 770;

	private int bt_SignUp_X = 255;
	private int bt_SignUp_Y = 770;

	// Hitbox các ô nhập
	private Rectangle box_username;
	private Rectangle box_password;

	// Tạo độ các ô nhập
	private int box_username_X = 253;
	private int box_username_Y = 528;

	private int box_password_X = 253;
	private int box_password_Y = 648;

	// Con trỏ chuột
	private Rectangle cursor;

	// Vị trí con trỏ chuột
	private int cursorPosition = 0;

	boolean check_pass;
	boolean check_null;
	boolean check_exist;
	
	private Image img_check_pass = null;
	private Image img_check_null = null;
	private Image img_check_exist = null;
	
	private Rectangle bt_back = null;
	private int bt_back_X = 900;
	private int bt_back_Y = 840;
	
	private Detail acc_detail;

	// Khởi tạo
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo nhạc nền, âm thanh hiệu ứng
		music = new GameMusic();
//		music.playMusic();
		sound = new SoundEffect();

		// Tạo hình ảnh
		img_background = new Image("Data/Image/Background_SignIn.png");
		img_bt_SignIn = new Image("Data/Image/Button_SignIn.png");
		img_bt_SignUp = new Image("Data/Image/Button_SignUp.png");

		// Tạo hitbox
		bt_SignIn = new Rectangle(bt_SignIn_X, bt_SignIn_Y, 225, 70);
		bt_SignUp = new Rectangle(bt_SignUp_X, bt_SignUp_Y, 240, 70);
		box_username = new Rectangle(box_username_X, box_username_Y, 500, 38);
		box_password = new Rectangle(box_password_X, box_password_Y, 500, 38);

		// Tạo con trỏ
		cursor = new Rectangle(0, 0, 5, 32);

		// Tạo các đối tượng chứa
		username = new StringBuilder();
		password = new StringBuilder();
		img_username = new ArrayList<Image>();
		img_password = new ArrayList<Image>();

		check_pass = true;
		check_null = false;
		check_exist = true;
		img_check_pass = new Image("Data/Image/Check_pass.png");
		img_check_null = new Image("Data/Image/Check_Null.png");
		img_check_exist = new Image("Data/Image/Check_pass.png");
		
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
			// Bỏ trỏ chuột
			else {
				cursorPosition = 0;
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

		// Bấm nút đăng nhập
		if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && (bt_SignIn
				.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_SignIn.contains(
						new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))) {
			// Kiểm tra tài khoản, mật khẩu
			sound.click();
			if (checkAccount()) {
				acc_detail = DetailDAO.getInstance().selectByUsername(new Detail(username.toString()));

				music.setMusic(acc_detail.isGameMusic());
				sound.setSound(acc_detail.isSoundEffect());

				username = new StringBuilder();
				password = new StringBuilder();
				
				img_username.clear();
				img_password.clear();

				sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
			}
			

		}

		// Bấm nút đăng ký
		if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && (bt_SignUp
				.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_SignUp.contains(
						new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))) {
			sound.click();
			sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
		}
	}

	// Hiển thị
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ hình nền
		img_background.draw();
		img_bt_SignIn.draw(bt_SignIn_X, bt_SignIn_Y);
		img_bt_SignUp.draw(bt_SignUp_X, bt_SignUp_Y);

		// Vẽ hitbox
		g.setColor(Color.transparent);
		g.draw(bt_SignIn);
		g.draw(bt_SignUp);
		g.draw(box_username);
		g.draw(box_password);

		// Vẽ chữ
		int x1 = box_username_X + 5, y1 = box_username_Y + 3, x2 = box_password_X + 5, y2 = box_password_Y + 3;

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

		// Vẽ con trỏ chuột
		g.setColor(Color.yellow);
		g.fill(cursor);
		if (cursorPosition == 1) {
			cursor.setLocation(x1, y1);
			g.draw(cursor);
		} else if (cursorPosition == 2) {
			cursor.setLocation(x2, y2);
			g.draw(cursor);
		} else {
			cursor.setLocation(-cursor.getWidth(), -cursor.getHeight());
		}

		
		if (!check_pass) {
			img_check_pass.draw();
			if ((bt_back
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_back.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				check_pass = true;
			}
		}
		
		
		if (check_null) {
			img_check_null.draw();
			if ((bt_back
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_back.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				check_null = false;
			}
		}

		
		if (!check_exist) {
			img_check_exist.draw();
			if ((bt_back
					.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
					|| bt_back.contains(
							new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
					&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sound.click();
				check_exist = true;
			}
		}

		
	}

	// Kiểm tra tài khoản, mật khẩu
	public boolean checkAccount() {
		if (username.length() == 0) {
			check_null = true;
			return false;
		} else if(AccountDAO.getInstance().selectByUsername(new Account(username.toString(),"")) == null) {
			check_exist = false;
			return false;
		}
		else{
			Account acc = new Account(username.toString(), "");
			String pass = AccountDAO.getInstance().selectByUsername(acc).getPassword();
			if (password.toString().equals(pass))
				return true;
			check_pass = false;
			return false;
		}
		
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
		return 6;
	}

}
