package de.faerix.base;

import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;


public class test extends StateBasedGame {
	
	public static final String gamename = "Faerix";
	public static final int menu = 0;
	public static final int play = 1;
	

	public test(String gamename) {
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
	}

	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(menu).init(container, this);
		//this.getState(play).init(container, this);
		this.enterState(menu);
		
	}
		
	public static void main(String[] args) {
		AppGameContainer appcontainer;
		try {
			appcontainer = new AppGameContainer(new test(gamename));
			appcontainer.setDisplayMode(1500, 900, false);
			appcontainer.start();
			
		}catch(SlickException e) {
			e.printStackTrace();
		}
	}
		
}