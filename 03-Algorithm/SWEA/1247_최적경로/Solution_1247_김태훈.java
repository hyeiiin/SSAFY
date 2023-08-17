package swea;
import java.util.*;
import java.io.*;
public class Solution_1247_김태훈 {
	
	static class point{
		int x, y;
		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, n, min;
	static boolean visit[];
	static point house, company, client[];
	//집좌표, 회사좌표, 고객좌표가 있는 맵
	
	private static void init() throws IOException{
		n = Integer.parseInt(br.readLine());
		visit = new boolean[n];
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		company = new point(a, b);
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		house = new point(a, b);
		client = new point[n];
		for (int i = 0; i < n; i++) {
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			client[i] = new point(a, b);
		}
	}
	
	private static void solve() {
		min = Integer.MAX_VALUE;
		dfs(0, company.x, company.y, 0, n);
		sb.append(min).append("\n");
	}
	
	private static void dfs(int count, int x, int y, int sum, int n) {
		if(count == n) {
			sum += Math.abs(x - house.x) + Math.abs(y - house.y);
			min = Math.min(min, sum);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(!visit[i]) {
				visit[i] = true;
				dfs(count+1, client[i].x, client[i].y, sum + Math.abs(x - client[i].x) + Math.abs(y - client[i].y), n);
				visit[i] = false;
			}
		}
	}
	
	private static void print() {
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			sb.append("#").append(t+1).append(" ");
			init();
			solve();
		}
		print();
	}

}
