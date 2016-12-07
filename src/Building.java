import java.awt.Color;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class Building extends Structure {
	public Building(MainApplication app, int x, int y, String loc, String destroyedLoc){
		super(app, x, y, loc, destroyedLoc);
		SCALE = 0.4;
		this.height = 90;
		this.width = 100;
		
		hitbox = new GRectangle(x + 10, y + 25, width, height);
		
		
		// If debug mode is true in structure, display the hit boxes to the screen
		if(this.DEBUG_MODE == true){
			debugHitbox = new GRect(x + 10, y + 25, width, height);
			debugHitbox.setColor(Color.BLUE);
			debugHitbox.setFilled(true);
			app.add(debugHitbox);
		}
		
	}
	
}
