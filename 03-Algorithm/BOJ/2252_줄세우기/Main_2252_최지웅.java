package todo.lesson._0821;

import java.io.*;
import java.util.*;

public class Main_2252_최지웅 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static List<List<Integer>> adjList;

	static List<Integer> topologyOrder;

	static boolean topologySortWithBFS() {

		int[] inDegrees = new int[N + 1];

		for (int from = 1; from <= N; from++) {
			for (int to : adjList.get(from)) {
				inDegrees[to]++;
			}
		}

		Queue<Integer> queue = new ArrayDeque<>();

		for (int from = 1; from <= N; from++) {
			if (inDegrees[from] == 0)
				queue.offer(from);
		}

		while (!queue.isEmpty()) {
			int from = queue.poll();
			topologyOrder.add(from);

			for (int to : adjList.get(from)) {
				inDegrees[to]--;
				if (inDegrees[to] == 0) {
					queue.offer(to);
				}
			}
		}

		// graph has cycle -> it can not be topologically sorted
		for (int from = 1; from <= N; from++) {
			if (inDegrees[from] > 0)
				return false;
		}

		// topologically sorted
		return true;
	}

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList<>();
		
		// dummy
		adjList.add(new LinkedList<>());
		
		for (int n = 1; n <= N; n++) {
			adjList.add(new LinkedList<>());
		}

		int from, to;
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
		}

		topologyOrder = new ArrayList<>();

		boolean sorted = topologySortWithBFS();
		
		if (sorted) {
			for (int i = 0; i < topologyOrder.size(); i++) {
				sb.append(topologyOrder.get(i) + " ");
			}
			System.out.println(sb);
		}
	}

}
