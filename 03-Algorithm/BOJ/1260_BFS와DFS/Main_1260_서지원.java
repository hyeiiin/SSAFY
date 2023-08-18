package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_서지원 {

	static int N, M, V;
	static int[] visited;
	static List<List<Integer>> graph;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}
		
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for (int i = 1; i < N + 1; i++) {
			Collections.sort(graph.get(i));
		}
		
		visited = new int[N + 1];
		dfs(V);
		sb.append("\n");
		
		visited = new int[N + 1];
		bfs(V);
		sb.append("\n");
		
		System.out.println(sb);
	}
	
	private static void dfs(int v) {
		sb.append(v).append(" ");
		visited[v] = 1;
		for (int n : graph.get(v)) {
			if (visited[n] == 1) continue;
			dfs(n);
		}
	}

	private static void bfs(int v) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(v);
		visited[v] = 1;
		while (!q.isEmpty()) {
			int x = q.poll();
			sb.append(x).append(" ");
			for (int n : graph.get(x)) {
				if (visited[n] == 1) continue;
				visited[n] = 1;
				q.offer(n);
			}
		}
	}

}
