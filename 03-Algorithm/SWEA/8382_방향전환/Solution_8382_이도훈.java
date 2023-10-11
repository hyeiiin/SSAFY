import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution_8382_이도훈 {


	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		for (int test_case = 1; test_case <= T; test_case++) {

			int answer = 0;

			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int a = Math.abs(x1 - x2);
			int b = Math.abs(y1 - y2);

			int max = Math.max(a, b);
			int min = Math.min(a, b);

			int diff = max - min;

			answer += min * 2;

			if (diff % 2 == 0) {
				answer += diff * 2;
			} else {
				answer += diff * 2 - 1;
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}


}
