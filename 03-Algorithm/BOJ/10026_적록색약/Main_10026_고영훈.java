import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_10026_고영훈 {
	final static int[] dy = { -1, 1, 0, 0 };
	final static int[] dx = { 0, 0, -1, 1 };
	static int N;
	static char[][] grid;
	static boolean[][] visited;

	private static void dfs(final int y, final int x, final char color) {
		visited[y][x] = true;
		for (int i = 0; i < 4; i++) {
			final int ny = y + dy[i];
			final int nx = x + dx[i];
			if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx] && grid[ny][nx] == color) {
				dfs(ny, nx, color);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new char[N][];
		for (int i = 0; i < N; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		// 비적록색약
		int a = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j, grid[i][j]);
					a++;
				}
			}
		}
		// 적록색약
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == 'G') {
					grid[i][j] = 'R';
				}
			}
		}
		int b = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j, grid[i][j]);
					b++;
				}
			}
		}
		// 출력
		System.out.println(a + " " + b);
	}
}
