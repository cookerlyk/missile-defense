import java.util.*;

/**
 * This class represents the Level that will handle the gameplay
 * and will export the Board and its objects out to the GUI class
 * for display
 * 
 * @author Kyle Cookerly
 *
 */

public class Level {
	private final int ROUND_TIME = 60;  // Round is 60 seconds
	private boolean gameOver, paused;
	private int score, time, roundNum;
	private Gameplay game;
	

	/*
	 * Set up the level when called
	 */
	public Level(MainApplication app){
		this.game = new Gameplay();
		this.paused = false;
		this.gameOver = false;
		this.score = 0;
		this.time = this.ROUND_TIME;
		this.roundNum = 1;
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
	}
	
	/*
	 * returns the round number
	 */
	public int getRoundNumber(){
		return this.roundNum;
	}
	
	/*
	 * returns the current time left in the round
	 */
	public int getTime(){
		return this.time;
	}
	
	/*
	 * returns the current score 
	 */
	public int getScore(){
		return this.score;
	}
	
	/*
	 * Allows the score to be manually set
	 */
	public void setScore(int newScore){
		this.score = newScore;
	}
	
	/*
	 * increments the score by a passed in amount when called
	 */
	public void incrementScore(int amount){
		this.score += amount;
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
