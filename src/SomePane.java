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
	
	private final int PROGRAM_WIDTH = 1024; //Resolution is 1024x650 max on screen
	private final int PROGRAM_HEIGHT = 768;
	private final String LABEL_FONT = "Arial-Bold-22";
	private final String AMMO_LABEL_FONT = "Arial-Bold-18";
	private final String ROUND_TIME_LABEL = "45";
	
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private Level lvl;
	private Timer move;
	private GLabel roundTime, score, ammoQ, ammoW, ammoE, ammoR;
	private GRect pauseBox;
	private GLabel pauseMessage, gameOverMessage;
	
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
		this.gameOverMessage = new GLabel("Game Over", 435, 280);
		this.gameOverMessage.setFont("Arial-Bold-30");
		
		this.drawLabelHelper();
		
	}
	
	
	public void showContents() {
		program.add(this.roundTime);
	    program.add(this.score);
		this.drawStructures();
		program.add(ammoQ);
		program.add(ammoW);
		program.add(ammoE);
		program.add(ammoR);
		this.run();

	}
	
	public void run(){
		move = new Timer(80, (ActionListener) this);
		move.setInitialDelay(1000);
		move.start();
		this.lvl.startRound();  // starts the round timer

	}
	
	public void actionPerformed(ActionEvent e){
		if(!lvl.isGameOver()){
			lvl.getGameObject().generateEnemyMissile("Sprites/enemyPlaceholder.png", program);
			lvl.getGameObject().checkForHits();
			this.roundTime.setLabel(String.valueOf(lvl.getTime()));
			
			this.score.setLabel(String.valueOf(lvl.getScore()));
			this.ammoQ.setLabel(String.valueOf(lvl.getTurrets()[0].getMissileCount()));
			this.ammoW.setLabel(String.valueOf(lvl.getTurrets()[1].getMissileCount()));
			this.ammoE.setLabel(String.valueOf(lvl.getTurrets()[2].getMissileCount()));
			this.ammoR.setLabel(String.valueOf(lvl.getTurrets()[3].getMissileCount()));

			// Enemy Missiles
			for(Missile missile: lvl.getGameObject().getMissilesOnStage()){
				this.missileHelper(missile);
			}

			// Friendly Missiles
			for(Missile fMissile: lvl.getGameObject().getFriendlyMissilesOnStage()){
				this.missileHelper(fMissile);
			}
			
			// Checks that all the buildings are not destroyed, sets game over to true if entered
			if(!lvl.getGameObject().checkBuildings()){
				lvl.setGameOver();
			}
		}
		else{
			this.gameOverHelper();
		}
	}

	@Override
	public void hideContents() {
		program.remove(this.roundTime);
		program.remove(this.score);
		for(Missile missile : lvl.getmissiles()){
			missile.getSprite().remove(program); 
		}
	}
	
//	@Override
//	public void mousePressed(MouseEvent e) {

//	}
	
	@Override
	public void mouseMoved(MouseEvent e){
		this.currentMouseX = e.getX();
		this.currentMouseY = e.getY();
	}
	
	
	@Override
	public void keyTyped(KeyEvent e){
		
		switch(Character.toLowerCase(e.getKeyChar())){
		
		// Turret Q
		case 'q':
			lvl.getGameObject().getTurretsOnStage()[0].fireMissile(program, lvl, this.currentMouseX, this.currentMouseY, lvl.getFriendlymissiles());
			break;
		
		// Turret W
		case 'w':
			lvl.getGameObject().getTurretsOnStage()[1].fireMissile(program, lvl, this.currentMouseX, this.currentMouseY, lvl.getFriendlymissiles());
			break;
	   
		// Turret E
		case 'e':
			lvl.getGameObject().getTurretsOnStage()[2].fireMissile(program, lvl, this.currentMouseX, this.currentMouseY, lvl.getFriendlymissiles());
			break;
		
		// Turret R
		case 'r':
			lvl.getGameObject().getTurretsOnStage()[3].fireMissile(program, lvl, this.currentMouseX, this.currentMouseY, lvl.getFriendlymissiles());
			break;

		// Pause or resume the game with a space bar press
		case ' ':
			this.pauseHelper();
			break;
			
		// Press the enter key when the game is over to return to main menu
		case '\n':
			if(lvl.isGameOver()){
				this.hideContents();
				program.switchBack();  // TODO reset the game to a new game 
			}
			break;
		}
	}
	
	
	
	/***********************************
	 * 
	 * 
	 *		   Helper functions 
	 * 
	 * 
	 ***********************************/
	
	/*
	 * Draws the turrets and buildings to the screen
	 */
	private void drawStructures(){
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
	}
	
	/*
	 * Helper function to handle missile drawing, removing, and setting to null
	 * @param missile object to check
	 */
	private void missileHelper(Missile missile){
		if(missile != null){
			if(!missile.isDestroyed()){
				missile.draw(program);                // draws the missile image if the missile is not destroyed
			}
			else{
				missile.getSprite().remove(program);  // removes image if the missile is destroyed
			}
			missile.move();
		}
		
		//Sets the missile object to null if it goes off screen, to hopefully evoke GC to destroy the object
		if(missile.getY() < 0 || missile.getY() > this.PROGRAM_HEIGHT || missile.getX() < 0 || missile.getX() > this.PROGRAM_WIDTH){
			missile = null;
		}
	}
	
	/*
	 * Helper function to perform the necessary tasks
	 * to end the game
	 */
	private void gameOverHelper(){
		this.move.stop();          // stops movement timer
		lvl.pauseRound();          // stops round timer
		program.add(this.gameOverMessage);
		
		// TODO high score entering
	}
	
	/*
	 * Helper function to pause the game
	 */
	private void pauseHelper(){
		if(!lvl.isPaused()){
			lvl.setPaused();           // sets paused to true
			this.move.stop();          // stops timer, thus pauses the game
			lvl.pauseRound();          // stops round timer
			program.add(this.pauseBox);
			program.add(this.pauseMessage);
		}
		else{
			program.remove(this.pauseBox);
			program.remove(this.pauseMessage);
			lvl.setPaused();           // sets paused to false
			move.setInitialDelay(50);  // 50ms delay before restart
			this.move.start();         // starts timer, thus resumes the game
			lvl.resumeRound();         // restarts the round timer
		}
	}
	
	/*
	 * set up the labels for the score and the time in the round
	 */
	private void drawLabelHelper(){
				this.roundTime = new GLabel(this.ROUND_TIME_LABEL,10, 20);
				this.score = new GLabel("0",875, 20);
				this.roundTime.setColor(Color.black);
				this.roundTime.setFont(LABEL_FONT);
			    this.score.setColor(Color.black);
				this.score.setFont(LABEL_FONT);
				
				this.ammoQ = new GLabel(String.valueOf(lvl.getTurrets()[0].getMissileCount()), 15, 600);
				this.ammoW = new GLabel(String.valueOf(lvl.getTurrets()[1].getMissileCount()), 150, 675);
				this.ammoE = new GLabel(String.valueOf(lvl.getTurrets()[2].getMissileCount()), 800, 675);
				this.ammoR = new GLabel(String.valueOf(lvl.getTurrets()[3].getMissileCount()), 925, 600);
				
				this.ammoQ.setFont(AMMO_LABEL_FONT);
				this.ammoW.setFont(AMMO_LABEL_FONT);
				this.ammoE.setFont(AMMO_LABEL_FONT);
				this.ammoR.setFont(AMMO_LABEL_FONT);
	}
}
