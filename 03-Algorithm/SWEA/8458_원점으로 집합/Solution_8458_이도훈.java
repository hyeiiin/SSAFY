import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Solution_8458_이도훈 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		for (int test_case = 1; test_case <= T; test_case++) {

			int N = Integer.parseInt(br.readLine());

			long result = 1;

			ArrayList<Integer> list = new ArrayList<>();

			int even = 0;
			int odd = 0;

			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				int other = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(
					Integer.parseInt(st.nextToken()));

				if (other % 2 == 0) {
					even++;
				} else {
					odd++;
				}

				list.add(other);

				max = Math.max(max, other);
			}

			if (even > 0 && odd > 0) {
				sb.append("#").append(test_case).append(" ").append(-1).append("\n");
			} else {
				int i = 0;
				int sum = 0;

				while (true) {
					sum += i;
					if(sum >= max && (sum - max) % 2 == 0) break;
					i++;
				}
				sb.append("#").append(test_case).append(" ").append(i).append("\n");

			}


		}
		System.out.println(sb);
	}


}
