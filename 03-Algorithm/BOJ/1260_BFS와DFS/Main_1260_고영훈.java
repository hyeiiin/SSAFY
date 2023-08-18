import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_고영훈 {
	final static Map<Integer, List<Integer>> graph = new HashMap<>();
	final static List<Integer> result = new ArrayList<>();

	private static void dfs(final int start, final boolean[] visited) {
		result.add(start);
		visited[start] = true;
		for (final int v : graph.get(start)) {
			if (!visited[v]) {
				dfs(v, visited);
			}
		}
	}

	private static void bfs(int start, final boolean[] visited) {
		final Queue<Integer> q = new ArrayDeque<>();
		visited[start] = true;
		q.offer(start);
		while (!q.isEmpty()) {
			start = q.poll();
			result.add(start);
			for (final int v : graph.get(start)) {
				if (!visited[v]) {
					visited[v] = true;
					q.offer(v);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());
		final int V = Integer.parseInt(st.nextToken());
		// 그래프에 양방향 간선 정보를 입력합니다.
		for (int v = 1; v <= N; v++) {
			graph.put(v, new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			final int a = Integer.parseInt(st.nextToken());
			final int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		// 정점 번호가 작은 것을 먼저 방문시키기 위해 정렬합니다.
		for (int v = 1; v <= N; v++) {
			Collections.sort(graph.get(v));
		}
		// DFS
		dfs(V, new boolean[N + 1]);
		for (final int v : result) {
			sb.append(v + " ");
		}
		sb.append("\n");
		// 결과 list 비우고 BFS
		result.clear();
		bfs(V, new boolean[N + 1]);
		for (final int v : result) {
			sb.append(v + " ");
		}
		// 출력
		sb.append("\n");
		System.out.println(sb);
	}
}
