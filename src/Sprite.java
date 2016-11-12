import java.awt.Image;
import java.awt.Graphics;
import acm.graphics.GImage;

public class Sprite {
	private GImage image;
	
	public Sprite(GImage image) {
		this.image = image;
	}
	
	
	
	public int getWidth(){
		return  (int)image.getWidth();
	}
	
	public int getHeight(){
		return (int)image.getHeight();
	}
	/**
	 * Draws a sprite in a location when called.
	 */
	public void draw(MainApplication p, int x, int y){
		image.setLocation(x, y);
		p.add(image);
	}
	
	public void remove(MainApplication p){
		p.remove(image);
	}
}
