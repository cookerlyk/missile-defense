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
	//TODO change to stage rather than level?
	
	private final int TOTAL_TURRETS = 4;
	private final int TOTAL_BUILDINGS = 4;
	
	private final int ROUND_TIME = 60;  // Round is 60 seconds
	

	private boolean gameOver;
	private int score, time, roundNum;
	private Gameplay game;
	
	// array lists that will hold the missile objects on the board
	private List<Missile> friendlyMissiles = new ArrayList<Missile>();
	private List<Missile> enemyMissiles = new ArrayList<Missile>();
	
	// Static Arrays to hold the turret and the building objects
	private Turret[] turrets;
	private Structure[] buildings;
	
	/*
	 * Set up the level (round?) when called
	 */
	public Level(){
		this.game = new Gameplay();
		this.gameOver = false;
		this.score = 0;
		this.time = this.ROUND_TIME;
		this.roundNum = 1;
		this.turrets = new Turret[this.TOTAL_TURRETS];
		this.buildings = new Structure[this.TOTAL_BUILDINGS];
	}
	
	/*
	 * Resets the Board and its objects to start a new round,
	 * updates the round counter and resets the clock as well
	 * as resets all of the turret and building objects to not
	 * destroyed
	 */
	public void resetLevel(){
		this.time = this.ROUND_TIME;
		this.roundNum += 1;
		// TODO reset the turrets and structures here
		// loop through all of them and reset their status if they are destroyed
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
	 * Returns the array of turret objects on the board
	 */
	public Turret[] getTurretsOnStage(){
		return this.turrets;
	}
	
	/*
	 * Returns the array of buildings on the board
	 */
	public Structure[] getBuildingsOnStage(){
		return this.buildings;
	}
	
	public void addFriendlyMissile(Missile missle){
		this.friendlyMissiles.add(missile);
	}
	
	/*
	 * returns the friendly missiles that are on the stage
	 */
	public List<Missile> getFriendlyMissilesOnStage(){
		return this.friendlyMissiles;
	}
	
	/*
	 * returns the enemy missiles that are on the stage
	 */
	public List<Missile> getEnemyMissilesOnStage(){
		return this.enemyMissiles;
	}
}
