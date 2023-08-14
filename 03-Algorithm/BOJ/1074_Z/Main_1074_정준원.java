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

public class Main_1074_정준원 {
//	static int arr[][];
	static int cnt = 0;

	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int v = (int) Math.pow(2, n);

//		arr = new int[v + 1][v + 1];
//		System.out.println("in");

		solve(1, 1, v, r + 1, c + 1);

//		for (int i = 0; i <= v; i++) {
////			System.out.println(Arrays.toString(arr[i]));
//		}

//		System.out.println(arr[r + 1][c + 1]);
	}

	static void solve(int r, int c, int d, int or, int oc) {
//		System.out.println("rc" + r + " " + c + " " + d + " " + cnt);
		if (d == 2) {
			cnt++;
			if (r == or && c == oc)
				System.out.println(cnt - 1);
			cnt++;
			if (r == or && c + 1 == oc)
				System.out.println(cnt - 1);
			cnt++;
			if (r + 1 == or && c == oc)
				System.out.println(cnt - 1);
			cnt++;
			if (r + 1 == or && c + 1 == oc)
				System.out.println(cnt - 1);

			return;
		}

		if (or >= r && r + d / 2 > or && oc >= c && oc < c + d / 2) {
//			System.out.println("r c" + r + c);
			solve(r, c, d / 2, or, oc);
		} else
			cnt += (d / 2) * (d / 2);

		if (or >= r && r + d / 2 > or && oc >= c + d / 2 && oc < c + d) {
//			System.out.println("r c + d / 2" + r + " " + (c + d / 2));

			solve(r, c + d / 2, d / 2, or, oc);
		} else
			cnt += (d / 2) * (d / 2);
		if (or >= r + d / 2 && r + d > or && oc >= c && oc < c + d / 2) {
//			System.out.println("r + d / 2  c  " + (r + d / 2) + " " + (c));

			solve(r + d / 2, c, d / 2, or, oc);
		} else
			cnt += (d / 2) * (d / 2);
		if (or >= r + d / 2 && r + d > or && oc >= c + d / 2 && oc < c + d) {
//			System.out.println("r + d / 2      c + d / 2  " + (r + d / 2) + " " + (d / 2 + c));

			solve(r + d / 2, c + d / 2, d / 2, or, oc);
		} else
			cnt += (d / 2) * (d / 2);
	}

}
