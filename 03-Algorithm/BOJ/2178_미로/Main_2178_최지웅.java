package todo.lesson._0818.mock;

import java.io.*;
import java.util.*;

public class Main_2178_최지웅 {

	static final int EMPTY = 1;
	static final int BLOCK = 0;
	
	static int N, M;
	
	static int[][] maze;
	
	static boolean[][] visited;
	static int[][] dist;
	
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	
	static class Point {
		int n, m;
		
		Point(int n, int m) {
			this.n = n;
			this.m = m;
		}
	}
	
	static boolean valid(int n, int m) {
		return 1 <= n && n <= N && 1 <= m && m <= M;
	}
	
	static void BFS(int n, int m) {
		
		Queue<Point> queue = new ArrayDeque<>();
		
		visited[n][m] = true;
		dist[n][m] = 1;
		queue.offer(new Point(n, m));
		
		Point p;
		int r, c;
		int nr, nc;
		while (!queue.isEmpty()) {
			p = queue.poll();
			r = p.n; c = p.m;
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if (valid(nr, nc) && !visited[nr][nc] && maze[nr][nc] != BLOCK) {
					visited[nr][nc] = true;
					dist[nr][nc] = dist[r][c] + 1;
					if (nr == N && nc == M) return;
					queue.offer(new Point(nr, nc));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N + 1][M + 1];
		
		String line;
		for (int n = 1; n <= N; n++) {
			line = br.readLine();
			for (int m = 1; m <= M; m++) {
				maze[n][m] = line.charAt(m - 1) - '0';
			}
		}
		
		visited = new boolean[N + 1][M + 1];
		dist = new int[N + 1][M + 1];
		BFS(1, 1);
		
		System.out.println(dist[N][M]);
	}
}
