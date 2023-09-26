package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_3307_정준원 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int test = 1; test <= t; test++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 큐 생성
			sb.append("#" + test + " ");
			sb.append(solve(n, arr));
			sb.append("\n");

		}

		System.out.println(sb.toString());

	}

	public static int solve(int n, int[] arr) {
		int[] dp = new int[n];
		dp[0] = 1;
		int res = 0;

		for (int i = 1; i < n; i++) {
			dp[i] = 1;

			for (int j = 0; j < i; j++) {

				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			res = Math.max(res, dp[i]);
//			System.out.println("dp" + dp[i]);
		}

		return res;
	}

}