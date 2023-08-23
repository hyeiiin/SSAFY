import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Solution_3124_이도훈 {

	static int[] parents;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		StringBuilder result = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {

			st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			parents = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}

			int[][] edges = new int[E][3];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				edges[i][0] = Integer.parseInt(st.nextToken());
				edges[i][1] = Integer.parseInt(st.nextToken());
				edges[i][2]= Integer.parseInt(st.nextToken());
			}
			Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));

			long weightSum = 0;
			for (int i = 0; i < edges.length; i++) {
				if (find(edges[i][0]) != find(edges[i][1])) {
					union(edges[i][0], edges[i][1]);
					weightSum += edges[i][2];
				}
			}
			result.append("#").append(test_case).append(" ").append(weightSum).append("\n");
		}
		System.out.println(result);
	}

	static class Edge {

		int a;
		int b;
		int w;

		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
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
