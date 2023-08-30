package sdf;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1005

class Main_1010_정준원 {
	static int n;
	static int m;
	static int t;
	static int tmp;
	static int result;

	static int[][] dp = new int[30][30];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < T; i++) {

			st = new StringTokenizer(br.readLine());

			// M개중 N개를 뽑는 경우이므로 nCr 에서 n = M, r = N이다.
			int N = Integer.parseInt(st.nextToken()); // N = r
			int M = Integer.parseInt(st.nextToken()); // M = n

			System.out.println(combi(M, N));
			/*
			 * sb.append(combi(M, N)).append('\n');
			 */
		}
		/*
		 * System.out.println(sb);
		 */

	}

	static int combi(int n, int r) {

		// 이미 값을 가지고 있는 경우 바로 반환
		if (dp[n][r] > 0) {
			return dp[n][r];
		}

		// 2번 성질 서쪽 과 동쪽의 수 가 같은 경우... 겹쳐질수 없다 햇으므로...
		if (n == r || r == 0) {
			return 1;
		}

		// 1번 성질
		 
		return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);  // 조합 nCr 을 구하는 것이므로 수학적 공식에 의한 nCr=n-1Cr-1+n-1Cr-1 로 진행.
	}
}
