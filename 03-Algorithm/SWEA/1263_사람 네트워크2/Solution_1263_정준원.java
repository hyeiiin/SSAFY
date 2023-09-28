 //

import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
//
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
//					System.out.print("Map" + map[i][j]);
					if (i != j && map[i][j] == 0) {
						map[i][j] = 999999;
					}
				}
//				System.out.println();
			}

			int v = solve();
			System.out.println("#" + tc + " " + v);
		}

	}

	static int solve() {
		int rowsize = map.length;
		int colsize = map[0].length;

		int[][] dp = new int[rowsize][colsize];

		for (int k = 0; k < rowsize; k++) {
			for (int i = 0; i < colsize; i++) {
				dp[k][i] = Integer.MAX_VALUE;

			}
		}

		for (int k = 0; k < rowsize; k++) {
			for (int i = 0; i < colsize; i++) {
				dp[k][i] = map[k][i];

			}
		}

		for (int k = 0; k < map.length; k++) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
				}
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
//				System.out.print(dp[i][j]);
			}
//			System.out.println();
		}

		int res = Integer.MAX_VALUE;

		for (int i = 0; i < map.length; i++) {
			int sum = 0;

			for (int j = 0; j < map.length; j++) {
				sum += dp[i][j];
			}
//			System.out.println("sum" + " " + i + " " + " " + sum);
			res = Math.min(sum, res);
		}

		return res;
	}

}
