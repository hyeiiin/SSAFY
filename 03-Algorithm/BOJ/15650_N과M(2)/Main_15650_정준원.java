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

public class Main_15650_정준원 {
	static boolean visited[];
	static int arr[];

	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		visited = new boolean[n];
		arr = new int[m];

		dfs(m, n, 0, 1);

	}

	public static void dfs(int m, int n, int d, int s) {

		if (d == m) {
			for (int c : arr) {
				System.out.print(c + " ");
			}

			System.out.println();
			return;
		}

		for (int i = s; i <= n; i++) {
			arr[d] = i;
			dfs(m, n, d + 1, i + 1);
		}

	}

}
