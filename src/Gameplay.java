import java.util.*;


/**
 * This class will handle the game play for all of the objects
 * contained in the game
 * 
 * @author Kyle Cookerly
 *
 */

public class Gameplay {
	
	private final int WIDTH_IN_PIXELS = 1024;
	
	private final int TOTAL_TURRETS = 4;
	private final int TOTAL_BUILDINGS = 4;
	private final int MISSILE_GENERATION_PROBILITY = 5;  // Translates to a 5% chance of a missile spawning
	private final int PERCENT = 100;                     // Used in place for hardcoding 100 into the RNG function
	private Random rng;

	
	// array lists that will hold the missile objects on the board
	// TODO maybe one list would work
	private List<Missile> missiles;
	
	// Static Arrays to hold the turret and the building objects
	private Turret[] turrets;
	private Structure[] buildings;
	
	
	
	 
	public Gameplay() {
		this.rng = new Random();  // seed the RNG at creation of the Gameplay object
		this.missiles = new ArrayList<Missile>();
		this.turrets = new Turret[this.TOTAL_TURRETS];
		this.buildings = new Structure[this.TOTAL_BUILDINGS];
	}
	
	
	/*
	 * generates a RNG and if the number is within the range, an enemy missile is generated
	 * when called the missile is added to the Array List of enemy missiles
	 */
	public boolean generateEnemyMissile(String spriteLocation, boolean friendly, MainApplication app){ //TODO add enum start location
		int num = this.rng.nextInt(this.PERCENT) + 1;
		int xCoord = this.rng.nextInt(this.WIDTH_IN_PIXELS);
		int yCoord = 0;
		if(num <= this.MISSILE_GENERATION_PROBILITY){
			Missile missile = new Missile(spriteLocation, friendly, xCoord, yCoord, app);
			this.missiles.add(missile);
			return true;
		}
		return false;
	}
	
	/*
	 * Friendly missile is generated at call and added to the friendly missile array
	 */
	public void generateFriendlyMissile(String spriteLocation, boolean friendly, int xCoord, int yCoord, MainApplication app){
		Missile missile = new Missile(spriteLocation, friendly, xCoord, yCoord, app);
		this.missiles.add(missile);

	}
	
//	/*
//	 * generates the buildings at start
//	 */
//	public void generateBuildings(){
//
//	}
//
//	/*
//	 * generates the turrets at start
//	 */
//	public void generateTurrets(){
//
//	}
	
	/*
	 * moves all of the missiles every tick of the clock
	 */
	public void moveMissiles(){
		for(Missile missile : this.missiles){ //TODO maybe there doesn't have to be different lists of missiles
			if(missile != null){
				missile.move();
			}
			
		}
	}
	
	/*
	 * checks if each missile hit anything on each tick of the clock
	 */
//	public void checkHits(){
//		for(Missile missile : this.enemyMissiles){
//			missile.checkIfHit();
//		}
//		for(Missile missile : this.friendlyMissiles){
//			missile.checkIfHit();
//		}
//	}
	
	/*
	 * returns the missiles that are on the stage
	 */
	public List<Missile> getMissilesOnStage(){
		return this.missiles;
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
	 * resets all of the turrets status when called 
	 * eg. destroyed to not destroyed.
	 * 
	 * Called when the round is over
	 */
//	public void resetTurrets(){
//		for(Turret turret : this.turrets){
//			turret.setDestoyed(false);
//		}
//		
//	}
}
