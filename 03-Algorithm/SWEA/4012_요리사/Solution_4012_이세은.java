package ssafyPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_이세은 {

	private static int[][] arr;
	private static boolean[] visited;
	private static int min;
	private static int n;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			n = Integer.parseInt(br.readLine()); // 식재료 수

			arr = new int[n][n];
			visited = new boolean[n];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			combination(0, 0);
			System.out.println("#" + test_case + " " + min);

		}

	}

	private static void combination(int cnt, int idx) {

		if (cnt == n / 2) { // 전체 행렬의 절반 탐색했을 경우
			// 방문한 절반 음식과 방문하지 않은 나머지 음식들 구하기
			int sum = 0, sum2 = 0; // 각각 식재료로 구성한 음식 더해서(시너지) 차이 구하기
			for (int i = 0; i < n-1; i++) {
				for (int j = i + 1; j < n; j++) {
					if (visited[i] && visited[j]) // 방문한 식재료 음식
						sum += arr[i][j] + arr[j][i]; // 시너지
					else if(!visited[i] && !visited[j]) { // 두 식재료 모두 방문하지 않은 식재료 음식
						sum2 += arr[i][j] + arr[j][i]; // 시너지
					}
				}
			}
			int sim = Math.abs(sum - sum2);
			min = Math.min(min, sim); // 맛의 차이 최솟값 구하기
			return;

		}

		// 각 식재료 조합
		for (int i = idx; i < n; i++) {
			visited[i] = true;
			combination(cnt + 1, i + 1);
			visited[i] = false;
		}
	}
}