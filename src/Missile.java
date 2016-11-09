import java.util.*;

public class Missile extends Sprite {
	private Sprite sprite;
	private ObjectState state;
	private boolean isFriendly, isDestroyed;
	private int x, y, velocity;
	private String path;
	private const int WIDTH, HEIGHT;

	public Missile() { // change
		WIDTH = ;// some width
		HEIGHT = ; // some height
		x = rand.nextInt(WIDTH); // Not sure
		y = 0;
		isFriendly = false;
		isDestroyed = false;
		path = "";
		velocity = 0;
	}
	public Missile(Sprite spr, ObjectState s, boolean friendFoe, boolean status, int xCo, int yCo, int vel, String p, const int W, const int H) {
		sprite = spr;
		state = s;
		isFriendly = friendFoe;
		isDestroyed = status;
		x = xCo;
		y = yCo;
		velocity = vel;
		path = p;
		WIDTH = W;
		HEIGHT = H;			
	}

	public boolean getIsFriendly() {
		return isFriendly;
	}
	public boolean getIsDestroyed() {
		return isDestroyed;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getVelocity() {
		return velocity;
	}
	public String getPath() {
		return path;
	}
	public const int getWidth() {
		return WIDTH;
	}
	public const int getHeight() {
		return WIDTH;
	}

	public void setX(int newX) {
		x = nX;
	}
	public void setY(int newY) {
		y = nY;
	}
	public void setVelocity(int v) {
		velocity = v;
	}
	public void setIsDestroyed(boolean newStatus) {
		isDestroyed = newStatus;
	}
	public void setPath(String p) {
		path = p;
	}
}
