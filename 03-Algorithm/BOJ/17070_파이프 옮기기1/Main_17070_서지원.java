package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이썬은 시간초과
public class Main_17070_서지원 {
	
	static int N, result;
	static int[][] map;
	// 0 : 가로, 1 : 세로, 2 : 대각선
	static int[] dx = {0, 1, 1}, dy = {1, 0, 1};

	// x, y : 파이프의 오른쪽 끝 좌표, d : 현재 파이프 방향
	private static void dfs(int x, int y, int d) {
		// 파이프의 한쪽 끝이 (N - 1, N - 1)에 도착
		if (x == N - 1 && y == N - 1) {
			result++;
			return;
		}
		
		// 가로, 세로, 대각선 이동
		for (int i = 0; i < 3; i++) {
			// 이동하는 방향이 가로인 경우 세로 제외, 이동하는 방향이 세로인 경우 가로 제외
			if ((d == 0 && i == 1) || (d == 1 && i == 0)) continue;
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!isMove(nx, ny, i)) continue; // 이동할 수 없다면 continue
			dfs(nx, ny, i); // 파이프 이동
		}
	}
	
	private static boolean isMove(int x, int y, int d) {
		// 범위 벗어나면 false
		if (x < 0 || x >= N || y < 0 || y >= N) return false;
		// 파이프가 이동할 방향이 가로 혹은 세로일 때, 이동하는 위치에 벽이 있다면 false
		if ((d == 0 || d == 1) && map[x][y] != 0) return false;
		// 파이프가 이동할 방향이 대각선일 때, 이동 전 좌표에서 가로, 세로, 대각선 중 벽이 있다면 false
		if (d == 2) {
			int nx = x - dx[d], ny = y - dy[d];
			if (nx + 1 >= N || ny + 1 >= N) return false;
			if (map[nx + 1][ny] != 0 || map[nx][ny + 1] != 0 || map[nx + 1][ny + 1] != 0) return false;		
		}
		// 위에 해당 안되면 움직일 수 있으므로 true
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = 0;
		dfs(0, 1, 0);
		System.out.println(result);
	}

}
