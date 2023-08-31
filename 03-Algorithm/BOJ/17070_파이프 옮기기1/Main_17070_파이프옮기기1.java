import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {

	public static int n;
	public static int[][] map;
	public static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n + 1][n + 1];
		dp = new int[n + 1][n + 1][3];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[1][2][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				
				if (i == 1 && j == 2) {
					continue;
				}
				
				if (map[i][j] == 1) {
					dp[i][j][0] = 0;
					dp[i][j][1] = 0;
					dp[i][j][2] = 0;
					continue;
				}

				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];

				if (map[i - 1][j - 1] == 0 && map[i][j - 1] == 0 && map[i - 1][j] == 0) {
					dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				} else {
					dp[i][j][1] = 0;
				}

				dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];

			}
		}
		bw.write(String.valueOf(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]));
		bw.flush();
		br.close();
		bw.close();
	}
}

