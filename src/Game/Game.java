package Game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame implements gameConfig {
	// Tiêu đề cửa sổ (tên game)
	private static String gameTitle = "MagicFrog";
	// Phần tử khởi chạy
	private static AppGameContainer game;

	// Khởi tạo
	public Game() {
		super(gameTitle);
	}

	public Game(String name) {
		super(name);
	}

	// Danh sách các trạng thái game
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// Màn hình Menu
		this.addState(new Menu());

		// Màn hình chơi
		this.addState(new PlayGame());

		// Màn hình cài đặt
		this.addState(new Setting());

		// Màn hình kĩ lục
		this.addState(new Leaderboard());

		// Màn hình thắng
		this.addState(new GameWin());

		// Màn hình thua
		this.addState(new GameOver());
	}

	// Chạy trò chơi
	public void start() {
		// Bắt ngoại lệ
		try {
			game = new AppGameContainer(new Game());
			game.setDisplayMode(screenWidth, screenHeight, false);
//			game.setTargetFrameRate(144);
//			game.setVSync(true);
			game.setShowFPS(false);
			
			// Chạy trò chơi
			game.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
