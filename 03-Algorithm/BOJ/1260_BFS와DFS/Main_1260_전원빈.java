import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int l;
	static int start;
	static int visited[];
	static List<Integer> nd[]; 
	static StringBuilder sb = new StringBuilder();
	
	static void dfs(int now) {
		sb.append(now).append(" ");
		visited[now] = 1;
		for(int i = 0; i < nd[now].size(); i++) {
			if(visited[nd[now].get(i)] == 1)continue;
			dfs(nd[now].get(i));
		}
	}
	
	static void bfs( ) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = 1;
		while(q.size() != 0) {
			int next = q.poll();
			sb.append(next).append(" ");
			for(int i = 0; i < nd[next].size(); i++) {
				if(visited[nd[next].get(i)] == 1)continue;
				visited[nd[next].get(i)] = 1;
				q.add(nd[next].get(i));
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		visited = new int[n+1];
		nd = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) {
			nd[i] = new ArrayList<>();
		}
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nd[from].add(to);
			nd[to].add(from);
		}
		for(int i = 1; i <= n; i++) {
			Collections.sort(nd[i]);
		}
		dfs(start);
		visited = new int[n+1];
		sb.append("\n");
		bfs();
		System.out.println(sb.toString());
	}

}