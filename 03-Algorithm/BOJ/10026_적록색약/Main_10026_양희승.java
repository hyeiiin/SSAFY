import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class G5_10026 {
	
	static int n, countRGB, countRB;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] RGB, RB;
	static Queue<int[]> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());

		RB = new char[n][n];
		RGB = new char[n][n];
		for (int i = 0; i < n; i++) {
			String input1 = br.readLine();
			char[] input2 = input1.toCharArray();
			for (int j = 0; j < n; j++) {
				
				RGB[i][j] = input2[j];
				
				if (input2[j] == 'G') {
					RB[i][j] = 'R';
				} else {
					RB[i][j] = input2[j];
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (RB[i][j] != 'F') {
					BFS(RB, i, j, RB[i][j]);
					countRB += 1;
				}
				if (RGB[i][j] != 'F') {
					BFS(RGB, i, j, RGB[i][j]);
					countRGB += 1;
				}
			}
		}
		
		System.out.println(countRGB + " " + countRB);
	}
	
	private static void BFS(char[][] graph, int x, int y, char s) {
		queue.add(new int[] {x, y});
		graph[x][y] = 'F';
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			x = now[0];
		    y = now[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && graph[nx][ny] == s) {
					graph[nx][ny] = 'F';
					queue.add(new int[] {nx, ny});
				}
			}
		}
	}
}
