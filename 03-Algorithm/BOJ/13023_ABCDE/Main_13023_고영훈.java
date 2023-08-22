import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13023_고영훈 {
	// 목표: 4 이상의 경로 길이가 있는지 확인하기
	final static int GOAL = 4;
	static int pathLength = 0;
	// 인접리스트, 방문배열
	static List<Integer>[] graph;
	static boolean[] visited;

	/**
	 * 조건에 맞는 경로 길이를 찾을 때까지 탐색
	 * 
	 * @param u     현재 노드
	 * @param count 현재 경로 길이
	 */
	private static void dfs(final int u, final int count) {
		if (pathLength == GOAL) {
			return;
		}
		if (count == GOAL) {
			pathLength = GOAL;
			return;
		}
		for (final int v : graph[u]) {
			if (!visited[v]) {
				visited[v] = true;
				dfs(v, count + 1);
				// 복구 안하면 누락되는 경우의 수 발생
				visited[v] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			final int a = Integer.parseInt(st.nextToken());
			final int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		// DFS?
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(i, 0);
			visited[i] = false;
		}
		System.out.println(pathLength == GOAL ? 1 : 0);
	}
}
