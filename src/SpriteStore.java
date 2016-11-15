import java.util.HashMap;

import javax.imageio.ImageIO;

import acm.graphics.GImage;



/**
 * 
 * Creates a hashmap of Sprite objects so it only needs to be read in once. 
 *
 */
public class SpriteStore {
	private static SpriteStore store = new SpriteStore();
	private HashMap sprites = new HashMap();
	public static SpriteStore get() {
		return store;
	}
	
	
	/**
	 * 
	 * @param name is the name of the the location/filename of the sprite that you want to pull from. 
	 * If the name is already in the hashmap, it will pull the sprite from the hashmap. 
	 * if it isnt in the hashmap, it will create a new GImage, create a sprite of the GImage, and store it inside the hashmap. 
	 * @return requested sprite.
	 */
	public Sprite getSprite(String name){
		GImage img = new GImage(name);
		if (sprites.get(name) != null) {
			Sprite temp = new Sprite(img);
			return temp;
		}
		Sprite image = new Sprite(img);
		sprites.put(name, image);
		return image;
	}
	
}
