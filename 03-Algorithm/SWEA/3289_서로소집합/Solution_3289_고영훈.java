import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3289_고영훈 {
	static int[] parent;

	private static int find(final int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			final int n = Integer.parseInt(st.nextToken());
			final int m = Integer.parseInt(st.nextToken());
			parent = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				parent[i] = i;
			}
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				final boolean zero = st.nextToken().equals("0");
				final int a = Integer.parseInt(st.nextToken());
				final int b = Integer.parseInt(st.nextToken());
				// 명령이 0이면 합집합 실행, 1이면 부모가 같은지 여부를 1과 0으로 출력
				if (zero) {
					union(a, b);
				} else {
					sb.append(find(a) == find(b) ? "1" : "0");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
