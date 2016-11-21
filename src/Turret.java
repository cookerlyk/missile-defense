import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.*;

public class Turret extends Structure implements ActionListener{
	
	private static int TIMERTICK = 1000;
	private static int DELAYTIME = 2000;
	private static int SHOTDELAY = 1000;
	
	private int ammo = 10;
	private Timer timer;
	private int fireDelay;
	
	public Turret(MainApplication app, int x, int y, String loc){
		super(app, x, y, loc);
		run();
	}
	
	/**
	 * 
	 * @return is the turret able to fire a missile
	 */
	boolean canFire(){
		if(ammo > 0 && this.destroyed == false && fireDelay <= 0){
			return true;
		}
		else return false;
	}
	
	/**
	 * 
	 * @param x: the x location of the mouse when fireMissile is Called
	 * @param y: the y location of the mouse when fireMissile is Called
	 */
	void fireMissile(MainApplication app, Level lvl, int x, int y, List<Missile> m){
		if(canFire()){
			ammo -=1;
			fireDelay = DELAYTIME;
			Missile missile = new Missile("Sprites/Missile_placeholder.png", true, this.x, this.y, app, x, y);
			m.add(missile);
		}
		
	}
	
	public void run(){
		timer = new Timer(TIMERTICK, this);
		timer.setInitialDelay(0);
		timer.start();
		System.out.println(fireDelay);
	}
	
	public void actionPerformed(ActionEvent e){
		if (fireDelay > 0){
			fireDelay -=SHOTDELAY;
		}
		System.out.println(fireDelay);
	}
}
