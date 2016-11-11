import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;


public class TutorialPane extends GraphicsPane{

	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GImage tut1;
	private GImage tut2;
	private GButton backButton;
	
	
	public TutorialPane(MainApplication app) {
		this.program = app;
		backButton = new GButton("Back", 100, 100, 100, 100);
		tut1 = new GImage("Screens/tutorial_1_placeholder.png",0, 0);
		//tut2 = new GImage("Screens/tutorial_2_placeholder.png", 0, 0);
	}
	
	@Override
	public void showContents() {
		program.add(tut1);
		program.add(backButton);
	}

	@Override
	public void hideContents() {
		//program.remove(tut1);
		program.remove(backButton);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == backButton) {
			program.switchBack();
		}
	}
	
}
