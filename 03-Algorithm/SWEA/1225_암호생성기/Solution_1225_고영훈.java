import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_1225_고영훈 {
	private static Deque<Integer> solution(final String s) {
		final Deque<Integer> q = new ArrayDeque<>();
		final StringTokenizer st = new StringTokenizer(s);
		for (int i = 0; i < 8; i++) {
			int n = Integer.parseInt(st.nextToken());
			q.add(n);
		}
		boolean cond = true;
		while (cond) {
			for (int i = 1; i <= 5; i++) {
				final int n = q.poll() - i;
				if (n <= 0) {
					q.add(0);
					cond = false;
					break;
				}
				q.add(n);
			}
		}
		return q;
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			sb.append("#");
			sb.append(t);
			for (final int n : solution(br.readLine())) {
				sb.append(" ");
				sb.append(n);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
