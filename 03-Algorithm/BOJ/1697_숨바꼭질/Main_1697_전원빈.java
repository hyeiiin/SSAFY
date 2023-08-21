import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int k;
	static int cnt;
	static int ans;
	static int visited[] = new int[300000];
	
	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(n);
		visited[n] = 1;
		while(q.size() != 0) {
			int x = q.poll();
			if(x== k) {
				ans = visited[x]-1;
				return;
			}
			for(int i = 0; i < 3; i++) {
				int nx = 0;
				if(i == 0) {
					nx = 2*x;
				}else if(i == 1){
					nx = x-1;
				}else if(i == 2) {
					nx = x+1;
				}
				if(nx < 0)continue;
				if(nx >= 3*k)continue;
				if(visited[nx] != 0)continue;
				visited[nx] = visited[x] + 1;
				q.add(nx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		if (k <=n) {
			ans = n-k;
		}else {			
			bfs();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(ans);
		System.out.println(sb.toString());
	}

}