import java.util.*;
import java.io.*;
import import java.awt.event.MouseEvent;
import acm.graphics.*;

/**
 * 
 * @author obaid
 *
 */

public class HighScores {
	private String[][] scoreArray;
	private static int NUM_SCORES = 10;
	private MainApplication program;
	private GButton backButton;
	
	public HighScores(MainApplication app) {
		this.program = app;
		backButton = new GButton("Back", 100, 100, 100, 100);
		scoreArray = new String[NUM_SCORES][2];
	}
	
	public void readHS() {
		Scanner sc = new Scanner(new File("HighScores.txt"));
		for (int i = 0; i < NUM_SCORES; i++) {
			String data[] = sc.nextLine().split(" ");
			scoreArray[i][0] = data[0];
			scoreArray[i][1] = data[1];
		}
		sc.close();
	}
	
	public String[] submitScore() {
		String[] score = new String[2];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input name: ");
		String s = in.readLine();
		score[0] = s;
		
		System.out.print("Input score: ");
		s = in.readLine();
		score[1] = s;
		return score;
	}
	
	public void addScore() {
		String[] newScore = submitScore();
		sort();
		for (int i = 0; i < NUM_SCORES; i++) {
			if (Integer.parseInt(newScore[1]) < Integer.parseInt(scoreArray[i][1])) {
				;
			}
			else {
				scoreArray[i][0] = newScore[0];
				scoreArray[i][1] = newScore[1];
			}
		}
		sort();		
	}
	
	public void drawSceen() {
		for (int i = 0; i < 10; i++) {
			System.out.print("Name: " + scoreArray[i][0] + " Score: " + scoreArray[i][1]);
		}
	}
	
	private void sort() {
		int j;
		for (int i = 1; i < NUM_SCORES-1; i++) {
			j = i;
			while (j > 0 && Integer.parseInt(scoreArray[j-1][1]) > Integer.parseInt(scoreArray[j][1])) {
				String[] temp = new String[2];
				
				temp [0] = scoreArray[j-1][0];
				temp[1] = scoreArray[j-1][1];
				
				scoreArray[j-1][0] = scoreArray[j][0];
				scoreArray[j-1][1] = scoreArray[j][1];
				
				scoreArray[j][0] = temp[0];
				scoreArray[j][1] = temp[1];
				
				j--;
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == backButton) {
			program.switchBack();
		}
	}
}