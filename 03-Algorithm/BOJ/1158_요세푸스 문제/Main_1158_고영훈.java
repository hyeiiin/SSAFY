import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1158_고영훈 {
	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final StringTokenizer st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int K = Integer.parseInt(st.nextToken());
		final Queue<Integer> q = new ArrayDeque<>();
		for (int x = 1; x <= N; x++) {
			q.add(x);
		}

		final StringBuilder sb = new StringBuilder();
		sb.append("<");
		do {
			for (int i = 1; i < K; i++) {
				q.add(q.remove());
			}
			sb.append(q.remove());
			if (!q.isEmpty()) {
				sb.append(", ");
			}
		} while (!q.isEmpty());
		sb.append(">\n");
		System.out.println(sb);
	}
}
