
import java.util.*;

import java.io.*;

public class Main_1260_조은서 {
	
	static StringBuilder sb;

	static ArrayList<Integer>[] graph;
	
	static int N, M, V;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
		
		for(int i=0; i<=N; i++) {
			graph[i].sort(null);
		}
		
		sb = new StringBuilder();
		dfs(V, new boolean[N+1]);
		System.out.println(sb);
		
		sb = new StringBuilder();
		bfs(V, new boolean[N+1]);
		System.out.println(sb);

	}
	
	private static void dfs(int start, boolean[] visited) {

		visited[start] = true;
		
		sb.append(start+ " ");
		
		for(int i=0; i<graph[start].size(); i++) {
			if(!visited[graph[start].get(i)]) {
				dfs(graph[start].get(i), visited);
			}
		}
	}
	
	private static void bfs(int start, boolean[] visited) {
		visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int next = queue.poll();
			
			sb.append(next + " ");
			
			for(int i=0; i<graph[next].size(); i++) {
				int temp = graph[next].get(i);
				if(!visited[temp])
					queue.offer(temp);
					visited[temp] = true;
			}

		}
		
	}
}
