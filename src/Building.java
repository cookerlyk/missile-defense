import java.awt.Color;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class Building extends Structure {
	public Building(MainApplication app, int x, int y, String loc){
		super(app, x, y, loc);
		
		this.height = 75;
		this.width = 100;
		
		hitbox = new GRectangle(x, y, width, height);
		
		
		//TODO remove, test only to generate the boxes for visual example
		if(this.DEBUG_MODE == true){
			debugHitbox = new GRect(x, y, width, height);
			debugHitbox.setColor(Color.BLUE);
			debugHitbox.setFilled(true);
			app.add(debugHitbox);
		}
		
	}
	
	
}
