package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1251_박정인 {
	static class Edge implements Comparable<Edge> {
		int n1, n2;
		double weight;

		public Edge(int n1, int n2, double weight) {
			super();
			this.n1 = n1;
			this.n2 = n2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}	

	static int N, parents[];
	static List<Edge> edges;
	static List<Integer> xPos, yPos;
	static double E; 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			double result = 0;
			N = Integer.parseInt(br.readLine());

			parents = new int[N];

			for (int i = 0; i < N; i++) {
				parents[i] = i;
			}
			
			edges = new ArrayList<>();			
			xPos = new ArrayList<>();
			yPos = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i= 0; i < N; i++) {
				xPos.add(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i= 0; i < N; i++) {
				yPos.add(Integer.parseInt(st.nextToken()));
			}						
			
			E = Double.parseDouble(br.readLine());

			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					edges.add(new Edge(i, j, E * Math.pow(getDist(i, j), 2)));
				}
			}

			Collections.sort(edges);

			for (Edge edge : edges) {
				int n1 = edge.n1;
				int n2 = edge.n2;
				double weight = edge.weight;

				if (find(n1) != find(n2)) {
					result += weight;
					union(n1, n2);
				}
			}

			sb.append("#").append(tc).append(" ").append(Math.round(result)).append("\n");
		}

		System.out.println(sb);
	}

	private static int find(int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = find(parents[x]);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b)
			parents[b] = a;
		else
			parents[a] = b;
	}

	private static double getDist(int n1, int n2) {		
		return Math.sqrt(Math.pow(xPos.get(n1) - xPos.get(n2), 2) + Math.pow(yPos.get(n1) - yPos.get(n2), 2));
	}
}
