import java.util.*;
import java.io.*;
import acm.graphics.*;

/**
 * 
 * @author obaid
 *
 * Backend for HighScoresPane
 *
 */

public class HighScores {
	private String[][] scoreArray;
	private static int NUM_SCORES = 10;
	
	public HighScores() {
		Scanner sc = new Scanner(new File("media/HighScores.txt"));
		for (int i = 0; i < NUM_SCORES; i++) {
			String data[] = sc.nextLine().split(" ");
			scoreArray[i][0] = data[0];
			scoreArray[i][1] = data[1];
		}
		sc.close();
	}
	
	/**
	 * Generates a score-holding array for addScore()
	 */
	private String[] submitScore(String name, int scoreval) {
		String[] score = new String[2];
		score[0] = name;
		score[1] = Integer.toString(scoreval);
		return score;
	}
	
	/**
	 * 
	 * @param name: taken from Level/Gameplay/GraphicsGame
	 * @param scoreval: taken from Level
	 */
	public void addScore(String name, int scoreval) {
		String[] newScore = submitScore(name, scoreval);
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
		
		PrintWriter pr = new PrintWriter("media/HighScores.txt");
		pr.print(""); // blanks the HighScores.txt
		pr.print(printScores()); // Refills file with the new list of scores
		pr.close();
	}
	
	public String printScores() {
		String list = "";
		for (int i = 0; i < 10; i++) {
			list += scoreArray[i][0] + scoreArray[i][1] + "\n";
		}
		return list;
	}
	
	/**
	 * Sort using insertion sort algorithm
	 */	
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
}