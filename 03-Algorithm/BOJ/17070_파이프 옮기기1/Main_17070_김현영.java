import java.io.*;
import java.util.*;

public class Main_17070_김현영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		// 지도. 1부터 시작
		int[][] map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 파이프의 끝위치 저장. 1부터 시작
		// 행번호, 열번호, 파이프상태 - 0:가로 1:대각선 2:세로
		int[][][] dp = new int[n + 1][n + 1][3];
		dp[1][2][0] = 1; // 초기상태
		 

		// 현재 위치에 파이프의 끝이 오도록 가로,대각선, 세로 방향으로 놓을 때 이전 파이프의 모양을 보고 판단해야함
		// 가로의 경우 이전에 올 수 있는 파이프의 모양은 가로, 대각선
		// 세로의 경우 이전에 올 수 있는 파이프의 모양은 세로, 대각선
		// 대각선의 경우 이전에 올 수 있는 파이프의 모양은 가로, 세로, 대각선
		for (int i = 1; i <= n; i++) {
			// 파이프는 3열부터 놓을 수 있음
			for (int j = 3; j <= n; j++) {
				// 현재 위치가 벽이면 패스
				if (map[i][j] == 1)
					continue;

				// 1행의 경우 가로 파이프밖에 올 수 없음
				if(i == 1)
					dp[i][j][0] = dp[i][j-1][0];
				
				// 현재 위치가 빈칸이라면, 가로, 세로 모양 파이프를 놓을 수 있음
				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1]; // 가로 파이프
				dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2]; // 세로 파이프

				// 대각선 파이프
				// 현재 위치와 현재 위치 기준 위, 왼쪽이 빈 칸이라면 대각선 파이프를 놓을 수 있음
				if (map[i - 1][j] == 0 && map[i][j - 1] == 0)
					dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
			}
		}

//		System.out.println("가로 dp ");
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= n; j++) {
//				System.out.printf("%4d ", dp[i][j][0]);
//			}
//			System.out.println();
//		}
//
//		System.out.println("대각선 dp ");
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= n; j++) {
//				System.out.printf("%4d ", dp[i][j][1]);
//			}
//			System.out.println();
//		}
//
//		System.out.println("세로 dp ");
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= n; j++) {
//				System.out.printf("%4d ", dp[i][j][2]);
//			}
//			System.out.println();
//		}

		int result = dp[n][n][0] + dp[n][n][1] + dp[n][n][2];
		System.out.println(result);

	}
}
