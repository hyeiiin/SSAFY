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
		indegree = new int[N + 1];
		indegree[0] = -1;

		adjList = new ArrayList[N + 1];
		for (int i = 1; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjList[from].add(to);
			indegree[to]++;
		}

		Queue<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
				sb.append(i).append(" ");
			}
		}

		while (!queue.isEmpty()) {
			Integer cur = queue.poll();

			for (Integer adj : adjList[cur]) {
				indegree[adj]--;

				if (indegree[adj] == 0) {
					queue.add(adj);
					sb.append(adj).append(" ");
				}
			}
		}
		System.out.println(sb);
	}
}
