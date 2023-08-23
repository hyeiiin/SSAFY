package sdf;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1600_정준원 {
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int[] dr2 = { 2, 2, 1, 1, -1, -1, -2, -2 };
	static int[] dc2 = { 1, -1, 2, -2, -2, 2, -1, 1 };
	static int[][] arr;
	static boolean[][][] visit;
	static int w;
	static int h;
	static int res = Integer.MAX_VALUE;
	static int k;

	static class point {

		int r, c, skill, time;

		public point(int r, int c, int skill, int time) {
			super();
			this.r = r;
			this.c = c;
			this.skill = skill;
			this.time = time;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		arr = new int[h][w];
		visit = new boolean[h][w][k + 1];

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < w; j++) {

				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs(0, 0);
		System.out.println(res == Integer.MAX_VALUE ? -1 : res);

	}

	static void bfs(int r, int c) {
		visit[r][c][0] = true;

		Queue<point> q = new LinkedList<>();

		q.add(new point(r, c, 0, 0));

		while (!q.isEmpty()) {

			for (int j = 0; j < q.size(); j++) {

				point p = q.poll();

				if (p.r == h - 1 && p.c == w - 1) {

					res = Math.min(res, p.time);
					return;
				}

				if (p.skill < k) {

					for (int i = 0; i < 8; i++) {

						int nr = p.r + dr2[i];
						int nc = p.c + dc2[i];
						int sk = p.skill;
						int time = p.time;

						if (nr < 0 || nc < 0 || nr >= h || nc >= w)
							continue;

						if (arr[nr][nc] == 1)
							continue;

						if (visit[nr][nc][sk + 1])
							continue;

						q.add(new point(nr, nc, sk + 1, time + 1));
						visit[nr][nc][sk + 1] = true;

					}

				}

				for (int i = 0; i < 4; i++) {

					int nr = p.r + dr[i];
					int nc = p.c + dc[i];
					int sk = p.skill;
					int time = p.time;

					if (nr < 0 || nc < 0 || nr >= h || nc >= w)
						continue;

					if (arr[nr][nc] == 1)
						continue;

					if (visit[nr][nc][sk])
						continue;

					q.add(new point(nr, nc, sk, time + 1));
					visit[nr][nc][sk] = true;

				}

			}

		}

	}

}
