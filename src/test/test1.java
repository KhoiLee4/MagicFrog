package test;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class test1 extends BasicGame{

	private Image alien = null;
	private Shape shape = null;
	private Circle circle = null;
	private boolean collides = false;
	private boolean spaceE = false;
	
	public test1(String title) {
		super(title);
	}

	// Khoi tao tai nguyen cho game
	// container la khung hinh chua game
	@Override
	public void init(GameContainer container) throws SlickException {
		alien = new Image("C:/Users/ADMIN/Pictures/Saved Pictures/desktop-wallpaper-tuf-gaming-rebrand-on-behance-asus-tuf-a15.jpg");
		circle = new Circle(50, 150, 10);
	
		// danh sach tao do cac diem cua shappe
		float[] points = new float[]{50,150,100,100,150,150,125,200,75,200};
		shape = new Polygon(points);
		
	}

	// Cap nhat sau moi chu ki cho game
	// Delta la thoi gian moi chu ki (mili giay)
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// thay doi toa do cua circle theo vi tri chuot
		circle.setCenterX(container.getInput().getMouseX());
		circle.setCenterY(container.getInput().getMouseY());
	
		//xet xem hinh circle cos nam trong shape khong
		collides = shape.contains(circle);
		
		
		// Nhan su kien tu ban phim
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_SPACE)) {
			spaceE = true;
		}
		else {
			spaceE = false;
		}
		
		
	}

	// Hien thi cap nhat
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// ghi chu Hello!!! len mang hinh
		g.drawString("Hello!!!", 50, 50);
		
		for(int i = 0; i < 5; i++)
		{
			// cat hinh va ve hinh
			alien.getSubImage(0, 0, 50, 50).draw(50 + 50 * i, 0);
		}
		
		// dat mau va to hinh tron
		g.setColor(new Color(255,255,255));
		g.fill(circle);

		// dat lai mau va ve hinh circle, hinh shape
		g.setColor(new Color(255,255,0));
		g.draw(circle);
		g.draw(shape);
		
		// in collides len man hinh
		g.drawString("Collides: " + collides, 50, 100);
		
		// in spaceE len man hinh
		g.drawString("SpaceE: " + spaceE, 50, 250);
		
	
	}



	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new test1("KKKKKK"));
		
		app.setDisplayMode(800, 600, false);
		app.setAlwaysRender(true);
		
		
		app.start();
	}
}
