import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1263_이도훈 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());


			int[][] dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(i==j){
						dist[i][j] = 0;
					}

					int num = Integer.parseInt(st.nextToken());

					if (num == 1) {
						dist[i][j] = 1;
					}
				}
			}

			// 플로이드 워셜 실행
			for (int m = 0; m < N; m++) {
				for (int s = 0; s < N; s++) {
					for (int e = 0; e < N; e++) {
						if (dist[s][m] != Integer.MAX_VALUE && dist[m][e] != Integer.MAX_VALUE) {
							dist[s][e] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
						}
					}
				}
			}

			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {

				int sum = 0;

				for (int j = 0; j < N; j++) {
					sum += dist[i][j];
				}

				min = Math.min(min, sum);

			}

			sb.append("#").append(test_case).append(" ").append(min).append("\n");

		}
		System.out.println(sb);
	}
}
