package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3109_서지원 {
	
	static int R, C, result;
	static int[] dx = {-1, 0, 1}, dy = {1, 1, 1};
	static char[][] board;

	private static boolean dfs(int x, int y) {
		board[x][y] = '*';
		if (y == C - 1) {
			return true;
		}
		
		for (int d = 0; d < 3; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			if (board[nx][ny] != '.') continue;
			if (dfs(nx, ny)) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp;
		temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
	
		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				board[i][j] = temp[j].charAt(0);
			}
		}
		
		result = 0;
		for (int i = 0; i < R; i++) {
			if (dfs(i, 0)) result++;
		}
		System.out.println(result);
	}

}