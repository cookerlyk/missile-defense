import java.util.*;


/**
 * This class will handle the game play for all of the objects
 * contained in the game
 * 
 * @author Kyle Cookerly
 *
 */

public class Gameplay {
	
	private static final String BUILDING_SPRITE = "Sprites/SpaceColony.png";
	private static final String BUILDING_SPRITE_DESTROYED = "Sprites/SpaceColonyDestroyed.png";

	private static final String TURRET_SPRITE = "Sprites/turret_Placeholder.png";
	private static final String TURRET_SPRITE_DESTROYED = "Sprites/turretDestroyed_Placeholder.png";
	private static final int BUILDING_Y_LOCATION = 536;
	private static final int HIGH_TURRET_Y_LOCATION = 450;
	private static final int LOW_TURRET_Y_LOCATION = 500;


	private static final String TURRET_SPRITE = "Sprites/MissileSilo.png";
	private static final String TURRET_SPRITE_DESTROYED = "Sprites/MissileSiloDestroyed.png";

	
	
	private final int SCORE_INCREASE = 10;
	private final int TOTAL_TURRETS = 4;
	private final int TOTAL_BUILDINGS = 4;
	private final int MISSILE_GENERATION_PROBILITY = 5;  // Translates to a 5% chance of a missile spawning
	private final int PERCENT = 100;                     // Used in place for hard coding 100 into the RNG function
	private Random rng;
	
	private int score;


	// array list that will hold the missile objects on the board
	private List<Missile> missiles;
	
	private List<Missile> friendlyMissiles;
	
	// Static Arrays to hold the turret and the building objects
	private Turret[] turrets;
	private Structure[] buildings;
	
	
	public Gameplay() {
		this.rng = new Random();  // seed the RNG at creation of the Gameplay object
		this.missiles = new ArrayList<Missile>();
		this.friendlyMissiles = new ArrayList<Missile>();
		this.turrets = new Turret[this.TOTAL_TURRETS];
		this.buildings = new Structure[this.TOTAL_BUILDINGS];
		this.score = 0;
	}
	
	/*
	 * generates a RNG and if the number is within the range, an enemy missile is generated
	 * when called the missile is added to the Array List of enemy missiles
	 */
	public boolean generateEnemyMissile(String spriteLocation, MainApplication app){
		int num = this.rng.nextInt(this.PERCENT) + 1;
		if(num <= this.MISSILE_GENERATION_PROBILITY){
			Missile missile = new Missile(app, spriteLocation);
			this.missiles.add(missile);
			return true;
		}
		return false;
	}
	
	/*
	 * generates the buildings at start
	 */
	public void generateBuildings(MainApplication app){
		for (int i = 0; i < TOTAL_BUILDINGS; i++) {
			this.buildings[i] = new Building(app, 290 + i*125, BUILDING_Y_LOCATION, BUILDING_SPRITE, BUILDING_SPRITE_DESTROYED);
		}
	}

	/*
	 * generates the turrets at start
	 */
	public void generateTurrets(MainApplication app){
		this.turrets[0] = new Turret(app,15,HIGH_TURRET_Y_LOCATION,TURRET_SPRITE, TURRET_SPRITE_DESTROYED);
		this.turrets[1] = new Turret(app,150,LOW_TURRET_Y_LOCATION,TURRET_SPRITE, TURRET_SPRITE_DESTROYED);
		this.turrets[2] = new Turret(app,800,LOW_TURRET_Y_LOCATION,TURRET_SPRITE, TURRET_SPRITE_DESTROYED);
		this.turrets[3] = new Turret(app,925,HIGH_TURRET_Y_LOCATION,TURRET_SPRITE, TURRET_SPRITE_DESTROYED);
	}
	
	/*
	 * moves all of the missiles every tick of the clock
	 */
	public void moveMissiles(){
		for(Missile missile : this.missiles){
			if(missile != null){
				missile.move();
			}
		}
	}
	
	/*
	 * checks if each missile hit anything on each tick of the clock
	 * by checking if the hit boxes of the buildings/turrets contain a missile 
	 * hit box
	 */
	public void checkForHits(){
		for(Missile missile : this.missiles){
			if(missile != null && !missile.isDestroyed()){
				// Check buildings
				for (int i = 0; i < this.buildings.length; i++) {
					if(!this.buildings[i].isDestroyed()){
						if (this.buildings[i].getHitBox().intersects(missile.getHitBox())) {
							this.buildings[i].destroy();
							missile.setDestroyed(true);
							break;
						}
					}
				}
				// Check turrets
				for (int i = 0; i < this.turrets.length; i++ ) {
					if(!this.turrets[i].isDestroyed()){
						if (!missile.isFriendly() && this.turrets[i].getHitBox().intersects(missile.getHitBox())) {
							this.turrets[i].destroy();
							missile.setDestroyed(true);
							break;
						}
					}
				}
				
				// Checks Missile vs. Friendly Missile collisions
				for (Missile fMissile : this.friendlyMissiles){
					if(!fMissile.isDestroyed() && fMissile != null){
						if (missile.getHitBox().intersects(fMissile.getHitBox())) {
							fMissile.setDestroyed(true);
							missile.setDestroyed(true);
							this.incrementScore(this.SCORE_INCREASE);
							break;
						}
					}
				}
			}
		}
	}
	
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
	 * resets all of the turret's status to true when called 
	 * eg. destroyed to not destroyed.
	 * 
	 * Called when each round is over
	 */
	public void resetTurrets(){
		for(Turret turret : this.turrets){
			turret.reset();
		}
	}
	
	/*
	 * resets all of the building's status to true when called 
	 * eg. destroyed to not destroyed.
	 *
	 */
	public void resetBuildings(){
		for(Structure building : this.buildings){
			building.reset();
		}
	}
	
	/*
	 * Returns Array list of friendly missiles
	 */
	public List<Missile> getFriendlyMissilesOnStage(){
		return this.friendlyMissiles;
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
	 * returns true if there is at least one building not destroyed
	 */
	public boolean checkBuildings(){
		for(Structure building : this.buildings){
			if(!building.isDestroyed()){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Removes all the missiles from the screen and sets them to null
	 * intended for use at the start of a new round
	 */
	public void removeAllMissiles(){
		for (Missile missile : this.missiles){
			if(!missile.isDestroyed() && missile != null){
				missile.setDestroyed(true);
				missile = null;
			}
		}
		for (Missile fMissile : this.friendlyMissiles){
			if(!fMissile.isDestroyed() && fMissile != null){
				fMissile.setDestroyed(true);
				fMissile = null;
			}
		}
	}
}
