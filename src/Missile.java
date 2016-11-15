import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import acm.graphics.*;



/** 
 * 
 * @author Obaid Rehman 
 *
 **/

public class Missile implements ActionListener {
	
	private Sprite sprite;
	private boolean isFriendly, isDestroyed, isHit;
	private int x, y, dx, dy;
	//private Random rng;
	private static int WIDTH = 50, HEIGHT = 50;
	private Timer movement;
	private final int MS = 50;
	/**
	 * Default constructor. Intended for enemies.
	 */
	public Missile(MainApplication app, String spriteLoc) {
		sprite = SpriteStore.get().getSprite(spriteLoc);
		isFriendly = false;
		isDestroyed = false;
		//this.rng = new Random();
		//x = rng.nextInt(1024);
		y = 0;
		dy = 10;
		dx = ThreadLocalRandom.current().nextInt(-10, 11);
		isHit = false;
	}
	/**
	 * Intentional use is for friendly missiles.
	 * @param s Sprite
	 * @param side Friend or foe / true or false
	 * @param status Destroyed or not
	 * @param x starting x coordinate
	 * @param y starting y coordinate
	 */
	
	public Missile(String spriteLoc, boolean side, int x, int y, MainApplication app) {
		sprite = SpriteStore.get().getSprite(spriteLoc);
		isFriendly = side;
		isDestroyed = false;
		this.x = x;
		this.y = y;
		dy = 10;
		if (isFriendly) 
			dy *= -1;
		dx = ThreadLocalRandom.current().nextInt(-10, 11); //change
		isHit = false;
	}
	
	public void run(){
		movement = new Timer(MS, this);
		movement.setInitialDelay(0);
		movement.start();
		System.out.println(movement.isRunning());
	}
	
	public void actionPerformed(ActionEvent e){
		this.setX(x+dx);
		this.setY(y+dy);
		System.out.println(x + "  " + y);
	}
	
	public void move() {
		this.x += dx;
		this.y += dy;
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
	
	public void draw(MainApplication app) {
		sprite.draw(app, (int) x, (int) y);
		
	}
}
