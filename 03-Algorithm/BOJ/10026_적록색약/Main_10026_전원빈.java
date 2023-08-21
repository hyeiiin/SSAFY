import java.io.*;

public class Main {
	
	static int visited[][];
	static char p[][];
	static int n;
	static int normal;
	static int rg;
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, -1, 0, 1};
	
	
	static void dfs(int y, int x, char c) {
		visited[y][x] = 1;
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(nx < 0 || nx >= n || ny < 0 || ny >=n )continue;
			if(visited[ny][nx] == 1)continue;
			if(p[ny][nx] != c)continue;
			dfs(ny, nx, c);
		}
	}
	
	static void gdfs(int y, int x) {
		visited[y][x] = 1;
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(nx < 0 || nx >= n || ny < 0 || ny >=n )continue;
			if(visited[ny][nx] == 1)continue;
			if(p[ny][nx] == 'B')continue;
			gdfs(ny, nx);
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		visited = new int[n][n];
		p = new char[n][n];
		for (int i = 0; i < n; i++) {
			String ts = bf.readLine();
			for (int j = 0; j < n; j++) {
				p[i][j] = ts.charAt(j);
			}
		}
		for (int i = 0; i < n; i++) {	
			for (int j = 0; j < n; j++) {
				if(visited[i][j] == 1)continue;
				dfs(i, j, p[i][j]);
				normal++;
			}
		}
		visited = new int[n][n];
		for (int i = 0; i < n; i++) {	
			for (int j = 0; j < n; j++) {
				if(visited[i][j] == 1)continue;
				if(p[i][j] == 'B') {
					dfs(i, j, 'B');
					rg++;
				}else {
					gdfs(i, j);
					rg++;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(normal).append(" ").append(rg);
		System.out.println(sb.toString());
	}

}