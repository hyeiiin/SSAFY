package todo.lesson._0818;

import java.io.*;
import java.util.*;

public class Main_1260_최지웅 {
	
	static StringBuilder sb = new StringBuilder();
	
	static List<Integer>[] adjList;
	static boolean visited[];
	
	static void DFS(int u) {
		
		sb.append(u + " ");
		visited[u] = true;
		
		for (int v : adjList[u]) {
			if (!visited[v]) {
				DFS(v);
			}
		}
	}
	
	static void BFS(int s) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		sb.append(s + " ");
		visited[s] = true;
		queue.offer(s);
		
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v : adjList[u]) {
				if (!visited[v]) {
					sb.append(v + " ");
					visited[v] = true;
					queue.offer(v);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		adjList = new List[N + 1];
		for (int n = 1; n <= N; n++) {
			adjList[n] = new ArrayList<>();
		}
		visited = new boolean[N + 1];
		
		int p, q;
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
			
			adjList[p].add(q);
			adjList[q].add(p);
		}
		
		for (int n = 1; n <= N; n++) {
			Collections.sort(adjList[n]);
		}
		
		Arrays.fill(visited, false);
		DFS(V);
		sb.append("\n");
		
		Arrays.fill(visited, false);
		BFS(V);
		sb.append("\n");
		
		System.out.println(sb);
	}

}
