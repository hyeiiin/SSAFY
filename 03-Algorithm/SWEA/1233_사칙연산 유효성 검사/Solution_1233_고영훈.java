import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1233_고영훈 {
	private static boolean isOp(final String line) {
		final char c = line.split(" ", 3)[1].charAt(0);
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			final int N = Integer.parseInt(br.readLine());
			final String[] lines = new String[N];
			for (int i = 0; i < N; i++) {
				lines[i] = br.readLine();
			}

			int validation = 1;
			final int M = (N - (N & 1)) / 2;
			for (int i = 0; i < M; i++) {
				if (!isOp(lines[i])) {
					validation = 0;
					break;
				}
			}
			if (validation == 0) {
				for (int i = M; i < N; i++) {
					if (isOp(lines[i])) {
						validation = 0;
						break;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(validation).append("\n");
		}
		System.out.println(sb);
	}
}
