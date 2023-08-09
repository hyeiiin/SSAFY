import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {  // BOJ_11660
	static int M, N;
	static int x1, x2, y1, y2;
	static StringBuilder sb;
	static int[][] DP;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 4
		M = Integer.parseInt(st.nextToken());

		DP = new int[N + 1][N + 1];

		/**
		 * DP[] = 누적합 배열 [x][y]의 값 = ([x][y] 위치 기준 이전 가로 & 세로 누적합 - 겹치는 부분) + 입력받은 숫자
		 */
		for (int x = 1; x <= N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 1; y <= N; y++) {
				DP[x][y] = DP[x - 1][y] + DP[x][y - 1] - DP[x - 1][y - 1] + Integer.parseInt(st.nextToken());
			}
		}

		// 임시 출력
//		System.out.println();
//		for (int i = 0; i < N + 1; i++) {
//			for (int j = 0; j < N + 1; j++) {
//				System.out.printf("%3d", DP[i][j]);
//			}
//			System.out.println();
//		}

		// M개의 구해야할 누적값 범위
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());

			sb = new StringBuilder();

			/*
			 * 구하려는 범위의 최대 크기 - 외곽 2군데의 합 + 외각 2군데의 중복된 부분
			 */
//			System.out.println(DP[x2][y2] + ", " + DP[x1 - 1][y2] + ", " + DP[x2][y1 - 1] + ", " + DP[x1 - 1][y1 - 1]);
			sb.append(DP[x2][y2] - DP[x1 - 1][y2] - DP[x2][y1 - 1] + DP[x1 - 1][y1 - 1]);
			System.out.println(sb.toString());
		}

	} // Main

}
