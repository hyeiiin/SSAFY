import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_고영훈 {
	static int N;
	static int M;
	// 인접 리스트
	static List<Integer>[] graph;
	// 차수 배열
	static int[] degrees;

	/**
	 * BFS: 위상 정렬
	 * 
	 * @return 정렬된 결과
	 */
	private static List<Integer> bfs() {
		final List<Integer> result = new ArrayList<>();
		final Queue<Integer> q = new ArrayDeque<>();
		// 큐에 차수가 0인 노드 삽입
		for (int i = 1; i <= N; i++) {
			if (degrees[i] == 0) {
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			final Integer u = q.remove();
			result.add(u);
			// 큐에 차수가 0인 자식 노드 삽입
			if (graph[u] != null) {
				for (final Integer v : graph[u]) {
					if (--degrees[v] == 0) {
						q.add(v);
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		degrees = new int[N + 1];
		// 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			final int a = Integer.parseInt(st.nextToken());
			final int b = Integer.parseInt(st.nextToken());
			if (graph[a] == null) {
				graph[a] = new ArrayList<Integer>();
			}
			graph[a].add(b);
			degrees[b]++;
		}
		// 위상 정렬하고 결과 출력
		final StringBuilder sb = new StringBuilder();
		for (final int n : bfs()) {
			sb.append(n + " ");
		}
		System.out.println(sb);
	}
}
