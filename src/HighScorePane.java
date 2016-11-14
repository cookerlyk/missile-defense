import java.awt.event.MouseEvent;

import acm.graphics.*;

public class HighScorePane extends GraphicsPane{
	
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GImage background;
	private GButton backButton;
	private GLabel score;
	private HighScores scoreTable;
		
	public HighScorePane(MainApplication app) {
		this.program = app;
		background = new GImage("Screens/High_Score_Placeholder.png", 0, 0);
		backButton = new GButton("Back", 100, 100, 100, 100);
		score = new GLabel("", 1, 1);
		scoreTable = new HighScores();
	}
	
	public void getScores() {
		score.setLabel(scoreTable.printScores());
	}
	
	@Override
	public void showContents() {
		program.add(background);
		program.add(backButton);
	}

	@Override
	public void hideContents() {
		program.remove(background);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == backButton) {
			program.switchBack();
		}
	}
}
