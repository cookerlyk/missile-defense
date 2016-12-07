import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.io.*;


public class GamePane extends GraphicsPane implements ActionListener{
	
	private final int PROGRAM_WIDTH = 1024; //Resolution is 1024x625 max on screen
	private final int PROGRAM_HEIGHT = 625;
	private final String GAMEOVER = "Game Over";
	private final String PAUSE = "Press Spacebar to Resume";
	private final String BACKGROUND_IMAGE = "Screens/gameplay_background.png";
	private final String STATUS_LABEL_FONT = "Arial-Bold-30";
	private final String LABEL_FONT = "Arial-Bold-20";
	private final String AMMO_LABEL_FONT = "Arial-Bold-18";
	private final String ROUND_TIME_LABEL = "45";
	private final int INITIAL_DELAY = 1000;
	
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private Level lvl;
	private Timer move;
	private GLabel pauseMessage, gameOverMessage, roundTime, score, ammoQ, ammoW, ammoE, ammoR;
	private GImage background;
	
	private int currentMouseX;
	private int currentMouseY;
	private int gameSpeed, tempRoundNum;
	
	

	
	
	public GamePane(MainApplication app) {
		this.program = app;
		lvl = new Level(program);
		background = new GImage(this.BACKGROUND_IMAGE, 0, 0);
		lvl.getGameObject().generateBuildings(program);
		lvl.getGameObject().generateTurrets(program);
		this.currentMouseX = 0;
		this.currentMouseY = 0;
		this.tempRoundNum = 1;
		this.gameSpeed = 80;

		this.pauseMessage = new GLabel(this.PAUSE, this.PROGRAM_WIDTH/3.2, this.PROGRAM_HEIGHT/2);
		this.pauseMessage.setFont(this.STATUS_LABEL_FONT);
		this.pauseMessage.setColor(Color.white);
		this.gameOverMessage = new GLabel(this.GAMEOVER, this.PROGRAM_WIDTH/2.3, this.PROGRAM_HEIGHT/2);
		this.gameOverMessage.setFont(this.STATUS_LABEL_FONT);
		this.gameOverMessage.setColor(Color.white);
		
		this.drawLabelHelper();
		
	}
	
	
	public void showContents() {
		program.add(this.background);
		program.add(this.roundTime);
	    program.add(this.score);
	    program.add(this.ammoQ);
		program.add(this.ammoW);
		program.add(this.ammoE);
		program.add(this.ammoR);
		this.drawStructures();
		this.run();

	}
	
	public void run(){
		move = new Timer(this.gameSpeed, (ActionListener) this);
		move.setInitialDelay(INITIAL_DELAY);
		move.start();
		this.lvl.startRound();  // starts the round timer

	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * Performs the necessary game play operations every tick of the clock
	 */
	public void actionPerformed(ActionEvent e){
		if(!lvl.isGameOver()){
			this.updateRoundSpeed();
			lvl.getGameObject().generateEnemyMissile("Sprites/enemyPlaceholder.png", program);
			lvl.getGameObject().checkForHits();
			this.updateScreenLabels();

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
		program.remove(this.gameOverMessage);
		program.remove(this.ammoQ);
		program.remove(this.ammoW);
		program.remove(this.ammoE);
		program.remove(this.ammoR);
		for(Missile missile : lvl.getmissiles()){
			missile.getSprite().remove(program); 
		}
		for(Turret turret : lvl.getTurrets()){
			turret.sprite.remove(program); 
		}
		for(Structure building : lvl.getBuildings()){
			building.sprite.remove(program); 
		}
		
	}
	
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
			if(!lvl.isGameOver()){
			lvl.getGameObject().getTurretsOnStage()[0].fireMissile(program, lvl, this.currentMouseX, this.currentMouseY, lvl.getFriendlymissiles());
			}
			break;
		
		// Turret W
		case 'w':
			if(!lvl.isGameOver()){
			lvl.getGameObject().getTurretsOnStage()[1].fireMissile(program, lvl, this.currentMouseX, this.currentMouseY, lvl.getFriendlymissiles());
			}
			break;
	   
		// Turret E
		case 'e':
			if(!lvl.isGameOver()){
			lvl.getGameObject().getTurretsOnStage()[2].fireMissile(program, lvl, this.currentMouseX, this.currentMouseY, lvl.getFriendlymissiles());
			}
			break;
		
		// Turret R
		case 'r':
			if(!lvl.isGameOver()){
			lvl.getGameObject().getTurretsOnStage()[3].fireMissile(program, lvl, this.currentMouseX, this.currentMouseY, lvl.getFriendlymissiles());
			}
			break;

		// Pause or resume the game with a space bar press
		case ' ':
			if(!lvl.isGameOver()){
				this.pauseHelper();
			}
			break;
			
		// Press the enter key when the game is over to return to main menu
		case '\n':
			if(lvl.isGameOver()){
				IODialog tb = new IODialog();
				String name = tb.readLine("Name (Three Initials):");
				program.addScore(name, lvl.getScore());
				this.hideContents();
				program.switchBack();
			}
			break;
		}
	}
	
	
	
