package Game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public final class MapLand extends Map implements gameConfig {

	private ArrayList<Obstacles> obstacles;
//	private int countPlank = 4;
	private Random randomSpawn = null;
	int randomTime = 0;

	// Khởi tạo
	protected MapLand(float x) throws SlickException {
		super(null, x, 0);

		this.background = new Image("Data/Image/MapLand.png");
		this.pos_y = screenHeight - background.getHeight();

		randomSpawn = new Random();

		obstacles = new ArrayList<Obstacles>();

		System.out.println("land map");

	}

	protected MapLand(float x, float y) throws SlickException {
		super(null, x, 0);

		randomSpawn = new Random();

		this.background = new Image("Data/Image/MapLand" + (randomSpawn.nextInt(0, 2) + 1) + ".png");

		this.pos_y = y - this.background.getHeight();

		obstacles = new ArrayList<Obstacles>();

		System.out.println("land map");

	}

	// Cập nhật map
	public void update(int delta) throws SlickException {

		// xử lí các đối tượng cảu Land Map

		// Di chuyển map
		super.update(delta);
	}

	// Vẽ map
	public void render() {
//		GameContainer g = PlayGame.gameContainer;
		super.render();

	}

}