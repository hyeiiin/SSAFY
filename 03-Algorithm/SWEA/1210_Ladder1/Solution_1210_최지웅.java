package lesson.swea_ans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1210_최지웅 {

	static BufferedReader br;
	static StringTokenizer st;

	static int tc;

	static final int SIZE = 100;
	static int[][] data;

	static int destY, destX;
	static int srcY, srcX;

	static final int UP = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;
	
	static final int[] dy = { 0, 0, -1};
	static final int[] dx = { -1, 1, 0};
	
	static int ny, nx;

	static boolean[][] visited;

	static boolean found;

	static boolean valid(int y, int x) {
		return 0 <= y && y < SIZE && 0 <= x && x < SIZE;
	}
	
	static void DFS(int y, int x) {
		
		if (found) return;
		
		visited[y][x] = true;

		if (y == 0) {
			srcY = y;
			srcX = x;
			found = true;
			return;
		}
	
		for (int i = 0; i < 3; i++) {
			ny = y + dy[i];
			nx = x + dx[i];
			
			if (valid(ny, nx) && data[ny][nx] > 0 && !visited[ny][nx]) {
				DFS(ny, nx);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			tc = Integer.parseInt(br.readLine());

			data = new int[SIZE][SIZE];

			for (int i = 0; i < SIZE; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < SIZE; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
					if (data[i][j] == 2) {
						destY = i;
						destX = j;
					}
				}
			}

			visited = new boolean[SIZE][SIZE];

			found = false;
			DFS(destY, destX);

			System.out.println("#" + tc + " " + srcX);
		}
	}
}