	/***********************************
	 * 
	 * 
	 *Helper functions 
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
				building.sprite.scale(0.4, 0.4); // TODO remove hard coded value
				building.draw(program);
			}
		}
		//Draws the turrets to the stage screen
		for(Turret turret : lvl.getGameObject().getTurretsOnStage()){
			if(turret != null){
				turret.sprite.scale(.5, .5); // TODO remove hard coded value
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
		if(missile.getY() < 0 || missile.getY() > this.PROGRAM_HEIGHT || missile.getX() < 0 || missile.getX() > this.PROGRAM_WIDTH) {
//			missile.setDestroyed(true);
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
		
	}
	
	/*
	 * Helper function to pause the game
	 */
	private void pauseHelper(){
		if(!lvl.isPaused()){
			lvl.setPaused();           // sets paused to true
			this.move.stop();          // stops timer, thus pauses the game
			lvl.pauseRound();          // stops round timer
			program.add(this.pauseMessage);
		}
		else{
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
				this.roundTime.setColor(Color.white);
				this.roundTime.setFont(LABEL_FONT);
			    this.score.setColor(Color.white);
				this.score.setFont(LABEL_FONT);
				
				// TODO less hard coding?
				this.ammoQ = new GLabel(String.valueOf(lvl.getTurrets()[0].getMissileCount()), 15, 560);
				this.ammoW = new GLabel(String.valueOf(lvl.getTurrets()[1].getMissileCount()), 150, 610);
				this.ammoE = new GLabel(String.valueOf(lvl.getTurrets()[2].getMissileCount()), 870, 610);
				this.ammoR = new GLabel(String.valueOf(lvl.getTurrets()[3].getMissileCount()), 995, 560);
				
				this.ammoQ.setFont(AMMO_LABEL_FONT);
				this.ammoW.setFont(AMMO_LABEL_FONT);
				this.ammoE.setFont(AMMO_LABEL_FONT);
				this.ammoR.setFont(AMMO_LABEL_FONT);
				this.ammoQ.setColor(Color.white);
				this.ammoW.setColor(Color.white);
				this.ammoE.setColor(Color.white);
				this.ammoR.setColor(Color.white);
	}
	
	private void updateScreenLabels(){
		this.roundTime.setLabel(String.valueOf(lvl.getTime()));
		this.score.setLabel(String.valueOf(lvl.getScore()));
		this.ammoQ.setLabel(String.valueOf(lvl.getTurrets()[0].getMissileCount()));
		this.ammoW.setLabel(String.valueOf(lvl.getTurrets()[1].getMissileCount()));
		this.ammoE.setLabel(String.valueOf(lvl.getTurrets()[2].getMissileCount()));
		this.ammoR.setLabel(String.valueOf(lvl.getTurrets()[3].getMissileCount()));
	}
	
	private void updateRoundSpeed(){
		if(this.tempRoundNum != lvl.getRoundNumber()){
			this.tempRoundNum = lvl.getRoundNumber();
			if(this.gameSpeed > 40){
				this.move.setDelay(this.gameSpeed-=2);
			}
		}
	}
	
}
