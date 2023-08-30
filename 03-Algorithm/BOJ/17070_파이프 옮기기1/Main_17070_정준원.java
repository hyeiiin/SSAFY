package sdf;

import java.util.*;
import java.io.*;

class Main_17070_정준원 {
	static int n;
	static int m;
	static int result = 0;

	static int[][] dp; // 가.세.대
	static int N;
	static int[][] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //
		arr = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(1, 2, 0); // 가로
		System.out.println(result);

	}

// 0 0 0 0
//	0 0 0 0
//	0 0 0 0
//	0 0 0 0

// 		0 1 1 1
//		0 0 1 1+1
//		0 0 1 1+2
//		0 0 1 1+1+1

	static void solve(int r, int c, int dir) {

//		if (dir == 2) { // 대각선.
		// System.out.println("r c" + r + " " + c + "dir " + dir);

		if (r == N && c == N) {
			result++;
			return;
		}

		if (r > N || c > N) {
			return;
		}

		if (dir == 0) { // 가로
			if (r + 1 <= N && c + 1 <= N && arr[r + 1][c + 1] == 0 && arr[r][c + 1] == 0 && arr[r + 1][c] == 0) { // 대각선
				solve(r + 1, c + 1, 2);
			}

			if (r <= N && c + 1 <= N && arr[r][c + 1] == 0) { // 가로
				solve(r, c + 1, 0);
			}

		}

		else if (dir == 1) { // 세로
			if (r + 1 <= N && c <= N && arr[r + 1][c] == 0) { // 세로
				solve(r + 1, c, 1);
			}

			if (r + 1 <= N && c + 1 <= N && arr[r + 1][c + 1] == 0 && arr[r + 1][c] == 0 && arr[r][c + 1] == 0) { // 대각선
				solve(r + 1, c + 1, 2);
			}
		}

		else { // 대각선
				// System.out.println("dir2");
			if (r + 1 <= N && c + 1 <= N && arr[r + 1][c + 1] == 0 && arr[r][c + 1] == 0 && arr[r + 1][c] == 0) { // 대각선
				solve(r + 1, c + 1, 2);
			}
			if (r <= N && c + 1 <= N && arr[r][c + 1] == 0) { // 가로

				solve(r, c + 1, 0);
			}
			if (r + 1 <= N && c <= N && arr[r + 1][c] != 1) { // 세로
				solve(r + 1, c, 1);
			}
		}

	}
}
