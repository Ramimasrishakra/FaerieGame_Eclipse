package de.faerix.base;

import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class Play extends BasicGameState {

	Image startBild;
	Image image;
	Ellipse ellipse;
	boolean quit = false;
	private float rotation;
	private float velocity; 
	float faeriePositionX = 200;
	float faeriePositionY = 200;
	float[] fallingSparkX = new float[50];
	float[] fallingSparkY = new float[50];
	int[] stopping = new int[50];
	int radius;
	Ellipse[] sparkles = new Ellipse[50];
	Ellipse sparkle1;
	Ellipse sparkle2;

	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		image = new Image("testdata/faerie.png");
		startBild = new Image("testdata/startBild.png");
		this.ellipse = new Ellipse(this.faeriePositionX, this.faeriePositionY, 25, 25);
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			this.fallingSparkX[i] = this.faeriePositionX - 25 + (float) random.nextInt(50);
			this.fallingSparkY[i] = this.faeriePositionY - 25 + (float) random.nextInt(50);
			this.stopping[i] = 30 + (int) random.nextInt(25);
		}
		this.sparkle1 = new Ellipse(this.faeriePositionX - 40, this.faeriePositionY + 5, 3, 3);
		this.sparkle2 = new Ellipse(this.faeriePositionX + 35, this.faeriePositionY + 2, 1, 1);
		this.sparkle();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		startBild.draw();
		this.renderSparkle(g);
		g.drawImage(this.image, this.faeriePositionX-25, this.faeriePositionY-25);
		g.fill(this.ellipse);
		g.fill(this.sparkle1);
		g.fill(this.sparkle2);

	}

	public void renderSparkle(Graphics g) {
		for (Ellipse spark : this.sparkles) {
			g.fill(spark);			
		}
		g.setColor(new Color(204,204,255, 0.2f));
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		this.fall();
	
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
		} else if (input.isKeyDown(Input.KEY_UP)) {
			this.moveY(-1);
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			this.moveY(1);
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			this.moveX(+1);
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			this.moveX(-1);
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

	public void fall() {
		for (int i = 0; i < 50; i++) {
			Random random = new Random();
			if (this.fallingSparkY[i] > this.faeriePositionY + this.stopping[i]) {
				this.fallingSparkY[i] = this.faeriePositionY - 10 + (float) random.nextInt(20);
				this.fallingSparkX[i] = this.faeriePositionX - 25 + (float) random.nextInt(50);
			}
			this.fallingSparkY[i] += random.nextFloat();
			this.fallingSparkX[i] += -1 + (float) random.nextInt(3);
			this.sparkles[i].setLocation(this.fallingSparkX[i], this.fallingSparkY[i]);
		}
	}
	
	public void moveX(int xMove) {
		this.faeriePositionX += xMove;
		this.ellipse.setCenterX(this.faeriePositionX);
		
	}
	
	public void moveY( int yMove) {
		this.faeriePositionY += yMove;
		this.ellipse.setCenterY(this.faeriePositionY);
	}
	

	public int getID() {
		return 1;
	}

}
