package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_10026_박정인 {
	static int N;
	static char map[][], map1[][]; // 정상, 적록색약
	static boolean visited[][], visited1[][];	// 정상, 적록색약
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		map1 = new char[N][N];

		visited = new boolean[N][N];
		visited1 = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char ch = str.charAt(j);
				map[i][j] = ch;
				
				// 적록색약의 경우 빨, 초 구분 X >> 하나로 통일
				if (ch == 'G') { 
					map1[i][j] = 'R';
				} else {
					map1[i][j] = ch;
				}
				
			}
		}
		
		int cnt = 0, cnt1 = 0;	// 정상, 적록색약
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(map, visited, i, j);
					cnt++;
				}					
				if (!visited1[i][j]) {
					bfs(map1, visited1, i, j);
					cnt1++;
				}					
			}
		}
		
		sb.append(cnt).append(" ").append(cnt1);
		System.out.println(sb);
	}
	
	private static void bfs(char[][] map, boolean[][] visited, int x, int y) {
		int cnt = 0;
		char target = map[x][y];
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int i = 0 ; i <4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny])	continue;
				
				if (map[nx][ny] == target) {
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;					
				}
			}
		}			
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
