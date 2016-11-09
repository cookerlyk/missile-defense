import java.util.*;

public class Structure {
	protected int x; 
	protected int y;
	protected Sprite sprite;
	private boolean destroyed;
	
	/**
 	 * @param x: x location of the structure
	 * @param y: y location of the structure
	 * @param loc: image location of the sprite
	 */
	public Structure(int x, int y, String loc){
		destroyed = false;
		this.x = x;
		this.y = y;
		//this.sprite = 
		
	}
	
	public void Draw(){
		sprite.draw(x, y);
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
}