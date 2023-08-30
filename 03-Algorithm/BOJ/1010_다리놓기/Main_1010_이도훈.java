import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_이도훈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		long[][] dp = new long[30][30];

		for (int i = 0; i < 30; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || i == j) {
					dp[i][j] = 1L;
				} else {
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				}
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			if (N < M) {
				int temp = N;
				N = M;
				M = temp;
			}

			sb.append(dp[N][M])
				.append("\n");
		}

		System.out.println(sb);
	}

}
