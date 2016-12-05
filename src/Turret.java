import java.util.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.math.*;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class Turret extends Structure implements ActionListener{
	
	
	private static int TIMERTICK = 1000; // timer ticks every MS
	private static int DELAYTIME = 1000;
	private static int SHOTDELAY = 1000;

	
	
	AudioPlayer audio = AudioPlayer.getInstance();
	
	private int MAXAMMO = 15;
	private int ammo = 15;
	private Timer timer;
	private int fireDelay;
	
	
	public Turret(MainApplication app, int x, int y, String loc, String destroyedLoc){
		
	
		
		super(app, x, y, loc, destroyedLoc);
		SCALE = 0.3;
		this.height = 100;
		this.width = 100;
		hitbox = new GRectangle(x, y, width, height);
		
		debugHitbox = new GRect(x, y, width, height);
		
		//TODO remove, test only to generate the boxes for visual example
		if(this.DEBUG_MODE == true){
			debugHitbox = new GRect(x, y, width, height);
			debugHitbox.setColor(Color.BLUE);
			debugHitbox.setFilled(true);
			app.add(debugHitbox);
		}
		
		run();
	}
	
	/**
	 * 
	 * @return is the turret able to fire a missile
	 */
	boolean canFire(double mY){		
		//System.out.println("Fire Angle = " + theta);
		if(ammo > 0 && this.destroyed == false && fireDelay <= 0 && mY < y){
			
			
			
			//Change this to firing missile sound.
			//audio.playSound("sounds", "Missile Fire.WAV");
			
			
			return true;
		}
		else{
			if(ammo == 0 || mY > y){
				audio.playSound("sounds", "Fire Error.WAV");
			}
			//Change this to missile firing error sound
			return false;
		}
	}
	
	/**
	 * 
	 * @param x: the x location of the mouse when fireMissile is Called
	 * @param y: the y location of the mouse when fireMissile is Called
	 */
	void fireMissile(MainApplication app, Level lvl, int x, int y, List<Missile> m){
		if(canFire(y)){
			ammo -=1;
			fireDelay = DELAYTIME;
			audio.playSound("sounds", "Missile Fire.WAV");
//			double theta =Math.toDegrees(Math.atan2((y - this.y),(x - this.x)));
//			double rounded = Math.round(theta/15)*15;
//			System.out.println("Theta = " + rounded);
			Missile missile = new Missile("Sprites/friendlyPlaceholder.png", true, this.x, this.y, app, x, y); //TODO fix user firing
			m.add(missile);
		}
		
	}
	
	public void run(){
		timer = new Timer(TIMERTICK, this);
		timer.setInitialDelay(0);
		timer.start();
//		System.out.println(fireDelay);
	}
	
	public void actionPerformed(ActionEvent e){
		if (fireDelay > 0){
			fireDelay -=SHOTDELAY;
		}
		
		
		
	//	System.out.println(fireDelay);
	}
	
	public int getMissileCount(){
		return ammo;
	}
	
	
	@Override
	public void reset(){
		destroyed = false;
		this.sprite.remove(game);
		this.sprite = SpriteStore.get().getSprite(spriteLoc);
		this.sprite.scale(SCALE, SCALE);
		sprite.draw(game,(int) x,(int) y);
		this.debugHitbox.setFillColor(Color.blue);
		ammo = MAXAMMO;
	}
}
