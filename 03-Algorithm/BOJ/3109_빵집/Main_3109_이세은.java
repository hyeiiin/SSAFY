package ssafyPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_이세은 {

	private static char[][] arr;
	private static int[] moveR = { -1, 0, 1 };
	private static int[] moveC = { 1, 1, 1 };
	private static boolean[][] visited;
	private static int r, c, cnt = 0;
	private static boolean found;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new char[r][c];
		visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		// 첫번째 열에서 출발
		for (int row = 0; row < r; row++) {
			if (!visited[row][0]) {
				found = false;
				dfs(row, 0);
			}
		}

		System.out.println(cnt);

	}

	public static void dfs(int i, int j) {

		visited[i][j] = true;
		
		if (j == c - 1) { //빵집과 만났을 경우 종료
			found = true;
			cnt++;
			return;
		}

		for (int k = 0; k < 3; k++) {
			int newR = i + moveR[k];
			int newC = j + moveC[k];

			if (newR >= 0 && newC >= 0 && newR < r && newC < c && !found) {
				if (!visited[newR][newC] && arr[newR][newC] != 'x') {
					visited[newR][newC] = true;
					dfs(newR, newC);
				}
			}
		}

	}
}