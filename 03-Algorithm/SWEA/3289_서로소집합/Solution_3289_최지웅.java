package todo.lesson._0822;

import java.io.*;
import java.util.*;

public class Solution_3289_최지웅 {

	// disjoint set - union & find
	static int[] parents;

	static void union(int a, int b) {
		
		int setA = find(a);
		int setB = find(b);
		if (setA == setB)
			return;
		else {
			parents[setA] = parents[setB];
		}
	}

	static int find(int a) {
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}

	public static void main(String[] args) throws Exception {

		/* define */
		final int UNION = 0;
		final int COMP = 1;

		final int SAME = 1;
		final int DIFF = 0;

		/* input */
//		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int n, m;

		int op, a, b;

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			/* initialize */
			parents = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				parents[i] = i;
			}

			/* process */
			sb.append("#" + t + " ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				op = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				if (op == UNION) {
					union(a, b);
				} else if (op == COMP) {
					if (find(a) == find(b)) {
						sb.append(SAME);
					} else
						sb.append(DIFF);
				}
				
			}

			sb.append("\n");
		}

		/* output */
		System.out.println(sb);
	}

}
