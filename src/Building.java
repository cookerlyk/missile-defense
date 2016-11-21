import java.awt.Color;
import acm.graphics.GRect;

public class Building extends Structure {
	public Building(MainApplication app, int x, int y, String loc){
		super(app, x, y, loc);
		
		this.height = 100;
		this.width = 100;
		
		hitbox = new GRect(x, y, width, height);
		
		//TODO remove, test only to generate the boxes for visual example
		hitbox.setColor(Color.BLUE);
		hitbox.setFilled(true);
		app.add(hitbox);
	}
	
	
}
