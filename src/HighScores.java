import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
		
		try {
			BufferedReader br = openFileReader("../media/HighScores.txt");
			for (int i = 0; i < NUM_SCORES; i++) {
				String[] data = br.readLine().split(" ");
				scoreArray[i] = data;
				//scoreArray[i][1] = data[1];
			}
			br.close();
			sort();
//			System.out.print(printScores());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("I'm broken\n");
			e.printStackTrace();
		}
	}
	
	/* convenience method to open a file for reading, throws exception if the file is not found */
	private static BufferedReader openFileReader(String filename) {
		BufferedReader rd = null;
//		while (rd == null) {
			try {
				rd = new BufferedReader(new FileReader(filename));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
//		}
		return rd;
	}
	
	private static BufferedWriter openFileWriter (String filename) {
		BufferedWriter wr = null;
//		while (wr == null) {
			try {
				wr = new BufferedWriter(new FileWriter(filename));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
//		}
		return wr;
	}
	
	/**
	 * Generates a score-holding array for addScore()
	 */
	private String[] submitScore(String name, int scoreval) {
		if (name == "") {
			name = "UNK";
		}
		name = name.toUpperCase();
		String[] score = new String[2];
		score[0] = name;
		score[1] = Integer.toString(scoreval);
		return score;
	}
	
	private String[][] shift(int x, String[] nS) {
		String[][] newArray = new String[NUM_SCORES][2];
		for (int i = 0; i < NUM_SCORES; i++) {
			newArray[i] = scoreArray[i];
		}
		newArray[x] = nS;
		for (int i = x+1; i < NUM_SCORES; i++) {
			newArray[i] = scoreArray[i-1];
		}
		return newArray;	
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
				continue;
			}
			else {
				scoreArray = shift(i, newScore);
				//scoreArray[i][1] = newScore[1];
				break; // TODO do we need to do more here?
			}
		}
		sort();	
		
		try {
			BufferedWriter bw = openFileWriter("../media/HighScores.txt");
			bw.write(printScores()); // Refills file with the new list of scores
			bw.flush(); //forces it to write out
			bw.close();
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
			if (scoreArray[i][0].length() > 3) {
				scoreArray[i][0] = scoreArray[i][0].substring(0, 3);
			}
			if (Integer.parseInt(scoreArray[i][1]) > 99999) {
				scoreArray[i][1] = "99999";
			}
			if (scoreArray[i][1].length() < 5) {
				while (scoreArray[i][1].length() != 5) {
					scoreArray[i][1] = "0".concat(scoreArray[i][1]);
				}
			}
			list += scoreArray[i][0] + " " + scoreArray[i][1] + "\n";
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