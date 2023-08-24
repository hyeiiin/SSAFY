import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 최소신장트리 문제
 */
class Solution_1251_이도훈 {

	static int[] parents;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 섬의 개수
			int N = Integer.parseInt(br.readLine());

			int[][] islands = new int[N][2];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					islands[j][i] = Integer.parseInt(st.nextToken());
				}
			}

			double E = Double.parseDouble(br.readLine());

			ArrayList<Edge> edges = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						continue;
					}

					long a = Math.abs(islands[i][0] - islands[j][0]);
					long b = Math.abs(islands[i][1] - islands[j][1]);
					long dist = a * a + b * b;

					edges.add(new Edge(i, j, dist));
				}
			}

			Collections.sort(edges, Comparator.comparingLong(e -> e.weight));

			parents = new int[N];
			for (int i = 0; i < N; i++) {
				parents[i] = i;
			}

			long weightSum = 0;

			for (int i = 0; i < edges.size(); i++) {
				Edge edge = edges.get(i);

				if (find(edge.to) != find(edge.from)) {
					union(edge.to, edge.from);
					weightSum += edge.weight;
				}
			}

			sb.append("#").append(test_case).append(" ").append(Math.round(weightSum * E))
				.append("\n");
		}
		System.out.println(sb);
	}

	static class Edge {

		int from;
		int to;
		long weight;

		public Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	static void union(int a, int b) {
		int aP = find(a);
		int bP = find(b);

		if (aP != bP) {
			parents[bP] = aP;
		}
	}

	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

}
