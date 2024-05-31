package Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import GameData.Detail;
import GameData.DetailDAO;

//
//class Score {
//    private String username;
//    private int score;
//
//    public Score(String username, int score) {
//        this.username = username;
//        this.score = score;
//    }
//
//    public String getPlayerName() {
//        return username;
//    }
//
//    public int getScore() {
//        return score;
//    }
//    @Override
//    public String toString() {
//    	// TODO Auto-generated method stub
//    	return username + " " +score;
//    }
//}

public class Leaderboard extends BasicGameState {
	// Nhạc nền, âm thanh hiệu ứng
	private SoundEffect sound;

	// Hình nền
	private Image img_background = null;

	// Hình các nút
	private Image img_bt_back = null;

	// Hitbox các nút
	private Rectangle bt_back = null;

	// Tọa độ các nút
	private int bt_back_X = 30;
	private int bt_back_Y = 830;

	// private List<Score> scores;
	ArrayList<Detail> list_username = null;

	// Biến để cài đặt font chữ
	private UnicodeFont font;

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// Tạo nhạc nền, âm thanh hiệu ứng
		sound = new SoundEffect();

		// Tạo hình ảnh
		img_background = new Image("Data/Image/Leaderboard.png");
		img_bt_back = new Image("Data/Image/Button_Back.png");

		// Tạo hitbox
		bt_back = new Rectangle(bt_back_X, bt_back_Y, 130, 140);

		// Cài đặt font chữ
		try {
			font = new UnicodeFont("Data/Font/SundayMilk.ttf", 40, true, false);
			font.addAsciiGlyphs();
			font.getEffects().add(new ColorEffect(java.awt.Color.white));
			font.loadGlyphs();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Lấy danh sách điểm từ csdl
		// scores = new ArrayList<>();
		list_username = DetailDAO.getInstance().selectAll();

		// int i = 0;
		// while (i < list_username.size() && i < 10) {
		// String user = list_username.get(i).getUsername();
		// int score = list_username.get(i).getMaxScore();
		// scores.add(new Score(user, score));
		// i++;
		// }

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		// Chuyển sang setting
		if ((bt_back.intersects(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f))
				|| bt_back
						.contains(new Circle(container.getInput().getMouseX(), container.getInput().getMouseY(), 0.5f)))
				&& container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.click();
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}
		list_username = DetailDAO.getInstance().selectAll();
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// Vẽ hình nền
		img_background.draw();
		img_bt_back.draw(bt_back_X, bt_back_Y);

		// Vẽ hitbox
		g.setColor(Color.transparent);
		g.draw(bt_back);

		g.setColor(new Color(40, 144, 255));
		g.setFont(font);

		drawScoreboard(g);
	}

	@Override
	public int getID() {
		return 3;
	}

	private void drawScoreboard(Graphics g) {

		int x = 350; // Xác định tọa độ X bắt đầu của văn bản
		int y = 320; // Xác định tọa độ Y bắt đầu của văn bản

		// Vẽ danh sách điểm số
		int i = 0;
		while (i < list_username.size() && i < 8) {
			g.drawString(list_username.get(i).getUsername(), x, y);
			g.drawString("" + list_username.get(i).getMaxScore(), x + 280, y);
			y += 72; // Tăng khoảng cách giữa các dòng văn bản
			i++;
		}
	}

}
