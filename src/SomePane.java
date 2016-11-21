import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;


public class SomePane extends GraphicsPane implements ActionListener{
	
	private final int PROGRAM_WIDTH = 1024; //Resolution is 1024x768
	private final int PROGRAM_HEIGHT = 768;
	
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GImage img;
	private Level lvl;
	private Turret test;
	private Turret test2;
	private Timer move;
	
	private GRect pauseBox;
	private GLabel pauseMessage;
	
	private int currentMouseX;
	private int currentMouseY;
	
	
	
	public SomePane(MainApplication app) {
		this.program = app;
		lvl = new Level(program);
		lvl.getGameObject().generateBuildings(program);
		lvl.getGameObject().generateTurrets(program);
		this.currentMouseX = 0;
		this.currentMouseY = 0;
		
		// TODO do all of this better, no hard coding and make pause window better (image instead of making with GRect and GLabel)
		this.pauseBox = new GRect(this.PROGRAM_WIDTH/2 - 200, this.PROGRAM_HEIGHT/2 - 200, 400, 200);
		this.pauseBox.setColor(Color.red);
		this.pauseBox.setFilled(true);
		this.pauseMessage = new GLabel("Press Spacebar to Resume", 320, 280);
		this.pauseMessage.setFont("Arial-Bold-30");
		
	}
	
	
	public void showContents() {
		
		// Draws the buildings to the stage screen
		for(Structure building : lvl.getGameObject().getBuildingsOnStage()){
			if(building != null){
				building.sprite.scale(0.4, 0.4);
				building.draw(program);
			}
		}
		
		 //Draws the turrets to the stage screen
		for(Turret turret : lvl.getGameObject().getTurretsOnStage()){
			if(turret != null){
				turret.sprite.scale(0.3, 0.3);
				turret.draw(program);
			}
		}
		
		this.run();
	
	}
	
	public void run(){
		move = new Timer(80, (ActionListener) this);
		move.setInitialDelay(1000);
		move.start();

	}
	
	public void actionPerformed(ActionEvent e){
		lvl.getGameObject().generateEnemyMissile("Sprites/Missile_placeholder.png", program);
		lvl.getGameObject().checkForHits();
		for(Missile missile: lvl.getGameObject().getMissilesOnStage()){
			if(missile != null){
				missile.draw(program);
				missile.move();
			}
			
			//Sets the missile object to null if it goes off screen, to hopefully evoke GC to destroy the object
			if(missile.getY() < 0 || missile.getY() > this.PROGRAM_HEIGHT || missile.getX() < 0 || missile.getX() > this.PROGRAM_WIDTH){
				missile = null;
			}
		}
	}

	@Override
	public void hideContents() {
		program.remove(img);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == img) {
			program.switchBack();
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e){
		this.currentMouseX = e.getX();
		this.currentMouseY = e.getY();
	}
	
	
	@Override
	public void keyTyped(KeyEvent e){
		System.out.println(e.getKeyChar()); //TODO remove, testing only
		
		switch(Character.toLowerCase(e.getKeyChar())){
		
		// Turret Q
		case 'q':
			lvl.getGameObject().getTurretsOnStage()[0].fireMissile(program, lvl, 100, 100, lvl.getmissiles());
			break;
		
		// Turret W
		case 'w':
			lvl.getGameObject().getTurretsOnStage()[1].fireMissile(program, lvl, 100, 100, lvl.getmissiles());
			break;
	   
		// Turret E
		case 'e':
			lvl.getGameObject().getTurretsOnStage()[2].fireMissile(program, lvl, 100, 100, lvl.getmissiles());
			break;
		
		// Turret R
		case 'r':
			lvl.getGameObject().getTurretsOnStage()[3].fireMissile(program, lvl, 100, 100, lvl.getmissiles());
			break;

		// Pause or resume the game with a space bar press
		case ' ':
			if(!lvl.isPaused()){
				lvl.setPaused();           // sets paused to true
				this.move.stop();          // stops timer, thus pauses the game
				program.add(this.pauseBox);
				program.add(this.pauseMessage);
			}
			else{
				program.remove(this.pauseBox);
				program.remove(this.pauseMessage);
				lvl.setPaused();           // sets paused to false
				move.setInitialDelay(50);  // 50ms delay before restart
				this.move.start();         // starts timer, thus resumes the game
			}
			break;
		}
		
	}

}
