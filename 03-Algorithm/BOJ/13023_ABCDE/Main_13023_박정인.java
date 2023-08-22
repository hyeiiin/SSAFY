package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13023_박정인 {
	static int N, M;
	static List<Integer>[] graph;
	static boolean flag;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {		
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		flag = false;
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			dfs(i, 1);
			if (flag) {
				break;
			}
		}
		
		System.out.println(flag == true ? 1 : 0);
	}	
	
	private static void dfs(int index, int depth) {
		if (depth == 5) {
			flag = true;
			return;
		}
		
		visited[index] = true;
		for (int next : graph[index]) {
			if (!visited[next]) {
				dfs(next, depth + 1);
			}
		}
		visited[index] = false;
	}
}
