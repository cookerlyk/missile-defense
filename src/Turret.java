import java.util.*;

public class Turret extends Structure{
	private int ammo = 10;
	
	
	public Turret(MainApplication app, int x, int y, String loc){
		super(app, x, y, loc);
	}
	
	/**
	 * 
	 * @return is the turret able to fire a missile
	 */
	boolean canFire(){
		if(ammo > 0 && this.destroyed == false){
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
			
			Missile missile = new Missile("Sprites/Missile_placeholder.png", true, this.x, this.y, app, x, y);
			m.add(missile);
		}
		
	}
}
