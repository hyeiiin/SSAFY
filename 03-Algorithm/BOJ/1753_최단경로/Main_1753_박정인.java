package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1753
 * 
 * @author SSAFY
 *
 */
public class Main_1753_박정인 {
	static class Node implements Comparable<Node> {
		int index, weight;

		public Node(int index, int weight) {
			super();
			this.index = index;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int V, E, K;
	static List<Node>[] graph;
	static int[] d;

	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		graph = new ArrayList[V + 1];
		d = new int[V + 1];

		Arrays.fill(d, INF);		
		
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, weight));
		}

		dijkstra();
		
		for (int i = 1; i <= V; i++) {
			sb.append(d[i] == INF ? "INF" : d[i]).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(K, 0));
		d[K] = 0;		

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (d[now.index] < now.weight)	continue;						

			for (Node next : graph[now.index]) {
				int cost = next.weight + d[now.index];
				if (cost < d[next.index]) {
					d[next.index] = cost;
					pq.offer(new Node(next.index, cost));
				}
			}
		}
	}
}
