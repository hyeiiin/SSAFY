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

public class Main_15649_정준원 {
	static boolean visited[];
	static int arr[];

	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		visited = new boolean[n];
		arr = new int[m];

		dfs(m, n, 0);

	}

	public static void dfs(int m, int n, int d) {

		if (d == m) {
			for (int c : arr) {
				System.out.print(c + " ");

			}
			System.out.println();
			return;
		}

		for (int i = 0; i < n; i++) {

			if (!visited[i]) {

				visited[i] = true;
				arr[d] = i + 1;

				dfs(m, n, d + 1);

				visited[i] = false;
			}

		}

	}

}
