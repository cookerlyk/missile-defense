import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;



public class SomePane extends GraphicsPane implements ActionListener{
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GImage img;
	private Level lvl;
	private Turret test;
	private Turret test2;
	private Timer move;
	
	
	
	public SomePane(MainApplication app) {
		this.program = app;
		img = new GImage("robot head.jpg", 100, 100);
		lvl = new Level(program);
		test = new Turret(program,0,550,"Sprites/turret_Placeholder.png");
		test2 = new Turret(program,750,550,"Sprites/turret_Placeholder.png");
		
		game = new Gameplay();
	}
	
	//@Override
	private Gameplay game; //TODO probably don't need this 
	
	
	public void showContents() {
		test.draw(program);
		test2.draw(program); //TODO something broken here with drawing two turrets
		this.run();
		
	
	}
	
	public void run(){
		move = new Timer(80, (ActionListener) this);
		move.setInitialDelay(1000);
		move.start();
		lvl.getGameObject().generateEnemyMissile("Sprites/Missile_placeholder.png", false, program);
		lvl.getGameObject().generateEnemyMissile("Sprites/Missile_placeholder.png", false, program);
		lvl.getGameObject().generateEnemyMissile("Sprites/Missile_placeholder.png", false, program);
		this.testGeneration();
	}
	
	public void actionPerformed(ActionEvent e){
		for(Missile missile: lvl.getGameObject().getEnemyMissilesOnStage()){
			missile.draw(program);
			missile.move();
			//missile.draw(program); // Redundant
			
		}
	}

	@Override
	public void hideContents() {
		program.remove(img);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == img) {
			program.switchBack();
		}
	}

	private void testGeneration(){
		System.out.println(lvl.getGameObject().getEnemyMissilesOnStage().size());
	}
}
