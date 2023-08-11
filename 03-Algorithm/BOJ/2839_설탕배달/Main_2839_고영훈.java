import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2839_고영훈 {
	static int[] dp;

	private static int f(final int n) {
		if (n < 3) {
			return -1;
		}
		if (n < 15 && n % 3 == 0) {
			return n / 3;
		}
		if (n % 5 == 0) {
			return n / 5;
		}
		if (dp[n] == -1) {
			final int min = Math.min(f(n - 5), f(n - 3));
			if (min != -1) {
				dp[n] = min + 1;
			}
		}
		return dp[n];
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		Arrays.fill(dp, -1);
		System.out.println(f(N));
	}
}
