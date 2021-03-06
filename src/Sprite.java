import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import acm.graphics.GImage;
import acm.graphics.GPolygon;

public class Sprite {
	private GImage image;
	private Image rotatedImage;
	
	public Sprite(GImage image) {
		this.image = image;
	}
	
	public Sprite(GImage image, double xScale, double yScale){
		this.image = image;
		this.image.scale(xScale, yScale);
	}
	
	
	public void scale(double xScale, double yScale){
		this.image.scale(xScale, yScale);
	}
	
	public Sprite getSprite(){
		return this;
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
	
	public GImage getImage(){
		return this.image;
	}

}
