import java.awt.Color;
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
	protected MainApplication game;
	
	
	protected double SCALE;
	
	protected String spriteLoc;
	protected String destroyedSpriteLoc;
	
	protected final boolean DEBUG_MODE = true; // set to false if you want the hit boxes to not appear on screen
	protected GRect debugHitbox;
	
	/**
 	 * @param x: x location of the structure
	 * @param y: y location of the structure
	 * @param loc: image location of the sprite
	 */
	public Structure(MainApplication app, int x, int y, String spriteLoc, String destroyedSpriteLoc){
		this.spriteLoc = spriteLoc;
		this.destroyedSpriteLoc = destroyedSpriteLoc;
		destroyed = false;
		this.x = x;
		this.y = y;

		game = app;
		
		
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
		this.sprite.remove(game);
		this.debugHitbox.setFillColor(Color.red);
		this.sprite = SpriteStore.get().getSprite(destroyedSpriteLoc);
		this.sprite.scale(SCALE, SCALE);
		sprite.draw(game,(int) x,(int) y);
	}
	
	/**
	 * Resets the structure state to not destroyed
	 */
	public void reset(){
		destroyed = false;
		this.sprite.remove(game);
		this.debugHitbox.setFillColor(Color.blue);
		this.sprite = SpriteStore.get().getSprite(spriteLoc);
		this.sprite.scale(SCALE, SCALE);
		sprite.draw(game,(int) x,(int) y);
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