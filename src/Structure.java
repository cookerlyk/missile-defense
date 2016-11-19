import java.util.*;
import acm.graphics.GObject;
import acm.graphics.GRectangle;

public class Structure {
	protected int x; 
	protected int y;
	protected int height;
	protected int width;
	protected Sprite sprite;
	protected boolean destroyed;
	protected GRectangle hitbox;
	/**
 	 * @param x: x location of the structure
	 * @param y: y location of the structure
	 * @param loc: image location of the sprite
	 */
	public Structure(MainApplication app, int x, int y, String spriteLoc){
		destroyed = false;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		hitbox = new GRectangle(width, height);
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
		
	}
	
	
	/**
	 * Resets the structure state to not destroyed
	 */
	public void reset(){
		destroyed = false;
	}
	
	
	/**
	 * 
	 * @return the current structure state
	 */
	public boolean isDestoryed(){
		return destroyed;
	}
	
	public GRectangle getHitBox(){
		return this.hitbox;
	}
}