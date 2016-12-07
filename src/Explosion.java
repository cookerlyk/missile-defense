import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import acm.graphics.*;

/**
 * 
 * @author obaid
 *
 */

public class Explosion implements ActionListener {
	private final String path = "FX/kaboom.png";
	private GImage kaboom;
	private Timer t;
	private int counter, speed, x, y;
	private static final int MAX_COUNT = 3;
	private MainApplication program;
	
	public Explosion(int speed, int x, int y, MainApplication app) {
		t = new Timer(speed, (ActionListener) this);
		kaboom = new GImage(path, x, y);
		kaboom.setLocation(x-kaboom.getWidth()/2, y-kaboom.getHeight()/2);
		program = app;
		counter = 0;
		this.t.setInitialDelay(800);
		this.t.setDelay(800);
	}
	public void start() {
		this.t.start();
	}
	
	public void add() {
		program.add(this.kaboom);
	}
	
	public void stop() {
		this.t.stop();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		program.remove(kaboom);
		stop();
	}
}
