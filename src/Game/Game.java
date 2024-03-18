package Game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame implements gameConfig {
	
	private static String gameTitle = "MagicFrog";
	private static AppGameContainer game;

	public Game() {
		super(gameTitle);
	}
	public Game(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new Menu());
		this.addState(new PlayGame());
		this.addState(new Setting());
		this.addState(new Leaderboard());
		this.addState(new GameWin());
		this.addState(new GameOver());
	}

	public void start() {
		try {
			game = new AppGameContainer(new Game());
			game.setDisplayMode(screenWidth, screenHeight, false);
//			game.setTargetFrameRate(60);
//			game.setVSync(true);
			game.setShowFPS(false);
			game.start();
			
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
