import java.util.*;
import java.io.*;
import java.io.*;

/**
 * 
 * @author obaid
 *
 */

public class HighScores {
	private String[][] scoreArray;
	
	public void readHS() {
		scoreArray = new String[10][2];
		Scanner sc = new Scanner(new File("HighScores.txt"));
		for (int i = 0; i < 10; i++) {
			String data[] = sc.nextLine().split(" ");
			scoreArray[i][0] = data[0];
			scoreArray[i][1] = data[1];
		}
		sc.close();
	}
	
	public String[][] submitScore() {
		String[][] score = new String[1][1];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input name: ");
		String s = in.readLine();
		score[0][0] = s;
		
		System.out.print("Input score: ");
		s = in.readLine();
		score[0][1] = s;
	}
	
	public void addScore(String[1][1] score) {
		
	}
	
	public void drawSceen() {
		for (int i = 0; i < 10; i++) {
			System.out.print("Name: " + scoreArray[i][0] + " Score: " + scoreArray[i][1]);
		}
	}
	
	public void back() {
		
	}
}