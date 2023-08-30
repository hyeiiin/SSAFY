package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1010
 * 
 * @author SSAFY
 *
 */
public class Main_1010_박정인 {
	static int N, M, dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 다리 놓는 형태를 보면 combination 에서 i = start 로 하나 놓고 그 이후부터 놓을 수 있는 것과 형태가 같다 
			// 조합 사용
			// mCn
			
			dp = new int[M + 1][N + 1];
			
			for (int i = 0; i <= M; i++) {
				for (int j = 0, end = Math.min(i, N); j <= end; j++) {
					if (j == 0 || i == j)	dp[i][j] = 1;
					else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				}
			}
			
			sb.append(dp[M][N]).append("\n");
		}

		System.out.println(sb);
	}
}
