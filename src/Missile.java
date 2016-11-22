import java.util.*;
import acm.graphics.*;
import java.lang.Math;

/** 
 * 
 * @author Obaid Rehman 
 *
 **/

public class Missile {
	
	private Sprite sprite;
	private boolean isFriendly, isDestroyed, isHit;
	private int x, y;
	private double radius, angle;
	private Random rng;
	private static final int WIDTH = 50, HEIGHT = 50;
	private GRect hitbox;
	private boolean left;
	private double scale = 0.1;
	
	/**
	 * Default constructor. Intended for enemies.
	 */
	public Missile(MainApplication app, String spriteLoc) {
		sprite = SpriteStore.get().getSprite(spriteLoc);
		isFriendly = false;
		isDestroyed = false;
		this.rng = new Random();
		x = rng.nextInt(1024);
		y = 0;
		radius = 10;
		angle = this.rng.nextDouble();
		if (angle < 0.3) {
			angle += 0.1;
		}
		else if (angle > 0.7) {
			angle -= 0.1;
		}
		isHit = false;
		
		this.hitbox = new GRect(x, y, Missile.WIDTH, Missile.HEIGHT); //TODO need to make the hit box reflect the orientation/size of the missile
		this.sprite.scale(scale, scale);
		
		//TODO remove, test only to generate the boxes for visual example
//		hitbox.setColor(Color.BLUE);
//		hitbox.setFilled(true);
		
		if (this.rng.nextInt(2) == 0) 
			left = true;
		else left = false;
		
		System.out.print("r:" + radius + " a:" + angle + "\n" );
	}
	
	/**
	 * Intentional use is for friendly missiles.
	 * @param s Sprite
	 * @param side Friend or foe / true or false
	 * @param status Destroyed or not
	 * @param x starting x coordinate
	 * @param y starting y coordinate
	 * @param mouseX is mouse's x
	 * @param mouseY is mouse's y
	 * @param l is whether the missile is going left or not.
	 */
	public Missile(String spriteLoc, boolean side, int x, int y, MainApplication app, int mouseX, int mouseY, boolean l) {
		sprite = SpriteStore.get().getSprite(spriteLoc);
		isFriendly = side;
		isDestroyed = false;
		this.x = x;
		this.y = y;
		radius = 10;
//		if (isFriendly) 
//			radius *= -1;
		
		this.left = l;
		
		double dx;
		if (this.left) {
			dx = mouseX - this.x;
		} else dx = mouseX + this.x;
		
		double dy = mouseY - this.y;
		angle = Math.toDegrees(Math.atan2(dy, dx));
		isHit = false;
		
		
		
		this.hitbox = new GRect(x, y, Missile.WIDTH, Missile.HEIGHT); //TODO need to make the hit box reflect the orientation/size of the missile
		this.sprite.scale(scale, scale);
		
		//TODO remove, test only to generate the boxes for visual example
//		hitbox.setColor(Color.BLUE);
//		hitbox.setFilled(true);
		System.out.print("x: " + mouseX + " y: " + mouseY + "\n");
	}
	
	public void draw(MainApplication app) {
		sprite.draw(app, this.x, this.y);
		//app.add(this.hitbox);
	}
	
	public void move() {
		if (left) {
			this.x -= (int) (radius*Math.cos(angle));
			this.hitbox.move((int) -(radius*Math.cos(angle)), (int) (radius*Math.sin(angle)));
		}
		else {
			this.x += (int) (radius*Math.cos(angle));
			this.hitbox.move((int) (radius*Math.cos(angle)), (int) (radius*Math.sin(angle)));
		}
		this.y += (int) (radius*Math.sin(angle));
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
	
	/**
	 * @return the hitbox for the missile
	 */
	public GRect getHitBox(){
		return this.hitbox;
	}
	
	/**
	 * @return missile angle
	 */
	public double getAngle() {
		return angle;
	}
}
