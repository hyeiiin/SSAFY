import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_고영훈 {
	static class Range {
		final int startY;
		final int startX;
		final int halfY;
		final int halfX;
		final int endY;
		final int endX;

		public Range(int startY, int startX, int endY, int endX) {
			this.startY = startY;
			this.startX = startX;
			this.halfY = startY + (endY - startY) / 2;
			this.halfX = startX + (endX - startX) / 2;
			this.endY = endY;
			this.endX = endX;
		}
	}

	private static long f(final int r, final int c, final Range range, final long start, final long end) {
		if (r == range.startY && c == range.startX) {
			return start;
		}
		final Range[] ranges = {
				new Range(range.startY, range.startX, range.halfY, range.halfX),
				new Range(range.startY, range.halfX, range.halfY, range.endX),
				new Range(range.halfY, range.startX, range.endY, range.halfX),
				new Range(range.halfY, range.halfX, range.endY, range.endX),
		};
		final long interval = (end - start) / 4;
		long s = start;
		for (int i = 0; i < 4; i++, s += interval) {
			final Range nr = ranges[i];
			if (r >= nr.startY && r < nr.endY && c >= nr.startX && c < nr.endX) {
				final long result = f(r, c, nr, s, s + interval);
				if (result != 0) {
					return result;
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final StringTokenizer st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int r = Integer.parseInt(st.nextToken());
		final int c = Integer.parseInt(st.nextToken());
		final int L = 2 << N;
		final long result = f(r, c, new Range(0, 0, L, L), 0, (long) L * L);
		System.out.println(result);
	}
}
