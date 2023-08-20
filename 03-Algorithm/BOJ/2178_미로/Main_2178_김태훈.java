package baekjoon;
import java.util.*;
import java.io.*;
public class Main_2178_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int n, m, min;
	static int map[][];
	static boolean[][] visit;
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visit = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String in = br.readLine();
			for (int j = 0; j < in.length(); j++) {
				map[i][j] = Character.getNumericValue(in.charAt(j));
			}
		}
	}
	
	static void solve() {
		min = Integer.MAX_VALUE;
//		dfs(0, 0, 1);
		min = bfs();
	}
	
	static int bfs() {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[]{0, 0});
		visit[0][0] = true;
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			int x = curr[0];
			int y = curr[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(map[nx][ny]==1 && !visit[nx][ny]) {
					visit[nx][ny] = true;
					map[nx][ny] = map[x][y] + 1;
					System.out.print(map[nx][ny]+" ");
					que.offer(new int[] {nx, ny});
					
				}
			}
		}
		return map[n-1][m-1];
	}
	
	static void dfs(int x, int y, int count) {
		if(x == n-1 && y == m-1) {
			min = Math.min(min, count);
			return;
		}
		
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(map[nx][ny]=='1' && !visit[nx][ny]) {
				dfs(nx, ny, count+1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		init();
		solve();
		System.out.println(min);
	}

}
