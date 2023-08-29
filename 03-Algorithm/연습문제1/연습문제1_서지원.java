package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 연습문제1_서지원 {

	static int N;
	static int[] dp;
	
	private static int dp(int n) {
		if (n <= 2) dp[n] = n + 1;
		else dp[n] = dp(n - 1) + dp(n - 2);
		return dp[n];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		System.out.println(dp(N));
	}

}
