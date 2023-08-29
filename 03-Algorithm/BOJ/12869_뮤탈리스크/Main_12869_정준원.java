package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_12869_정준원 {

	static int[] arr;
	static int[][] weapon = { { 9, 3, 1 }, { 9, 1, 3 }, { 3, 9, 1 }, { 3, 1, 9 }, { 1, 3, 9 }, { 1, 9, 3 } };
	static Integer[] dp;
	static int n;
	static int max = -Integer.MAX_VALUE;
	static int res = Integer.MAX_VALUE;

	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		arr = new int[3];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i : arr)
		//	System.out.println(i);

		dp = new Integer[n + 1];
		dp[1] = 0;

		int[][][] visit = new int[61][61][61];

		for (int i = 0; i < 61; i++) {
			for (int j = 0; j < 61; j++) {
				for (int k = 0; k < 61; k++) {
					visit[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}

		visit[arr[0]][arr[1]][arr[2]] = 1;

		solve(0, visit, arr[0], arr[1], arr[2]);
		System.out.print(res);
	}

	static void solve(int depth, int[][][] visit, int a, int b, int c) {

		if (isfinish(a, b, c)) {
			res = Math.min(res, depth);
			return;
		}

		for (int i = 0; i < 6; i++) {
			int na = a - weapon[i][0];
			int nb = b - weapon[i][1];
			int nc = c - weapon[i][2];

			if (na < 0)
				na = 0;

			if (nb < 0)
				nb = 0;

			if (nc < 0)
				nc = 0;
//
//			if (na <= 0 && nb <= 0 && nc <= 0)
//				solve(depth + 1, visit, na, nb, nc);
//			System.out.println("na .." + na);
//			System.out.println("nb .." + nb);
//			System.out.println("nc .." + nc);
			if (visit[na][nb][nc] > depth + 1) {
				visit[na][nb][nc] = depth + 1;
				solve(depth + 1, visit, na, nb, nc);
			}

		}

	}

	static boolean isfinish(int a, int b, int c) {
		boolean flag;

		if (a > 0) {
			return false;
		}
		if (b > 0) {
			return false;
		}
		if (c > 0) {
			return false;
		}

		return true;
	}

}