package study.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/9205
 */
public class Main_9205_박정인 {
	static int T, N, arr[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N + 2][2];

			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}

			boolean[][] isMoveable = new boolean[N + 2][N + 2];

			// 단순히 옆으로만 이동가능 여부 반영 >> 거쳐가는 경우 반영x
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					int dist = Math.abs(arr[i][0] - arr[j][0]) + Math.abs(arr[i][1] - arr[j][1]);

					if (dist <= 1000) {
						isMoveable[i][j] = true;
					}
				}
			}

			// 거쳐가는 경우 반영
			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					for (int j = 0; j < N + 2; j++) {
						if (isMoveable[i][k] && isMoveable[k][j]) {
							isMoveable[i][j] = true;
						}
					}
				}
			}

			if (isMoveable[0][N + 1]) {
				sb.append("happy");
			} else {
				sb.append("sad");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
