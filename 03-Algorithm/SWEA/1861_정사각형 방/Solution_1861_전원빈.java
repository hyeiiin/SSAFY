package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1861 {
	static int test;
	static int n;
	static int[] dx = { 1, 0, -1, 0};
	static int[] dy = { 0, -1, 0, 1};
	static int visited[][];
	static int box[][];
	static int cnt;
	static int ans;
	
	static void find(int y, int x) {
		visited[y][x] = 1;
		cnt++;
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || ny >= n || nx < 0 || nx >= n)continue;
			if(visited[ny][nx] == 1)continue;
			if(box[ny][nx] == box[y][x]+1) {
				find(ny, nx);
			}
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		test = Integer.parseInt(bf.readLine());
		for(int t = 1; t <= test; t++) {
			n = Integer.parseInt(bf.readLine());
			box = new int[n][n];
			for(int i = 0; i < n; i++) {
				StringTokenizer st  = new StringTokenizer(bf.readLine());
				for(int j = 0; j < n; j++) {
					box[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = -1;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					cnt = 0;
					visited = new int[n][n];
					find(i, j);
					if(cnt >= max) {
						if(cnt == max) {
							ans = Math.min(ans, box[i][j]);
						}else {							
							max = cnt;
							ans = box[i][j];
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append(" ").append(max).append("\n");
			
		}
	
		System.out.println(sb.toString());
	}

}
