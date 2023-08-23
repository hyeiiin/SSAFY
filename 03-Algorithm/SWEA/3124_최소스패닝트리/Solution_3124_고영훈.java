import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_3124_고영훈 {
	static int[] parents;

	static void make(int size) {
		parents = new int[size];
		for (int i = 1; i < size; i++) {
			parents[i] = i;
		}
	}

	static int find(int x) {
		if (x == parents[x]) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) {
			return false;
		}
		parents[b] = a;
		return true;
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			final int V = Integer.parseInt(st.nextToken());
			final int E = Integer.parseInt(st.nextToken());
			final int[][] edges = new int[E][];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				final int u = Integer.parseInt(st.nextToken());
				final int v = Integer.parseInt(st.nextToken());
				final int w = Integer.parseInt(st.nextToken());
				edges[i] = new int[] { u, v, w };
			}
			Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));

			make(V + 1);
			long weight = 0;
			for (int i = 0; i < E; i++) {
				final int[] e = edges[i];
				if (union(e[0], e[1])) {
					weight += e[2];
				}
			}
			sb.append("#" + t + " " + weight + "\n");
		}
		System.out.println(sb);
	}
}
