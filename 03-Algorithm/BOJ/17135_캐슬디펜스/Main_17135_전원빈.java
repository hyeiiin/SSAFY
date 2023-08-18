import java.io.*;
import java.util.*;

class Point {
	int y;
	int x;
	int r;
	
	Point(int y, int x, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(y).append(" ").append(x);
		return sb.toString();
	}
}

public class Main {
	static int n;
	static int m;
	static int d;
	static int map[][];
	static int visited[][];
	static int findyou[][];
	static int arc[];
	static int[] arcstatus = new int[3];
	static int tempans;
	static int ans;
	static int flag;
	static int dx[] = {-1, 0, 1};
	static int dy[] = {0, -1, 0};
	
	static void swap(int p[], int i, int j) {
		int temp  = p[i];
		p[i] =p[j];
		p[j] = temp;
	}
	
	static boolean np(int p[]) {
		int n = p.length;
		int i = n-1;
		while(i > 0 && p[i-1] >= p[i])--i;
		if(i == 0)return false;
		
		int j = n-1;
		while(p[i-1] >= p[j])--j;
		swap(p, i-1, j);
		
		int k = n-1;
		while(i < k) {
			swap(p, i++, k--);
		}
		return true;
	}
	
	static void bfs(int y, int x, int r){
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(y,x, r));
		int vivisited[][] = new int[n][m];
		vivisited[y][x] = 1;
		while(q.size() != 0) {
			int hx = q.peek().x;
			int hy = q.peek().y;
			int hr = q.peek().r;
			q.remove();
			if(hr >= d)return;
			if(findyou[hy][hx] == 1) {
				visited[hy][hx] = 1;
				return;
			}
			for(int i = 0; i < 3; i++) {
				int ny = hy + dy[i];
				int nx = hx + dx[i];
				if(ny < 0 || nx < 0 || nx >= m)continue;
				if(vivisited[ny][nx] == 1)continue;
				vivisited[ny][nx] = 1;
				q.add(new Point(ny, nx, hr+1));
			}
		}
	}
	static void gogogo(int where) {
		for(int i = 0; i < 3; i++) {
			bfs(where, arcstatus[i], 0);
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(visited[i][j] == 1) findyou[i][j] = 0;
			}
		}
		if(where == 0)return;
		gogogo(where-1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		arc = new int[m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = m - 3; i < m; i++) {
			arc[i] = 1;
		}
		do {
			findyou = new int[n][m];
			for(int i = 0; i < n; i++) {
				findyou[i] =Arrays.copyOf(map[i], m);
			}
			tempans = 0;
			int num = 0;
			visited = new int[n][m];
			for(int i = 0; i < m; i++) {
				if(arc[i] == 1) {
					arcstatus[num] = i;
					num++;
				}
			}
			gogogo(n-1);
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(visited[i][j] == 1)tempans++;
				}
			}
			ans = Math.max(ans, tempans);
		}while(np(arc));
		StringBuilder sb = new StringBuilder();
		sb.append(ans);
		System.out.println(sb.toString());
	}

}