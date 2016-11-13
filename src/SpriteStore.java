import java.util.HashMap;

import javax.imageio.ImageIO;

import acm.graphics.GImage;

public class SpriteStore {
	private static SpriteStore store = new SpriteStore();
	private HashMap sprites = new HashMap();
	
	public static SpriteStore get() {
		return store;
	}
	
	public Sprite getSprite(String name){
		if (sprites.get(name) != null) {
			return (Sprite) sprites.get(name);
		}
		
		GImage img = new GImage(name);
		Sprite image = new Sprite(img);
		sprites.put(name, image);
		return image;
	}
	
}
