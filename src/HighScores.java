import java.util.*;
import java.io.*;

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
	
	public void drawSceen() {
		
	}
	
	public void back() {
		
	}
}