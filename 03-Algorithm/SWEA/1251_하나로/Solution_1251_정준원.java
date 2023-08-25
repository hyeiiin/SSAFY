package sdf;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1251_정준원 {

	static class point implements Comparable<point> {
		int to;
		long cost;

		public point(int to, long cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(point o) {
			// TODO Auto-generated method stub
			return Long.compare(this.cost, o.cost);
		}

	}

	static ArrayList<point> graph[];

	public static void main(String[] args) throws IOException { // prim 써
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			StringBuilder sb = new StringBuilder();

			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			long[] x = new long[n];
			long[] y = new long[n];

			for (int i = 0; i < n; i++) {
				x[i] = Long.parseLong(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < n; i++) {
				y[i] = Long.parseLong(st.nextToken());
			}

			double E = Double.parseDouble(br.readLine());

			PriorityQueue<point> pq = new PriorityQueue<>();
			graph = new ArrayList[n];

			for (int i = 0; i < n; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j)
						continue;

					long dis = (long) (((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])));
					graph[i].add(new point(j, dis)); // 총 섬이 n개 니깐 섬에서 섬 까지의 거리를 넣는다.
				}
			}

			for (int i = 0; i < n; i++) {
				for (point s : graph[i]) {
					// System.out.println("i " + i + " " + s.to + " " + s.cost);
				}
			}

			sb.append("#" + t + " " + Math.round(E * prim(x, y, n)));
			System.out.println(sb.toString());
		}
	}

	static long prim(long[] x, long[] y, int n) {

		long res = 0;

		PriorityQueue<point> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[n];

		int cnt = 0;
		pq.add(new point(0, 0));

		while (!pq.isEmpty()) {
			point p = pq.poll();

			if (visit[p.to])
				continue;

			// System.out.println("p.to p.cost" + p.to + " " + p.cost);
			res += p.cost;
			visit[p.to] = true;

			// System.out.println("res " + res);

			if (++cnt == n) {
				break;
			}

			for (point v : graph[p.to]) {
				if (!visit[v.to]) {
					// System.out.println("pq offer" + v.to + " " + v.cost);
					pq.offer(v);
				}
			}

		}

		return res;
	}

}