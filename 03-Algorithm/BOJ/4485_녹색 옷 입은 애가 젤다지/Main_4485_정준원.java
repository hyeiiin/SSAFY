package sdf;

import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Main_4485_정준원 {

	static int[][] dp;
	static int[][] arr;
 	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cnt = 0;

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			dp = new int[n][n];
			arr = new int[n][n];

			if (n == 0)
				break;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					dp[i][j] = Integer.MAX_VALUE;
				}
			}

			dp[0][0] = arr[0][0];
 
			int res = solve(n, dp);

			cnt++;
			System.out.println("Problem " + cnt + ": " + res);
		}

	}

	static class point {
		int x, y;

		public point(int x, int y) {

			this.x = x;
			this.y = y;
		}

	}

	static int solve(int n, int[][] dp) {

		Queue<point> q = new LinkedList<>();

		q.add(new point(0, 0));

		while (!q.isEmpty()) {

			point p = q.poll();

			int x = p.x;
			int y = p.y;

			for (int i = 0; i < 4; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;

//				if (dp[nx][ny] <= dp[x][y] + arr[nx][ny])
//					continue;

				if (dp[nx][ny] > dp[x][y] + arr[nx][ny]) {
					dp[nx][ny] = dp[x][y] + arr[nx][ny];

					q.add(new point(nx, ny));
				}

			}

		}

		return dp[n - 1][n - 1];
	}

}