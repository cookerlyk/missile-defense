import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class HighScorePane extends GraphicsPane{
	
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GImage background;
	private GButton backButton;
	private GLabel[] scores;
	private HighScores scoreTable;
	private GRect backing;
	private static final int SPACER = 50;
		
	public HighScorePane(MainApplication app) {
		this.program = app;
		this.background = new GImage("Screens/High_Score_Placeholder.png", 0, 0);
		this.backing = new GRect(375, 130, 300, 500);
		this.backing.setFillColor(Color.WHITE);
		this.backing.setFilled(true);
		this.backButton = new GButton("Back", 100, 100, 100, 100);
		this.scoreTable = new HighScores();
		this.scores = new GLabel[scoreTable.getNum()];
		for (int i = 0; i < this.scoreTable.getNum(); i++) {
			scores[i] = new GLabel("", 0, 0);
		}
		this.getScores();
	}
	
	public void getScores() {
		for (int i = 0; i < this.scoreTable.getNum(); i++) {
			scores[i].setLabel(this.scoreTable.getTable()[i][0] + " " + this.scoreTable.getTable()[i][1]);
			scores[i].setLocation(480, 160+i*SPACER);
			scores[i].setFont("Arial-Bold-20");
		}
	}
	public void addScores() {
		program.add(this.backing);
		for (GLabel score: this.scores) {
			program.add(score);
		}
	}
	public void remScores() {
		for (GLabel score: this.scores) {
			program.remove(score);
		}
		program.remove(this.backing);
	}
	
	@Override
	public void showContents() {
		program.add(background);
		program.add(backButton);
		addScores();
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(backButton);
		remScores();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == backButton) {
			program.switchBack();
		}
	}
}
