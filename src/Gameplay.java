import java.util.*;

/**
 * This class represents the board where all of the game objects, the 
 * missiles, turrets, and structures, will be placed and accessable
 * 
 * @author Kyle Cookerly
 *
 */

public class Board {
	
	private final int TOTAL_TURRETS = 4;
	private final int TOTAL_BUILDINGS = 4;
	
	private int height;
	private int width;
	//private tile[][] tile;
	
	// array lists that will hold the missile objects on the board
	 private List<Missile> friendlyMissiles = new ArrayList<Missile>();
	 private List<Vehicle> enemyMissiles = new ArrayList<Missile>();
	 
	 // Static Arrays to hold the turret and the building objects
	 private Turret[] turrets;
	 private Structure[] buildings;
	 
	 
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		this.turrets = new Turret[this.TOTAL_TURRETS];
		this.buildings = new Structure[this.TOTAL_BUILDINGS];
		
		//TODO add the turrets and the buildings in the constructor as the setup
	}
	
	/*
	 * Returns the array list of friendly missiles on the board
	 */
	public List<Missile> getFriendlyMissiles(){
		return this.friendlyMissiles;
	}
	
	/*
	 * Returns the array list of enemy missiles on the board
	 */
	public List<Missile> getEnemyMissiles(){
		return this.enemyMissiles;
	}
	
	/*
	 * Returns the array of turret objects on the board
	 */
	public Turret getTurrets(){
		return this.turrets;
	}
	
	/*
	 * Returns the array of buildings on the board
	 */
	public Structure getBuildings(){
		return  this.buildings;
	}
	
	/*
	 * Returns the height of the board
	 */
	public int getHeight(){
		return this.height;
	}
	
	/*
	 * Returns the width of the board
	 */
	public int getWidth(){
		return this.width;
	}
}
