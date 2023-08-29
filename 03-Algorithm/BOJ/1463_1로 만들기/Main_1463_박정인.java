package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1463_박정인 {
	static int N, dp[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		dp = new int[1000001];

		dp[1] = 0;	// 1에서 1만드는 경우
		dp[2] = 1;	// 2에서 1만드는 경우 >> /2 로 1번
		dp[3] = 1;	// 3에서 1만드는 경우 >> /3으로 1번
		for (int i = 4; i <= N; i++) {	// i에서 1만드는데 필요한 연산 횟수
			// -1의 경우는 무조건 이전거 도달 가능 >> dp[i] = dp[i-1] + 1은 보장이 된다.
			// 이후 3, 2로 나누어 떨어지는 경우 -1의 경우와 비교하면 된다. 
			dp[i] = dp[i - 1] + 1;	
			
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
			}
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
			}
		}
		System.out.println(dp[N]);
	}
}
