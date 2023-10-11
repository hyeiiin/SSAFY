import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8458_고영훈 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int solution() throws Exception {
		final int N = Integer.parseInt(br.readLine());
		int[] dists = new int[N];

		for (int i = 0; i < N; i++) {
			final StringTokenizer st = new StringTokenizer(br.readLine());
			final int x = Integer.parseInt(st.nextToken());
			final int y = Integer.parseInt(st.nextToken());
			dists[i] = Math.abs(x) + Math.abs(y);
		}

		int maxDist = 0;
		int odd = -1;
		for (int i = 0; i < N; i++) {
			final int dist = dists[i];
			if (odd == -1) {
				odd = dist & 1;
			} else if (odd != (dist & 1)) {
				return -1;
			}
			if (dist > maxDist) {
				maxDist = dist;
			}
		}

		int i;
		for (i = 0; maxDist > 0; maxDist -= ++i);
		if ((maxDist & 1) == 1) {
			i += (i & 1) == 1 ? 2 : 1;
		}
		return i;
	}

	public static void main(String[] args) throws Exception {
		final int T = Integer.parseInt(br.readLine());
		final StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " " + solution() + "\n");
		}
		System.out.println(sb);
	}
}
