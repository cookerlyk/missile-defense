import java.util.*;
import java.io.*;

/**
 * 
 * @author obaid
 *
 * Backend for HighScoresPane
 *
 */

public class HighScores {
	private String[][] scoreArray;
	private static final int NUM_SCORES = 10;
	private File sf;
	
	public HighScores() {
		scoreArray = new String[NUM_SCORES][2];
		for (int i = 0; i < NUM_SCORES; i++) {
			for (int j = 0; j < 2; j++) {
				scoreArray[i][j] = "";
			}
		}
		this.sf = new File("HighScores.txt");
		FileReader fr;
		try {
			fr = new FileReader(this.sf);
			BufferedReader br = new BufferedReader(fr);
			for (int i = 0; i < NUM_SCORES; i++) {
				String data = br.readLine(), dat[];
				data = data.replaceAll("\\s+", " ");
				dat = data.split(" ");	
				scoreArray[i] = dat;
				//scoreArray[i][1] = data[1];
			}
			fr.close();
			sort();
			System.out.print(printScores());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("I'm broken\n");
			e.printStackTrace();
		}
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
				scoreArray[i] = newScore;
				//scoreArray[i][1] = newScore[1];
			}
		}
		sort();	
		
		try {
			FileWriter fw = new FileWriter(this.sf);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(""); // blanks the HighScores.txt
			bw.write(printScores()); // Refills file with the new list of scores
			bw.newLine();
			fw.close();
		} catch (IOException e) {
			;//e.printStackTrace();
		}
		
	}
	public String[][] getTable() {
		return scoreArray;
	}
	
	public int getNum() {
		return NUM_SCORES;
	}
	
	public String printScores() {
		String list = "";
		for (int i = 0; i < 10; i++) {
			list += scoreArray[i][0] + scoreArray[i][1] + "\n";
		}
		return list;
	}
	
	/**
	 * Sort using bubble sort algorithm
	 */	
	private void sort() {
		int n = NUM_SCORES;
		String[] temp = new String[2];

		while (n != 0) {
			int nn = 0;
			for (int i = 1; i <= n-1; i++) {
				if (Integer.parseInt(scoreArray[i-1][1]) < Integer.parseInt(scoreArray[i][1])) {
					temp = scoreArray[i-1];
					//temp[1] = scoreArray[i-1][1];
					scoreArray[i-1] = scoreArray[i];
					//scoreArray[i-1][1] = scoreArray[i][1];
					scoreArray[i] = temp;
					nn = i;
				}				
			}
			n = nn;
		}
		/*	
		temp [0] = scoreArray[j-1][0];
		temp[1] = scoreArray[j-1][1];
		scoreArray[j-1][0] = scoreArray[j][0];
		scoreArray[j-1][1] = scoreArray[j][1];
		scoreArray[j][0] = temp[0];
		scoreArray[j][1] = temp[1];
		*/		
	}
}