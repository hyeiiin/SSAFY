package todo.lesson._0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_최지웅 {

	static int R, C;

	static char[][] land;

	static int count;

	// direction selection by greedy property
	// up-right, right, down-right
	static final int[] dr = { -1, 0, 1};
	static final int[] dc = { 1, 1, 1};

	static int nr, nc;

	static boolean[][] visited;

	static boolean found;
	
	static void print() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(visited[r][c] + " ");
			}
			System.out.println();
		}
	}
	
	static boolean valid(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	static void DFS(int r, int c) {
		
		visited[r][c] = true;
		
		if (found) return;

		if (c == C - 1) {
			found = true;
			count++;
			return;
		}

		for (int i = 0; i < 3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (!found && valid(nr, nc) && land[nr][nc] != 'x' && !visited[nr][nc]) {
				DFS(nr, nc);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		land = new char[R][C];
		for (int r = 0; r < R; r++) {
			land[r] = br.readLine().toCharArray();
		}

		visited = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			if (!visited[r][0]) {
				found = false;
				DFS(r, 0);
			}
		}

		System.out.println(count);
	}

}
