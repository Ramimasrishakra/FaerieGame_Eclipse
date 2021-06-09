package de.faerix.base;

import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.SlickException;

public class faerie extends BasicGameState {

	private Image image;
	private Ellipse ellipse;
	private float rotation;
	private float velocity;
	private int yPosition = 200;
	private int xPosition = 200;
	private float[] fallingSparkX = new float[50];
	private float[] fallingSparkY = new float[50];
	private int[] stopping = new int[50];
	private int radius;
	private Ellipse[] sparkles = new Ellipse[50];
	private Ellipse sparkle1;
	private Ellipse sparkle2;
	
	public faerie(int state) {

	}

	public faerie() {
		
		this.ellipse = new Ellipse(this.xPosition, this.yPosition, 25, 25);
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			this.fallingSparkX[i] = this.xPosition - 25 + (float) random.nextInt(50);
			this.fallingSparkY[i] = this.yPosition - 25 + (float) random.nextInt(50);
			this.stopping[i] = 30 + (int) random.nextInt(25);
		}
		this.sparkle1 = new Ellipse(this.xPosition - 40, this.yPosition + 5, 3, 3);
		this.sparkle2 = new Ellipse(this.xPosition + 35, this.yPosition + 2, 1, 1);
		this.sparkle();
	}

	

	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		this.image = new Image("testdata/faerie.png");
		this.fall();
	}

	public void drawImage(Graphics g) {

		g.drawImage(this.image, this.xPosition - 25, this.yPosition - 25);
	}

	public void fall() {
		for (int i = 0; i< 50 ; i++) {
			Random random = new Random();
			if(this.fallingSparkY[i] > this.yPosition+this.stopping[i]) {
				this.fallingSparkY[i] = this.yPosition - 10 + (float)random.nextInt(20);
				this.fallingSparkX[i] = this.xPosition - 25 + (float)random.nextInt(50);
			}
			this.fallingSparkY[i] += random.nextFloat();
			this.fallingSparkX[i] +=  - 1 + (float)random.nextInt(3);
			this.sparkles[i].setLocation(this.fallingSparkX[i], this.fallingSparkY[i]);		
		}

	}

	public void sparkle() {
		// int sparks = 5 + (int)(Math.random() * ((50 - 5) + 1));
		for (int i = 0; i < 50; i++) {
			int size = 1 + (int) (Math.random() * ((5 - 1)));
			Ellipse sparkle = new Ellipse(this.fallingSparkX[i], this.fallingSparkY[i], size, size);
			this.sparkles[i] = sparkle;
		}
	}

	public void moveX(int xMove) {
		this.xPosition += xMove;
		this.ellipse.setCenterX(this.xPosition);

	}

	public void moveY(int yMove) {
		this.yPosition += yMove;
		this.ellipse.setCenterY(this.yPosition);
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		this.fall();
	}

	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(new Color(204, 204, 255, 0.2f));
		this.renderSparkle(g);
		this.drawImage(g);
		g.fill(this.ellipse);
		g.fill(this.sparkle1);
		g.fill(this.sparkle2);
	}

	public void renderSparkle(Graphics g) {
		for (Ellipse spark : this.sparkles) {
			g.fill(spark);
		}
		g.setColor(new Color(204, 204, 255, 0.2f));
	}

	public int getID() {
		return 1;
	}

}
