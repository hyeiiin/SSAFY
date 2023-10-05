import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution_5658_이도훈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;

		StringBuilder answer = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			StringBuilder sb = new StringBuilder();

			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			// 투포인터로 처리
			String input = br.readLine();

			TreeSet<Long> set = new TreeSet<>();

			int l = 0;
			int r = N / 4;

			sb.append(input.substring(l++, r));
			set.add(Long.valueOf(sb.toString(), 16));

			while (l < N) {
				if(r >= N) r %= N;
				sb.deleteCharAt(0);
				sb.append(input.charAt(r++));
				l++;
				set.add(Long.valueOf(sb.toString(), 16));
			}

			for (int i = 0; i < K-1; i++) {
				set.pollLast();
			}

			answer.append("#").append(test_case).append(" ").append(set.pollLast()).append("\n");
		}
		System.out.println(answer);
	}
}
