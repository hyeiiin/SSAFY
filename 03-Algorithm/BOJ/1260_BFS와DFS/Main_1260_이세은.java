package ssafyPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_이세은 {

	private static List<Integer>[] arr;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		arr = new ArrayList[n+1];
		for(int i=0; i<=n; i++)
			arr[i] = new ArrayList<>();;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			arr[to].add(from);
		}

		for (int i = 1; i <= n; i++)
			Collections.sort(arr[i]); // 각 노드 리스트 오름차순 정렬해서 정점 번호가 작은 것부터 탐색하도록 함
		
		visited = new boolean[n + 1];
		dfs(v);
		System.out.println();
		visited = new boolean[n + 1];
		bfs(v);

	}

	public static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		for (int val : arr[v]) {
			if (!visited[val]) {
				visited[val] = true;
				dfs(val);
			}
		}
	}

	public static void bfs(int v) {
		visited[v] = true;

		Queue<Integer> q = new LinkedList<>();
		q.add(v);

		while(!q.isEmpty()) {
			int node = q.poll();
			System.out.print(node + " ");
			for (int val : arr[node]) {
				if (!visited[val]) {
					visited[val] = true;
					q.add(val);
				}

			}
		}
	}
}