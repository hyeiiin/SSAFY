package todo.lesson._0824;

import java.io.*;
import java.util.*;

public class Solution_1251_최지웅 {

	//
	static final int SRC = 0;

	/* input */
	static int N;
	static double E;

	static class Land {
		int x, y;

		Land(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] xArr;
	static int[] yArr;

	static Land[] lands;

	static double[][] cost;

	static class Edge implements Comparable<Edge> {
		int from, to;
		double cost;

		Edge(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge e) {
			return Double.compare(this.cost, e.cost);
		}
	}

	/* util */
	static long dist(int from, int to) {
		long dx = Math.abs(lands[from].x - lands[to].x);
		long dy = Math.abs(lands[from].y - lands[to].y);
		return dx * dx + dy * dy;
	}

	static double tax(int from, int to) {
		return E * dist(from, to);
	}

	/* solve */
	static double totalCost;

	static final int NONE = -1;

	static void prim() {

		boolean[] visited = new boolean[N];

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		visited[SRC] = true;
		for (int i = 0; i < N; i++) {
			if (i != SRC) {
				pq.offer(new Edge(SRC, i, cost[SRC][i]));
			}
		}

		int count = 1;

		Edge edge;
		int from, to;

		int addedVertex = NONE;

		while (count < N) {
			edge = pq.poll();

			from = edge.from;
			to = edge.to;

			if (visited[from] && visited[to])
				continue;

			count++;
			totalCost += cost[from][to];

			if (!visited[from])
				addedVertex = from;
			else if (!visited[to])
				addedVertex = to;
			visited[addedVertex] = true;

			for (int candidate = 0; candidate < N; candidate++) {
				if (addedVertex != candidate && !visited[candidate]) {
					pq.offer(new Edge(addedVertex, candidate, cost[addedVertex][candidate]));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {

		/* input */
//		System.setIn(new FileInputStream("re_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			xArr = new int[N];
			yArr = new int[N];
			lands = new Land[N];

			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				xArr[n] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				yArr[n] = Integer.parseInt(st.nextToken());
			}

			for (int n = 0; n < N; n++) {
				lands[n] = new Land(xArr[n], yArr[n]);
			}

			E = Double.parseDouble(br.readLine());

			cost = new double[N][N];
			for (int from = 0; from < N; from++) {
				for (int to = from + 1; to < N; to++) {
					cost[from][to] = cost[to][from] = tax(from, to);
				}
			}

			//
			totalCost = 0;
			prim();

			/* output */
			sb.append(String.format("#%d %d\n", t, Math.round(totalCost)));
		}
		System.out.println(sb);
	}

}
