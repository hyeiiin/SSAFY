package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 연습문제2_박정인 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];
		dp[1] = 2;
		dp[2] = 5;
		
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i - 2] + 2 * dp[i - 1];
		}
		
		System.out.println(dp[N]);
	}
}
