import java.util.*;
import acm.graphics.*;
import java.lang.Math;

/** 
 * 
 * @author Obaid Rehman 
 *
 **/

public class Missile {
	
	private final double SCALE = 0.1;
	private Sprite sprite;
	private boolean isFriendly, isDestroyed, isHit;
	private int x, y;
	private double radius, angle;
	
	private int roundedAngle;
	
	private Random rng;
	
	private final static int WIDTH = 30, HEIGHT = 50;


	private GRectangle hitbox;
	
	private final boolean DEBUG_MODE = true; // set to false if you want the hit boxes to not appear on screen
	private GRect debugHitbox;
	
	
	
	/**
	 * Default constructor. Intended for enemies.
	 */
	public Missile(MainApplication app, String spriteLoc) {
		
		
	
		isFriendly = false;
		isDestroyed = false;
		this.rng = new Random();
		x = rng.nextInt(1024);
		y = 0;
		radius = 10;
		angle = 135 * this.rng.nextDouble() - 135;
		if (angle <= 20 && angle >= 0) {
			angle += 20;
		} else if (angle >= -20 && angle < 0) {
			angle -= 20;
		}
		roundedAngle = Math.abs((int)Math.round(angle/15)*15);
		
		String spriteLoc2 = "Sprites/enemyPlaceholder_R-" + roundedAngle + ".png";
		System.out.println("Theta = " + roundedAngle);
		System.out.println(spriteLoc2);
		
		
		
		sprite = SpriteStore.get().getSprite(spriteLoc2);
/*
 * 
		if (angle < 0.3) {
			angle += 0.1;
		}
		else if (angle > 0.7) {
			angle -= 0.1;
		}
		angle = -100 * angle;
*/
		isHit = false;
		
		this.hitbox = new GRectangle(x, y, Missile.WIDTH, Missile.HEIGHT); //TODO need to make the hit box reflect the orientation/size of the missile
		this.sprite.scale(SCALE, SCALE);
		
		//TODO remove, test only to generate the boxes for visual example
//		hitbox.setColor(Color.BLUE);
//		hitbox.setFilled(true);

		System.out.print("r" + radius + "a" + angle + "\n" );
		
		//TODO remove, test only to generate the boxes for visual example
		if(this.DEBUG_MODE == true){
			this.debugHitbox = new GRect(x, y, Missile.WIDTH, Missile.HEIGHT); 
		}
	
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
	 */
	public Missile(String spriteLoc, boolean side, int x, int y, MainApplication app, int mouseX, int mouseY) {
		double theta =Math.toDegrees(Math.atan2((mouseY - y),(mouseX - x)));
		roundedAngle = Math.abs((int)Math.round(theta/15)*15);
		
		String spriteLoc2 = "Sprites/friendlyPlaceholder_R-" + roundedAngle + ".png";
		System.out.println("Theta = " + roundedAngle);
		System.out.println(spriteLoc2);
		
		
		sprite = SpriteStore.get().getSprite(spriteLoc2);
		isFriendly = side;
		isDestroyed = false;
		this.x = x;
		this.y = y;
		radius = 10;
		
		
//		if (isFriendly)
//			radius *= -1;
		
		
		double dx = mouseX - this.x;
		double dy = mouseY - this.y;
		angle = Math.abs(Math.toDegrees(Math.atan2(dy, dx)));
		isHit = false;
		
		
		
		this.hitbox = new GRectangle(x, y, Missile.WIDTH, Missile.HEIGHT); //TODO need to make the hit box reflect the orientation/size of the missile
		this.sprite.scale(SCALE, SCALE);
		
		System.out.print("x: " + mouseX + " y: " + mouseY + " a: " + angle + "\n");
		
		//TODO remove, test only to generate the boxes for visual example
		if(this.DEBUG_MODE == true){
			this.debugHitbox = new GRect(x, y, Missile.WIDTH, Missile.HEIGHT); 
		}
	}
	
	public void draw(MainApplication app) {
		sprite.draw(app, this.x, this.y);
		
		//TODO remove, test only to generate the boxes for visual example
		if(this.DEBUG_MODE == true){
			app.add(this.debugHitbox);
		}
		
	}
	
	public void move() {
		sprite.getImage().movePolar(radius, angle);
		this.x = (int) sprite.getImage().getX();
		this.y = (int) sprite.getImage().getY();
		this.hitbox.setLocation(this.x, this.y);
		
		//TODO remove, test only to generate the boxes for visual example
		if(this.DEBUG_MODE == true){
			this.debugHitbox.setLocation(this.x, this.y);
		}
		
	//	move((int) (radius*Math.cos(angle)), (int) (radius*Math.sin(angle)));

		/*
		if (left) {
			this.x -= (int) (radius*Math.cos(angle));
			this.hitbox.move((int) -(radius*Math.cos(angle)), (int) (radius*Math.sin(angle)));
		}
		else {
			this.x += (int) (radius*Math.cos(angle));
			this.hitbox.move((int) (radius*Math.cos(angle)), (int) (radius*Math.sin(angle)));
		}
		this.y += (int) (radius*Math.sin(angle)); 
		*/
	}
	
	/**
	 * @return the isDestroyed
	 */
	public boolean isDestroyed() {
		return this.isDestroyed;
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
	public GRectangle getHitBox(){
		return this.hitbox;
	}
	
	/**
	 * @return missile angle
	 */
	public double getAngle() {
		return angle;
	}
}
