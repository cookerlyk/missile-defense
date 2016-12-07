import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GObject;


public class MenuPane extends GraphicsPane {
	
	public static final int WINDOW_WIDTH = 1024;
	public static final int BUTTON_WIDTH = 150;
	public static final int BUTTON_HEIGHT = 75;
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GButton playGame;
	private GButton instructions;
	private GButton highScore;
	private GButton exit;
	
	private GImage background;
	
	public MenuPane(MainApplication app) {
		program = app;
		playGame = new GButton("Play", WINDOW_WIDTH/2 - BUTTON_HEIGHT, 75, BUTTON_WIDTH, BUTTON_HEIGHT);
		instructions = new GButton ("Instructions", WINDOW_WIDTH/2 - BUTTON_HEIGHT, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		highScore = new GButton("High Scores", WINDOW_WIDTH/2 - BUTTON_HEIGHT, 325, BUTTON_WIDTH, BUTTON_HEIGHT);
		exit = new GButton("Exit", WINDOW_WIDTH/2 - BUTTON_HEIGHT, 450, BUTTON_WIDTH, BUTTON_HEIGHT);
		playGame.setFillColor(Color.RED);
		instructions.setFillColor(Color.RED);
		highScore.setFillColor(Color.RED);
		exit.setFillColor(Color.RED);
		
		background = new GImage("Screens/missile_defense_menu.png", 0, 0);
	}
	
	@Override
	public void showContents() {
		program.add(background);
		program.add(playGame);
		program.add(instructions);
		program.add(highScore);
		program.add(exit);
	}

	@Override
	public void hideContents() {
		program.remove(playGame);
		program.remove(background);
		program.remove(instructions);
		program.remove(highScore);
		program.remove(exit);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == playGame) {
			program.switchToSome();
		}
		if(obj == instructions){
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
