package todo.lesson._0824;

import java.util.*;

public class Main_17281_최지웅 {
	
	/* input */
	static int N;
	static int[][] record;
	
	/* code */
	static final int NUM_PLAYER = 9;

	static final int OUT = 0;
	static final int HIT = 1;
	static final int DOUBLE = 2;
	static final int TRIPLE = 3;
	static final int HOMERUN = 4;
	
	static final int NUM_BASE = 4;
	
	static final int HOME = 0;
	static final int FIRST = 1;
	static final int SECOND = 2;
	static final int THIRD = 3;
	
	/* status */
	static boolean[] selected = new boolean[NUM_PLAYER + 1];
	static int[] batOrder = new int[NUM_PLAYER + 1];
	
	//
	static boolean[] bases = new boolean[NUM_BASE];
	
	static int outCount;
	static int curOrder;
	
	static int score;
	static int maxScore;

	/* simulation */
	static void hit() {
		if (bases[THIRD]) {
			score++;
			bases[THIRD] = false;
		}
		if (bases[SECOND]) {
			bases[THIRD] = bases[SECOND];
			bases[SECOND] = false;
		}
		if (bases[FIRST]) {
			bases[SECOND] = bases[FIRST];
		}
		bases[FIRST] = true; 
	}
	
	static void doubles() {
		if (bases[THIRD]) {
			score++;
			bases[THIRD] = false;
		}
		if (bases[SECOND]) {
			score++;
			bases[SECOND] = false;
		}
		if (bases[FIRST]) {
			bases[THIRD] = bases[FIRST];
			bases[FIRST] = false;
		}
		bases[SECOND] = true;
	}
	
	static void triple() {
		if (bases[THIRD]) {
			score++;
			bases[THIRD] = false;
		}
		if (bases[SECOND]) {
			score++;
			bases[SECOND] = false;
		}
		if (bases[FIRST]) {
			score++;
			bases[FIRST] = false;
		}
		bases[THIRD] = true;
	}
	
	static void homerun() {
		for (int id = FIRST; id <= THIRD; id++) {
			if (bases[id]) {
				score++;
				bases[id] = false;
			}
		}
		score++;
	}
	
	//
	static void bat(int swing) {
		if (swing == OUT) outCount++;
		else if (swing == HIT) hit();
		else if (swing == DOUBLE) doubles();
		else if (swing == TRIPLE) triple();
		else if (swing == HOMERUN) homerun();
		
		if (curOrder == 9) curOrder = 1;
		else curOrder++;
	}
	
	static void playGame() {
		
		Arrays.fill(bases, false);
		score = 0;
		curOrder = 1;
		
		for (int n = 1; n <= N; n++) {
			Arrays.fill(bases, false);
			outCount = 0;
			while (outCount < 3) {
				bat(record[n][batOrder[curOrder]]);
			}
		}
		
		maxScore = Math.max(maxScore, score);
	}
	
	static void permutation(int count) {
		if (count == 4) {
			permutation(count + 1);
			return;
		}		
		if (count > NUM_PLAYER) {
			playGame();
			return;
		} else {
			for (int i = 1; i <= NUM_PLAYER; i++) {
				if (selected[i]) continue;
				selected[i] = true;
				batOrder[count] = i;
				permutation(count + 1);
				selected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {

		/* input */
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		
		record = new int[N + 1][NUM_PLAYER + 1];
		
		for (int n = 1; n <= N; n++) {
			for (int i = 1; i <= NUM_PLAYER; i++) {
				record[n][i] = in.nextInt();
			}
		}
		
		selected[1] = true;
		batOrder[4] = 1;
		
		maxScore = Integer.MIN_VALUE;
		permutation(1);
		
		System.out.println(maxScore);
		
		//
		in.close();
	}

}
