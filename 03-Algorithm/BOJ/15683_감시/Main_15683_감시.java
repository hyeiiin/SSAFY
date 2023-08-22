package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15683_감시 {
	static class Cctv {
		int x, y, index;

		public Cctv(int x, int y, int index) {
			super();
			this.x = x;
			this.y = y;
			this.index = index;
		}
	}

	static int[][] cctvDir = { {}, { 3 }, { 1, 3 }, { 0, 3 }, { 0, 1, 3 }, { 0, 1, 2, 3 } };

	static int N, M, map[][], min = Integer.MAX_VALUE;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	
	static List<Cctv> list; // CCTV 위치
	static int[] output;

	static final int BLANK = 0;
	static final int WALL = 6;
	static final int CCTV_START = 1;
	static final int CCTV_END = 5;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] >= CCTV_START && map[i][j] <= CCTV_END) { // CCTV인 경우
					list.add(new Cctv(i, j, map[i][j]));
				}
			}
		}
		output = new int[list.size()];

		dfs(0);
		System.out.println(min);
	}

	// 중복순열 >> CCTV가 같은 방향일 경우도 포함
	private static void dfs(int cnt) {
		if (cnt == list.size()) {
			// CCTV가 보는 방향 방문처리
			// 사각지대 계산
			// 최소값 갱신
			int area = spread(output);
			min = Math.min(min, area);

			return;
		}

		// 회전수 0 ~ 3 >> 0도, 90도 180도, 270도 
		for (int i = 0; i < 4; i++) {
			output[cnt] = i;
			dfs(cnt + 1);
		}
	}
	
	private static int spread(int[] rotateArr) {	// 각 CCTV마다 회전 횟수가 담긴 배열

		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < rotateArr.length; i++) {
			int r = rotateArr[i]; // 90도 회전수
			Cctv p = list.get(i); // CCTV

			int[] arr = cctvDir[p.index];

			for (int d : arr) {				
				d = rotate(d, r);
				
				int nx = p.x;
				int ny = p.y;
				while (true) {
					nx += dx[d];
					ny += dy[d];

					if (!isRange(nx, ny) || map[nx][ny] == WALL)
						break;

					visited[nx][ny] = true;
				}
			}
		}

		return countBlindSpot(visited);
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	// 회전수 만큼 90도 회전
	private static int rotate(int d, int rotateCnt) {

		return (d + 3 * rotateCnt) % 4;
	}

	// 사각지대 계산
	private static int countBlindSpot(boolean[][] visited) {
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 방문 안했고 빈칸인 경우 반영
				if (!visited[i][j] && map[i][j] == BLANK) {
					total++;
				}
			}
		}

		return total;
	}
}
