import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Timer;

import acm.graphics.GLabel;

/**
 * This class represents the Level that will handle the gameplay
 * and will export the Board and its objects out to the GUI class
 * for display
 * 
 * @author Kyle Cookerly
 *
 */

public class Level implements ActionListener {
	private final String LABEL_FONT = "Arial-Bold-22";
	private final int BONUS_SCORE_INCREMENT = 50;
	private final int ROUND_TIME = 45;  // A round lasts 45 seconds
	private final int ROUND_RESET_DELAY = 750;
	private boolean gameOver, paused;
	private int time, roundNum;
	private Gameplay game;
	private Timer roundTimer;
	
	private GLabel roundNumberLabel;
	
	private MainApplication app;

	/*
	 * Set up the level when called
	 */
	public Level(MainApplication app){
		this.game = new Gameplay();
		this.paused = false;
		this.gameOver = false;
		this.time = this.ROUND_TIME;
		this.roundNum = 1;
		this.roundTimer = new Timer(1000, this);
		this.roundNumberLabel = new GLabel("Round " + String.valueOf(this.getRoundNumber()), 475, 280);
		this.roundNumberLabel.setFont(LABEL_FONT);
		this.roundNumberLabel.setColor(Color.black);
		this.app = app;
		
	}
	
	/*
	 * starts the timer for the round
	 */
	public void startRound(){
		this.roundTimer.start();
	}
	
	/*
	 * stops the timer at a given state when called
	 */
	public void pauseRound(){
		this.roundTimer.stop();
	}
	
	/*
	 * resumes the timer
	 * TODO remove and call start for both unless 
	 * something needs to be added here to differentiate the functionality
	 * from startRound()
	 */
	public void resumeRound(){
		this.roundTimer.start();
	}
	
	public void actionPerformed(ActionEvent e){
		this.app.remove(this.roundNumberLabel);
		if(this.time > 0){
			this.decrementTime();
		}
		else{
			this.game.removeAllMissiles();
			this.resetLevel();
		}
	}
	
	/*
	 * Returns the Gameplay object 
	 */
	public Gameplay getGameObject(){
		return this.game;
	}
	
	/*
	 * Resets the Board and its objects to start a new round,
	 * updates the round counter and resets the clock as well
	 * as resets all of the turret and building objects to not
	 * destroyed
	 */
	public void resetLevel(){
		this.game.resetTurrets();
		this.time = this.ROUND_TIME;
		this.roundNum += 1;
		this.roundTimer.setInitialDelay(ROUND_RESET_DELAY);
		this.pauseRound();
		for(Turret turret : this.getTurrets()){
			if(!turret.isDestroyed()){
				this.game.incrementScore(BONUS_SCORE_INCREMENT);
			}
		}
		this.roundNumberLabel.setLabel("Round " + String.valueOf(this.getRoundNumber()));
		this.app.add(this.roundNumberLabel);
		this.startRound();
	}
	
	/*
	 * returns the round number
	 */
	public int getRoundNumber(){
		return this.roundNum;
	}
	
	/*
	 * returns the current score
	 */
	public int getScore(){
		return this.game.getScore();
	}
	
	/*
	 * returns the current time left in the round
	 */
	public int getTime(){
		return this.time;
	}
	
	/*
	 * Decrements the time by 1 when called
	 */
	public void decrementTime(){
		this.time -= 1;
	}
	
	
	/*
	 * sets gameOver to be true
	 */
	public void setGameOver(){
		this.gameOver = true;
	}
	
	/*
	 * returns the status of the game
	 */
	public boolean isGameOver(){
		return this.gameOver;
	}
	
	/*
	 * returns the status of the game
	 */
	public boolean isPaused(){
		return this.paused;
	}
	
	/*
	 * toggles paused when called
	 */
	public void setPaused(){
		if(this.paused == false){
			this.paused = true;
		}
		else{
			this.paused = false;
		}
	}
	
	/*
	 * Returns the array of turret objects on the board
	 */
	public Turret[] getTurrets(){
		return this.game.getTurretsOnStage();
	}
	
	/*
	 * Returns the array of buildings on the board
	 */
	public Structure[] getBuildings(){
		return this.game.getBuildingsOnStage();
	}
	
	/*
	 * returns the missiles that are in play
	 */
	public List<Missile> getmissiles(){
		return this.game.getMissilesOnStage();
	}
	
	public List<Missile> getFriendlymissiles(){
		return this.game.getFriendlyMissilesOnStage();
	}
	
}
