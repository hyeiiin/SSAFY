package algo_0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10026_조은서 {

	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int normal_count, abnormal_count;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
	
		visited = new boolean[N][N];
		normal_count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					dfs(i,j);
					normal_count++;
				}
			}
		}
		
		// 적록색약 결과 구하기 위해 G를 R로 변환
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 'G') {
					map[i][j] = 'R';
				}
			}
		}
		

		visited = new boolean[N][N]; // 방문 배열 초기화
		abnormal_count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					dfs(i,j);
					abnormal_count++;
				}
			}
		}
		
		System.out.println(normal_count + " " + abnormal_count);
		
	}

	static void dfs(int x, int y) {
		char ch = map[x][y];
		visited[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny <0 || nx >= N || ny >= N) { // 범위 넘어가면 pass
				continue;
			}
			
			if(!visited[nx][ny] && map[nx][ny] == ch) { // 방문하지 않았고, 값이 같다면 그 위치에서 다시 탐색
				dfs(nx,ny);
			}
		}
		
	}
}

/*
5
RRRBB
GGBBB
BBBRR
BBRRR
RRRRR
 */
