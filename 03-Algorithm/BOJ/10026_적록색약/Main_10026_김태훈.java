package baekjoon;
import java.util.*;
import java.io.*;
public class Main_10026_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static char map[][];
	static boolean visit[][];
	static int n, count_a, count_b;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	static void init() throws IOException{
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
	}
	
	static void solve() {
		//일반인
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visit[i][j]) {					
					dfs(i, j);
					count_a++;
				}
			}
		}
		//적록색약
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j]=='G') {
					map[i][j] = 'R';
				}
			}
		}
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visit[i][j]) {					
					dfs(i, j);
					count_b++;
				}
			}
		}
		System.out.println(count_a + " " + count_b);
	}
	
	static void dfs(int x, int y) {
		visit[x][y] = true;
		char a = map[x][y];
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(map[nx][ny]==a && !visit[nx][ny]) {
				dfs(nx, ny);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		init();
		solve();
	}

}
