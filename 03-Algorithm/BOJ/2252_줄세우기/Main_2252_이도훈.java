import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 위상 정렬 문제
 */
public class Main {

	static int N;
	static int M;

	static ArrayList<Integer>[] adjList;
	static int[] indegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 진입 차수 계산할 배열
		indegree = new int[N + 1];

		// 인접 리스트
		adjList = new ArrayList[N + 1];

		// 인접 리스트 초기화
		for (int i = 1; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 인접 리스트 작성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjList[from].add(to); // 연결 정보 추가
			indegree[to]++; // 진입 차수 추가
		}

		// BFS
		Queue<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) { // 진입 차수가 0인 부분부터 위상 정렬 시작
				queue.add(i);
				sb.append(i).append(" ");
			}
		}

		while (!queue.isEmpty()) {
			Integer cur = queue.poll();

			for (Integer adj : adjList[cur]) {
				indegree[adj]--; // 인접 노드의 진입 차수 하나 차감

				if (indegree[adj] == 0) { // 진입 차수가 0이면 더이상 들어올 곳이 없으므로 정렬
					queue.add(adj);
					sb.append(adj).append(" ");
				}
			}
		}
		System.out.println(sb);
	}
}
