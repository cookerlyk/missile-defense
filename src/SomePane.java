import java.awt.event.MouseEvent;
import java.util.List;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SomePane extends GraphicsPane {
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GImage img;
	private Level lvl;
	private Turret test;
	private Missile testMiss;
	
	public SomePane(MainApplication app) {
		this.program = app;
		img = new GImage("robot head.jpg", 100, 100);
		lvl = new Level(program);
		testMiss = new Missile(program, "Sprites/Missile_placeholder.png");
		test = new Turret(program,370,550,"Sprites/turret_Placeholder.png");
	}
	
	@Override
	public void showContents() {
		program.add(img);
		test.draw(program);
		//testMiss.draw(program);
		System.out.println(lvl.getGameObject().generateEnemyMissile("Sprites/Missile_placeholder.png", false, program));
		lvl.getGameObject().generateEnemyMissile("Sprites/Missile_placeholder.png", false, program);
		for(Missile missile: lvl.getGameObject().getEnemyMissilesOnStage()){
			missile.draw(program);
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

}
