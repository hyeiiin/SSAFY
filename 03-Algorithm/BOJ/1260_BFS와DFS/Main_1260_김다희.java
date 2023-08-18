import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visit=new boolean[1001];
	static List<Integer>[] a=new LinkedList[1001];
	
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		// TODO Auto-generated method stub
		int n, m, start,u,v;
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		start=Integer.parseInt(st.nextToken());
		for(int i=1;i<=n;i++) {
			a[i]=new LinkedList<>();
		}
		for (int i = 0; i < m; i++) {
			st=new StringTokenizer(br.readLine());
			u=Integer.parseInt(st.nextToken());
			v=Integer.parseInt(st.nextToken());
			a[u].add(v);
			a[v].add(u);
		}
		for (int i = 1; i <= n; i++) {
			Collections.sort(a[i]);
		}
		dfs(start);
		sb.append("\n");
		visit=new boolean[1001];
		bfs(start);
		System.out.println(sb.toString());
	}
	private static void dfs(int s) {
		visit[s] = true;
		sb.append(s).append(" ");
		for (int i = 0; i < a[s].size(); i++) {
			int next = a[s].get(i);
			if (visit[next] == false) {
				dfs(next);
			}
		}
	}
	private static void bfs(int s) {
		Queue<Integer>q=new ArrayDeque<>();
		visit[s]=true;
		q.add(s);
		while(!q.isEmpty()) {
			int node=q.poll();
			sb.append(node).append(" ");
			for (int i = 0; i < a[node].size(); i++) {
				int next = a[node].get(i);
				if (visit[next] == false) {
					visit[next] = true;
					q.add(next);
				}
			}
		}
	}
}

