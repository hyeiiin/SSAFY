import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_김나연 {
	
	static int n,m,v,from, to;
	static int a[][];
	static boolean visited[];
	
	public static void dfs(int here, StringBuilder sb) {
		visited[here]=true;
		
		
		for(int i=1;i<=n;i++) {
			if(visited[i]==true)continue;
			
			if(a[here][i]==1) {
				sb.append(i).append(" ");
				dfs(i, sb);
			}
		}
	}
	
	public static void bfs(int here, StringBuilder sb) {
		visited[here]=true;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(here);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for (int i = 1; i <= n; i++) {
				if(visited[i]==true)continue;
				if(a[current][i]==1) {
					sb.append(i).append(" ");
					queue.offer(i);
					visited[i]=true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		
		a = new int [n + 1][n + 1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			a[from][to]=1;
			a[to][from]=1;
		}
		
		visited = new boolean[n + 1];
		
		sb.append(v+" ");
		dfs(v, sb);
		sb.append("\n");
		
		visited = new boolean[n + 1];
		sb.append(v+" ");
		bfs(v,sb);
		
		System.out.println(sb);
	}

}
