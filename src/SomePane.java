import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;


public class SomePane extends GraphicsPane implements ActionListener{
	
	private final int PROGRAM_WIDTH = 1024; //Resolution is 1024x768
	private final int PROGRAM_HEIGHT = 768;
	
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GImage img;
	private Level lvl;
	private Turret test;
	private Turret test2;
	private Timer move;
	
	private int currentMouseX;
	private int currentMouseY;
	
	
	
	public SomePane(MainApplication app) {
		this.program = app;
		lvl = new Level(program);
		lvl.getGameObject().generateBuildings(program);
		lvl.getGameObject().generateTurrets(program);
		this.currentMouseX = 0;
		this.currentMouseY = 0;
		
	}
	
	
	public void showContents() {
		
		// Draws the buildings to the stage screen
		for(Structure building : lvl.getGameObject().getBuildingsOnStage()){
			if(building != null){
				building.draw(program);
			}
		}
		
		 //Draws the turrets to the stage screen
		for(Turret turret : lvl.getGameObject().getTurretsOnStage()){
			if(turret != null){
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
		//lvl.getGameObject().checkForHits();
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
		//TODO have to deal with capital letters
		switch(e.getKeyChar()){
		case 'q':
			lvl.getGameObject().generateFriendlyMissile("Sprites/Missile_placeholder.png", true, lvl.getGameObject().getTurretsOnStage()[0].x,
					lvl.getGameObject().getTurretsOnStage()[0].y, program, this.currentMouseX);
			break;
//		case 'w':
//			lvl.getGameObject().generateFriendlyMissile("Sprites/Missile_placeholder.png", true, lvl.getGameObject().getTurretsOnStage()[0].x,
//					lvl.getGameObject().getTurretsOnStage()[0].y, program);
//		case 'e':
//			lvl.getGameObject().generateFriendlyMissile("Sprites/Missile_placeholder.png", true, lvl.getGameObject().getTurretsOnStage()[0].x,
//					lvl.getGameObject().getTurretsOnStage()[0].y, program);
		case 'r':
			lvl.getGameObject().generateFriendlyMissile("Sprites/Missile_placeholder.png", true, lvl.getGameObject().getTurretsOnStage()[1].x,
					lvl.getGameObject().getTurretsOnStage()[1].y, program, -this.currentMouseX);
			break;
		}
	}

}
