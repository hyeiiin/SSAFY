package todo.lesson._0821;

import java.io.*;
import java.util.*;

public class Main_10026_최지웅 {
	
	
	static int N;
	static char[][] picture;
	
	static class Point {
		int r, c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int nr, nc;
	
	static boolean[][] visited;
	
	static int normal, weak;
	
	public static boolean valid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	public static void floodFillRG(int r, int c) {
		
		Queue<Point> queue = new ArrayDeque<>();
		
		visited[r][c] = true;
		queue.offer(new Point(r, c));
		
		Point point;
		while (!queue.isEmpty()) {
			point = queue.poll();
			r = point.r;
			c = point.c;
			
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if (valid(nr, nc) && !visited[nr][nc] && (picture[nr][nc] == 'R' || picture[nr][nc] == 'G')) {
					visited[nr][nc] = true;
					queue.offer(new Point(nr, nc));
				}
			}
		}
	}
	
	public static void floodFill(int r, int c, char color) {
		
		Queue<Point> queue = new ArrayDeque<>();
		
		visited[r][c] = true;
		queue.offer(new Point(r, c));
		
		Point point;
		while (!queue.isEmpty()) {
			point = queue.poll();
			r = point.r;
			c = point.c;
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if (valid(nr, nc) && !visited[nr][nc] && picture[nr][nc] == color) {
					visited[nr][nc] = true;
					queue.offer(new Point(nr, nc));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("input.txt"));
		
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		picture = new char[N][N];
		for (int n = 0; n < N; n++) {
			picture[n] = in.next().toCharArray();
		}
		
		// normal
		visited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					floodFill(r, c, picture[r][c]);
					normal++;
				}
			}
		}
		
		// weak
		for (int r = 0; r < N; r++) {
			Arrays.fill(visited[r], false);
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					if (picture[r][c] == 'B') {
						floodFill(r, c, picture[r][c]);
						weak++;	
					} else {
						floodFillRG(r, c);
						weak++;
					}
				}
			}
		}
		
		System.out.println(normal + " " + weak);
		
		in.close();
	}
}
