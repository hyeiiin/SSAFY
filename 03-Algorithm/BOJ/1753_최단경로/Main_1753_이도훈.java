import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main_1753_이도훈 {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		ArrayList<Node>[] adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		int start = Integer.parseInt(br.readLine());


		int[] distances = new int[V + 1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[start] = 0;

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, weight));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n->n.w));
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if(cur.w > distances[cur.to]) continue;

			for (Node adj : adjList[cur.to]) {
				int newDist = distances[cur.to] + adj.w;

				if (newDist >= distances[adj.to]) {
					continue;
				}
				distances[adj.to] = newDist;
				pq.add(new Node(adj.to, distances[adj.to]));
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= V; i++) {
			sb.append(distances[i] == Integer.MAX_VALUE ? "INF" : distances[i]).append("\n");
		}
		System.out.println(sb);
	}

	static class Node {
		int to;
		int w;

		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}


}
