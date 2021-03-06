package de.faerix.base;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState{
	
	Play play;
	
	Image playNow;
	Image exitGame;
	Image startBild;
	Music bgMusic;
	Sound click;
	
	
	
	public Menu(int state) {
		
	}
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException{
		startBild = new Image("testdata/startBild.png");
		//exitGame = new Image("testdata/Exit.png");
		playNow = new Image("testdata/playNow.png");
		bgMusic = new Music("testdata/sound/Waiting Song.wav");
		bgMusic.loop();
		click = new Sound("testdata/sound/click.wav");
		this.play = new Play(1);
		
		
		
	}
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString("Welcom to Faerie",100,50);
		startBild.draw();
		playNow.draw(50,50);
		//exitGame.draw(100,200);
		
		
		
	}
	public void update(GameContainer container, StateBasedGame sbg, int  delta) throws SlickException{
		
		int posX =  Mouse.getX();
		int posY = Mouse.getY();
		if ((posX > 500 && posX < 1100)&& (posY > 450 && posY < 1000) ) {
			if(Mouse.isButtonDown(0)) {
				click.play();
				sbg.enterState(1);
				
				
				
			}
				
			}
		}

	public int getID() {
		return 0;
	}
	
}
