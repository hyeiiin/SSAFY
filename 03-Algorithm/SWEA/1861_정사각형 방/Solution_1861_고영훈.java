import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_1861_고영훈 {
	static int N;
	static int[][] mat;
	static int[][] dp;

	public static int dfs(final int i, final int j) {
		int result = dp[i][j];
		if (result != -1) {
			return result;
		}

		result = 0;
		final int next = mat[i][j] + 1;
		// 상하좌우
		if (i - 1 >= 0 && mat[i - 1][j] == next) {
			result = Math.max(result, dfs(i - 1, j));
		}
		if (i + 1 < N && mat[i + 1][j] == next) {
			result = Math.max(result, dfs(i + 1, j));
		}
		if (j - 1 >= 0 && mat[i][j - 1] == next) {
			result = Math.max(result, dfs(i, j - 1));
		}
		if (j + 1 < N && mat[i][j + 1] == next) {
			result = Math.max(result, dfs(i, j + 1));
		}
		result++;

		dp[i][j] = result;
		return result;
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			// matrix
			mat = new int[N][N];
			for (int i = 0; i < N; i++) {
				final String[] tokens = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(tokens[j]);
				}
			}
			// dp
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], -1);
			}
			// dfs
			int start = 0;
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					final int r = dfs(i, j);
					if (r > max) {
						max = r;
						start = mat[i][j];
					} else if (r == max && mat[i][j] < start) {
						start = mat[i][j];
					}
				}
			}
			sb.append("#" + t + " " + start + " " + max + "\n");
		}
		System.out.println(sb);
	}
}
