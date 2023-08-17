package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_박정인 {
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();

			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int cnt = 0;

		for (int i = 0; i < R; i++) {
			if (dfs(i, 0)) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	private static boolean dfs(int x, int y) {
		if (y == C - 1) {
			return true;
		}

		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!isRange(nx, ny))	continue;
			
			if (visited[nx][ny] || map[nx][ny] == 'x')	continue;
			
			visited[nx][ny] = true;
			if (dfs(nx, ny)) {
				return true;
			}
		}
				
		return false;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
}
