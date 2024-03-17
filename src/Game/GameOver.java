package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState{

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
//		sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawString("Lose!!!", 100, 100);
		
	}

	@Override
	public int getID() {
		return 5;
	}

}