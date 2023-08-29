package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 연습문제1_서지원 {

	static int N;
	static int[] dp;
	
	private static int dp(int n) {
		dp = new int[N + 1];
		dp[1] = 2; dp[2] = 3;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		System.out.println(dp(N));
	}

}
