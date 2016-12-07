import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class MenuPane extends GraphicsPane {
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	
	private GButton playGame;
	private GButton tutorial;
	private GButton highScore;
	private GButton exit;
	
	private GImage background;
	
	public MenuPane(MainApplication app) {
		program = app;
		
		playGame = new GButton("Play", 384, 75, 200, 100);
		tutorial = new GButton ("Tutorial", 384, 200, 200, 100);
		highScore = new GButton("High Score", 384, 325, 200, 100);
		exit = new GButton("Exit", 384, 450, 200, 100);
		
		playGame.setFillColor(Color.RED);
		tutorial.setFillColor(Color.RED);
		highScore.setFillColor(Color.RED);
		exit.setFillColor(Color.RED);
		
		background = new GImage("Screens/missile_defense_menu.png", 0, 0);
	}
	
	@Override
	public void showContents() {
		program.add(background);
		program.add(playGame);
		program.add(tutorial);
		program.add(highScore);
		program.add(exit);
	}

	@Override
	public void hideContents() {
		program.remove(playGame);
		program.remove(background);
		program.remove(tutorial);
		program.remove(highScore);
		program.remove(exit);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == playGame) {
			program.switchToSome();
		}
		if(obj == tutorial){
			program.switchToTutorial();
		}
		
		if (obj == highScore){
			program.switchToHighScore();
		}
		
		if (obj == exit){
			System.exit(0); //TODO possibly find a better way to exit the program
			//program.destroy();
		}
		else return;
	}

}
