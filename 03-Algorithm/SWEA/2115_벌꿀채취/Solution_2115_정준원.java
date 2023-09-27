package sdf;

import java.util.*;
import java.io.*;

public class Solution_2115_정준원 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, C;
	static int[][] map;
	static boolean[][] visited;
	static int maxnum = 0;

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("#" + tc + " " + solve());
		}
	}

	private static int solve() {
		int result = 0;
		int max1 = 0;
		int max2 = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				maxnum = 0;
				maxinMsize(i, j, 0, 0, 0);
				max1 = maxnum;

				maxnum = 0;
				max2 = 0;

				for (int j2 = j + M; j2 <= N - M; j2++) {
					maxinMsize(i, j2, 0, 0, 0);
					max2 = Math.max(max2, maxnum);
				}

				for (int i2 = i + 1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N - M; j2++) {
						maxinMsize(i2, j2, 0, 0, 0);
						max2 = Math.max(max2, maxnum);
					}
				}

				result = Math.max(result, max1 + max2);
			}
		}

		return result;
	}

	private static void maxinMsize(int i, int j, int cnt, int sum, int powsum) {
		if (sum > C)
			return;

		if (cnt == M) {
			if (maxnum < powsum)
				maxnum = powsum;
			return;
		}

		maxinMsize(i, j + 1, cnt + 1, sum + map[i][j], powsum + map[i][j] * map[i][j]);
		maxinMsize(i, j + 1, cnt + 1, sum, powsum);
	}
}
