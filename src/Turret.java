
public class Turret extends Structure{
	private int ammo;
	
	
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
	void fireMissile(MainApplication app, int x, int y){
		if(canFire()){
			ammo -=1;
		}
	}
}
