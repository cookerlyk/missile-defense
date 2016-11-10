import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/** 
 * 
 * @author Obaid Rehman 
 *
 **/

public class Missile {
	private Sprite sprite;
	private boolean isFriendly, isDestroyed;
	private int x, y, dx, dy;
	private static int WIDTH = 50, HEIGHT = 50;
	private boolean isHit;
	
	public Missile() {
		sprite = EnemyMissileSprite; // DefaultMissileSprite;
		isFriendly = false;
		isDestroyed = false;
		Random rand;
		x = rand.nextInt(1024);
		y = 0;
		dy = 10;
		dx = ThreadLocalRandom.current().nextInt(-10, 11);
	}
	
	public Missile(Sprite s, boolean side, boolean status, int x, int y) {
		sprite = s;
		isFriendly = side;
		isDestroyed = status;
		this.x = x;
		this.y = y;
		dy = 10;
		dx = ThreadLocalRandom.current().nextInt(-10, 11);
	}
	
	
	/**
	 * @return the isDestroyed
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
	/**
	 * @param isDestroyed the isDestroyed to set
	 */
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the isHit
	 */
	public boolean isHit() {
		return isHit;
	}
	/**
	 * @param isHit the isHit to set
	 */
	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}
	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}
	/**
	 * @return the isFriendly
	 */
	public boolean isFriendly() {
		return isFriendly;
	}
	/**
	 * @return the dx
	 */
	public int getDx() {
		return dx;
	}
	/**
	 * @return the dy
	 */
	public int getDy() {
		return dy;
	}
	/**
	 * @return the wIDTH
	 */
	public static int getWIDTH() {
		return WIDTH;
	}
	/**
	 * @return the hEIGHT
	 */
	public static int getHEIGHT() {
		return HEIGHT;
	}
}
