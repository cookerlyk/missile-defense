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
	private final int PERCENT = 100;                     // Used in place for hard coding 100 into the RNG function
	private Random rng;

	
	// array list that will hold the missile objects on the board
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
		this.buildings[0] = new Building(app, 300, 700, "Sprites/house_placeholder.jpg");
		this.buildings[1] = new Building(app, 450, 700, "Sprites/house_placeholder.jpg");
		this.buildings[2] = new Building(app, 550, 700, "Sprites/house_placeholder.jpg");
		this.buildings[3] = new Building(app, 700, 700, "Sprites/house_placeholder.jpg");
	}

	/*
	 * generates the turrets at start
	 */
	public void generateTurrets(MainApplication app){
		this.turrets[0] = new Turret(app,15,550,"Sprites/turret_Placeholder.png");
		this.turrets[1] = new Turret(app,150,680,"Sprites/turret_Placeholder.png");
		this.turrets[2] = new Turret(app,800,680,"Sprites/turret_Placeholder.png");
		this.turrets[3] = new Turret(app,925,550,"Sprites/turret_Placeholder.png");
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
			if(missile != null){
				for (int i = 0; i < this.buildings.length; i++) {
					if (this.buildings[i].getHitBox().contains(missile.getHitBox().getLocation())) {
						this.buildings[i].destroy();
					}
				}
				for (int i = 0; i < this.turrets.length; i++ ) {
					if (!missile.isFriendly() && this.turrets[i].getHitBox().contains(missile.getHitBox().getLocation())) {
						this.turrets[i].destroy();
					}
				}
					
				/*
				if(this.buildings[0].getHitBox().contains(missile.getHitBox().getLocation())){
					this.buildings[0].destroy();
				}
				if(this.buildings[1].getHitBox().contains(missile.getHitBox().getLocation())){
					this.buildings[1].destroy();
				}
				if(this.buildings[2].getHitBox().contains(missile.getHitBox().getLocation())){
					this.buildings[2].destroy();
				}
				if(this.buildings[3].getHitBox().contains(missile.getHitBox().getLocation())){
					this.buildings[3].destroy();
				}
				if(!missile.isFriendly()){
					if(this.turrets[0].getHitBox().contains(missile.getHitBox().getLocation())){
						this.turrets[0].destroy();
					}
					if(this.turrets[1].getHitBox().contains(missile.getHitBox().getLocation())){
						this.turrets[1].destroy();
					}
					if(this.turrets[2].getHitBox().contains(missile.getHitBox().getLocation())){
						this.turrets[2].destroy();
					}
					if(this.turrets[3].getHitBox().contains(missile.getHitBox().getLocation())){
						this.turrets[3].destroy();
					}
				}
				*/
			}
			//		for(Missile missile : this.friendlyMissiles){
			//			missile.checkIfHit();
			//		}
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
	 * Called when the round is over
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
	
}
