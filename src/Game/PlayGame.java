package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayGame extends BasicGameState{
	
	public Frog frog = null;
	
	public static GameContainer game_container = null;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		frog = new Frog();
		
		game_container = container;
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
//		sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		frog.update(delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawString("Play", 100, 100);
		
		frog.render();
		
	}

	@Override
	public int getID() {
		return 1;
	}

}