import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1228_고영훈 {
	final static StringBuilder sb = new StringBuilder();
	final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	private static void solution() throws Exception {
		final int N = Integer.parseInt(br.readLine());
		final List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		final int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			st.nextToken();
			int x = Integer.parseInt(st.nextToken());
			final int y = Integer.parseInt(st.nextToken());
			for (int j = 0; j < y; j++) {
				list.add(x++, Integer.parseInt(st.nextToken()));
			}
		}
		for (int i = 0; i < 10; i++) {
			sb.append(" ").append(list.get(i));
		}
	}

	public static void main(String[] args) throws Exception {
		for (int t = 1; t <= 10; t++) {
			sb.append("#").append(t);
			solution();
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
