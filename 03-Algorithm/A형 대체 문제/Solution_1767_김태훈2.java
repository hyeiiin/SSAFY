package swea;

import java.util.*;
import java.io.*;

public class Solution_1767_김태훈2 {

	static class point {
		int x, y;

		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int N, map[][], min, size;
	static point[] core;
	static boolean[] visit;

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[12];
		core = new point[12];
		size = 0;
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			String in = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx += 2) {
				map[i][j] = in.charAt(idx) - '0';
				if (map[i][j] == 1 && i > 0 && i < N - 1 && j > 0 && j < N - 1) {
					core[size++] = new point(i, j);
				}
			}
		}
	}

	static void solve() {
		for (int i = size; i >= 0; i--) {
			combi(0, 0, i);
			if (min < Integer.MAX_VALUE)
				break;
		}
		sb.append(min);
	}

	static void combi(int idx, int start, int n) {
		if (idx == n) {
			dfs(0, 0);
		}
		for (int i = start; i < size; i++) {
			visit[idx] = true;
			combi(idx + 1, i + 1, n);
			visit[idx] = false;
		}
	}

	static void dfs(int idx, int sum) {
		// 종료 조건
		if (idx == size) {
			min = Math.min(min, sum);
			return;
		}
		if (!visit[idx]) {
			dfs(idx + 1, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int x = core[idx].x;
			int y = core[idx].y;
			int temp = 0;
			boolean poss = false;
			while (true) {
				x += dx[i];
				y += dy[i];
				// 끝에 도달 했으면
				if (x < 0 || x >= N || y < 0 || y >= N) {
					dfs(idx + 1, sum + temp);
					break;
				}
				// 전선이나 코어에 막혀있으면
				if (map[x][y] != 0) {
					break;
				}
				map[x][y] = 2;
				temp++;
			}
			while (true) {
				x -= dx[i];
				y -= dy[i];
				if (core[idx].x == x && core[idx].y == y) {
					break;
				}
				map[x][y] = 0;
			}
			// 되돌리기

		}
		// 연결 시키면됨, 연결 시키면서 전선 길이 세고, 만약에 전선이 끝까지 도달 못하면
		// 안된다. 연결된 전선은 맵에서 2로 바꾸고 다 하고 나서는 다시 돌면서 0으로 바꿔주면됨

	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			init();
			solve();
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
