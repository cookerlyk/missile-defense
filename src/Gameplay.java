import java.util.*;

/**
 * This class will handle the game play for all of the objects
 * contained in the game
 * 
 * @author Kyle Cookerly
 *
 */

public class Gameplay {
	
	private final int MISSILE_GENERATION_PROBILITY = 25;
	private Random rng;
	 
	public Gameplay() {
		this.rng = new Random();  // seed the RNG at creation of the Gameplay object
	}
	
	
	/*
	 * generates a RNG and if the number is within the range, an enemy missile is generated
	 * when called
	 */
	public boolean generateEnemyMissile(int velocity, int dx, int dy){ //TODO add enum start location
		int num = this.rng.nextInt(100) + 1;
		System.out.println(num);
		if(num <= 25){
			Missile missile = new Missile(velocity, dx, dy);
			
			return true; //TODO maybe return the missile here to send back to the level class?
		}
		return false;
	}
	
	
//	public void moveMissiles(Missile missileList){
//		for(Missile missile : missileList){
//			missile.move();
//		}
//	}
	
	/*
	 * calls each missile object's function to decide if there was a hit or not
	 */
	public void checkHits(Missile missileList){
		for(Missile missile : missileList){
			missile.checkIfHit();
		}
	}
	
	// TODO create a function to let this class interact with the level, since the missile
	// generated here needs to make it back to the Level class
	
}
