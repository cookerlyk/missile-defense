import java.util.*;

/**
 * This class will handle the game play for all of the objects
 * contained in the game
 * 
 * @author Kyle Cookerly
 *
 */

public class Gameplay {
	
	private final int TOTAL_TURRETS = 4;
	private final int TOTAL_BUILDINGS = 4;
	private final int MISSILE_GENERATION_PROBILITY = 25; // Translates to a 25% chance of a missile spawning
	private final int PERCENT = 100;                     // Used in place for hardcoding 100 into the RNG function
	private Random rng;
	
	// array lists that will hold the missile objects on the board
	// TODO maybe one list would work
	private List<Missile> friendlyMissiles;
	private List<Missile> enemyMissiles;
	
	// Static Arrays to hold the turret and the building objects
	private Turret[] turrets;
	private Structure[] buildings;
	
	 
	public Gameplay() {
		this.rng = new Random();  // seed the RNG at creation of the Gameplay object
		this.friendlyMissiles = new ArrayList<Missile>();
		this.enemyMissiles = new ArrayList<Missile>();
		this.turrets = new Turret[this.TOTAL_TURRETS];
		this.buildings = new Structure[this.TOTAL_BUILDINGS];
	}
	
	
	/*
	 * generates a RNG and if the number is within the range, an enemy missile is generated
	 * when called the missile is added to the Array List of enemy missiles
	 */
	public boolean generateEnemyMissile(Sprite spr, boolean friendly, int xCoord, int yCoord){ //TODO add enum start location
		int num = this.rng.nextInt(this.PERCENT) + 1;
		System.out.println(num);
		if(num <= this.MISSILE_GENERATION_PROBILITY){
			Missile missile = new Missile(spr, friendly, xCoord, yCoord);
			this.enemyMissiles.add(missile);
			return true;
		}
		return false;
	}
	
	/*
	 * Friendly missile is generated at call and added to the friendly missile array
	 */
	public void generateFirendlyMissile(Sprite spr, boolean friendly, int xCoord, int yCoord){
		Missile missile = new Missile(spr, friendly, xCoord, yCoord);
		this.friendlyMissiles.add(missile);

	}
	
	/*
	 * moves all of the missiles every tick of the clock
	 */
	public void moveMissiles(){
		for(Missile missile : this.enemyMissiles){ //TODO maybe there doesn't have to be different lists of missiles
			missile.move();
		}
		for(Missile missile : this.friendlyMissiles){
			missile.move();
		}
	}
	
	/*
	 * checks if each missile hit anything on each tick of the clock
	 */
	public void checkHits(){
		for(Missile missile : this.enemyMissiles){
			missile.checkIfHit();
		}
		for(Missile missile : this.friendlyMissiles){
			missile.checkIfHit();
		}
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
	
	/*
	 * resets all of the building and turrets status when called 
	 * eg. destroyed to not destroyed.
	 * 
	 * Called when the round is over
	 */
	public void resetStructures(){
		for(Turret turret : this.turrets){
			turret.setDestoyed(false);
		}
		for(Structure building : this.buildings){
			building.setDestoyed(false);
		}
	}
}
