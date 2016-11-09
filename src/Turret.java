
public class Turret extends Structure{
	private int ammo;
	
	
	public Turret(int x, int y, String loc){
		super(x, y, loc);
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
	
	void fireMissile(){
		if(canFire()){
			ammo -=1;
			/*
			 * create missile here. 
			 */
		}
	}
}
