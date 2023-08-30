package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_서지원 {

	static int T, N, M;
	static int[][] dp = new int[30][30];
	
	private static int nCr(int n, int r) {
		if (dp[n][r] != 0) return dp[n][r];
		if (r == 0 || n == r) return dp[n][r] = 1;
		else return dp[n][r] = nCr(n - 1, r - 1) + nCr(n -1, r);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sb.append(nCr(M, N)).append("\n");
		}
		System.out.println(sb);
	}

}
