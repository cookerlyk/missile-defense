import java.awt.Color;
import java.util.*;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class Structure {
	protected int x; 
	protected int y;
	protected int height;
	protected int width;
	protected Sprite sprite;
	protected boolean destroyed;
	protected GRectangle hitbox;
	
	protected final boolean DEBUG_MODE = true; // set to false if you want the hit boxes to not appear on screen
	protected GRect debugHitbox;
	
	/**
 	 * @param x: x location of the structure
	 * @param y: y location of the structure
	 * @param loc: image location of the sprite
	 */
	public Structure(MainApplication app, int x, int y, String spriteLoc){
		destroyed = false;
		this.x = x;
		this.y = y;

		this.sprite = SpriteStore.get().getSprite(spriteLoc);
	}
	
	public void draw(MainApplication app){
		sprite.draw(app,(int) x,(int) y);
	}
	
	/**
	 * Sets the structure state to destroyed
	 */
	public void destroy(){
		destroyed = true;
		this.debugHitbox.setFillColor(Color.red);
	}
	
	/**
	 * Resets the structure state to not destroyed
	 */
	public void reset(){
		destroyed = false;
		this.debugHitbox.setFillColor(Color.blue);
	}
	
	/**
	 * 
	 * @return the current structure state
	 */
	public boolean isDestroyed(){
		return destroyed;
	}
	
	/*
	 * returns the hitbox for the structure
	 */
	public GRectangle getHitBox(){
		return this.hitbox;
	}
}