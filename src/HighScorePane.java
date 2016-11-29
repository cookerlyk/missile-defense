import java.awt.event.MouseEvent;

import acm.graphics.*;

public class HighScorePane extends GraphicsPane{
	
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GImage background;
	private GButton backButton;
	private GLabel score;
	private HighScores scoreTable;
	private static final int SPACER = 50;
		
	public HighScorePane(MainApplication app) {
		this.program = app;
		background = new GImage("Screens/High_Score_Placeholder.png", 0, 0);
		backButton = new GButton("Back", 100, 100, 100, 100);
		scoreTable = new HighScores();
		score = new GLabel("a \n n", 300, 200);
		
		//this.getScores();
	}
	/*
	public void getScores() {
		this.score.setLabel(scoreTable.printScores());
	}
	*/
	@Override
	public void showContents() {
		program.add(background);
		program.add(backButton);
		program.add(score);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(backButton);
		program.remove(score);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == backButton) {
			program.switchBack();
		}
	}
}
