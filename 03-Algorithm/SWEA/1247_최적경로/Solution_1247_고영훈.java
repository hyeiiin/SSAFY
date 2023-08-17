import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_고영훈 {
	static int N;
	static Position[] positions;

	static class Position {
		final int x;
		final int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int calculateDistance(final Position a, final Position b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

	private static int permutation(final int count, final int bits, final Position last, final Position end) {
		if (count == N) {
			return calculateDistance(last, end);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if ((bits & 1 << i) == 0) {
				final int dist = calculateDistance(last, positions[i]);
				min = Math.min(min, permutation(count + 1, bits | 1 << i, positions[i], end) + dist);
			}
		}
		return min;
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			positions = new Position[N];
			final StringTokenizer st = new StringTokenizer(br.readLine());
			final Position company = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			final Position home = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++) {
				positions[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			int answer = permutation(0, 0, company, home);
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.println(sb);
	}
}
