package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * https://www.acmicpc.net/problem/1600
 * 
 * @author SSAFY
 *
 */
public class Main_1600_박정인 {
	static class Position {
		int x, y, cnt, horseCnt;

		public Position(int x, int y, int cnt, int horseCnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.horseCnt = horseCnt;
		}
	}
	static int K, W, H, map[][], min;

	static int[] monkeyDx = { -1, 0, 1, 0 };
	static int[] monkeyDy = { 0, -1, 0, 1 };
	static int[] horseDx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] horseDy = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static final int BLANK = 0;
	static final int WALL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] =  Integer.parseInt(st.nextToken());				
			}
		}

		bfs(0, 0);

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void bfs(int x, int y) {
		int total = 0, horseCnt = 0;

		Queue<Position> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[K + 1][H][W];
		q.offer(new Position(x, y, 0, K));	// x, y, 전체이동 횟수, 말 이동횟수
		visited[K][x][y] = true;

		while (!q.isEmpty()) {
			Position now = q.poll();
			
			if (now.x == H - 1 && now.y == W - 1) {
				min = Math.min(min, now.cnt);
				break;
			}
			
			// 원숭이 이동
			for (int i = 0; i < 4; i++) {
				int nx = now.x + monkeyDx[i];
				int ny = now.y + monkeyDy[i];
				
				if (!isRange(nx, ny) || map[nx][ny] == WALL)	continue;
				
				if (visited[now.horseCnt][nx][ny])	continue;
				
				visited[now.horseCnt][nx][ny] = true;
				q.offer(new Position(nx, ny, now.cnt + 1, now.horseCnt));
			}
			
			// 말 이동
			if (now.horseCnt > 0) {
				for (int i = 0; i < 8; i++) {
					int nx = now.x + horseDx[i];
					int ny = now.y + horseDy[i];
					
					if (!isRange(nx, ny) || map[nx][ny] == WALL)	continue;
					
					if (visited[now.horseCnt - 1][nx][ny])	continue;
					
					visited[now.horseCnt - 1][nx][ny] = true;
					q.offer(new Position(nx, ny, now.cnt + 1, now.horseCnt - 1));
				}
			}
			
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}
}