import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Solution_3289_이도훈 {

	static int[] parents;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		StringBuilder result = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			result.append("#").append(test_case).append(" ");

			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			parents = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int oper = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (oper == 0) {
					union(a, b);
				} else {
					if (find(a) == find(b)) {
						result.append("1");
					} else {
						result.append("0");
					}
				}
			}
			result.append("\n");
		}
		System.out.println(result);
	}

	static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);

		if (aParent != bParent) {
			parents[bParent] = aParent;
		}
	}

	static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
}
