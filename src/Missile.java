import Random;

public class Missile {
	private Sprite sprite;
	private boolean isFriendly, isDestroyed;
	private int x, y, velocity, dx, dy;
	private static int WIDTH = 50, HEIGHT = 50;
	private boolean isHit;

	public Missile() { // change
		WIDTH = ;// some width
		HEIGHT = ; // some height
		x = rand.nextInt(WIDTH); // Not sure
		y = 0;
		isFriendly = false;
		isDestroyed = false;
		dx = dy = rand.Random();
		velocity = 0;
		isHit = false;
	}
	public Missile(Sprite spr, boolean friendFoe, int xCo, int yCo, int vel, int dx, int dy) {
		sprite = spr;
		isFriendly = friendFoe;
		isDestroyed = false;
		x = xCo;
		y = yCo;
		velocity = vel;
		this.dx = dx;
		this.dy = dy;
		isHit = false;
	}
	public boolean isFriendly() {
		return isFriendly;
	}
	public void setFriendly(boolean isFriendly) {
		this.isFriendly = isFriendly;
	}
	public boolean isDestroyed() {
		return isDestroyed;
	}
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getVelocity() {
		return velocity;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public int getWIDTH() {
		return WIDTH;
	}
	public int getHEIGHT() {
		return HEIGHT;
	}
	
}
