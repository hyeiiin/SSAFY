import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2023_고영훈 {
	static StringBuilder sb;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		for (int base : new int[] { 2, 3, 5, 7 }) {
			amazingPrimes(1, base);
		}
		System.out.println(sb);
	}

	private static void amazingPrimes(final int digit, final int base) {
		if (digit == N) {
			sb.append(base);
			sb.append("\n");
			return;
		}
		int newBase = base * 10 + 1;
		for (int i = 0; i < 5; i++) {
			if (isPrime(newBase)) {
				amazingPrimes(digit + 1, newBase);
			}
			newBase += 2;
		}
	}

	private static boolean isPrime(final int n) {
		if ((n & 1) == 0) {
			return false;
		}
		for (int d = 3; d * d <= n; d += 2) {
			if (n % d == 0) {
				return false;
			}
		}
		return true;
	}
}
