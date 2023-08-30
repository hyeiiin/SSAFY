package todo.lesson._0830;

import java.io.*;
import java.util.*;

public class Main_17070_최지웅 {

	static int N;

	static final int H = 0; // Horizontal
	static final int V = 1; // Vertical
	static final int D = 2; // Diagonal
	static final int CASE_DIR = 3;

	static int[][] home;
	static final int EMPTY = 0;
	static final int WALL = 1;

	static int[][][] memo;
	
	static boolean check(int r, int c) {
		return home[r][c] == EMPTY && home[r - 1][c] == EMPTY && home[r][c - 1] == EMPTY;
	}
	
	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		home = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				home[r][c] = in.nextInt();
			}
		}
		
		memo = new int[N + 1][N + 1][CASE_DIR];
		
		memo[1][2][H] = 1;
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (r == 1 && c == 1) continue;
				if (r == 1 && c == 2) continue;
				
				if (home[r][c] != WALL) {
					memo[r][c][H] = memo[r][c - 1][H] + memo[r][c - 1][D]; 					
					memo[r][c][V] = memo[r - 1][c][V] + memo[r - 1][c][D];
					
					if (check(r, c)) {
						memo[r][c][D] = memo[r - 1][c - 1][H] + memo[r - 1][c - 1][V] + memo[r - 1][c - 1][D];
 					}
				}
			}
		}

		System.out.println(memo[N][N][H] + memo[N][N][V] + memo[N][N][D]);

		//
		in.close();
	}

}
