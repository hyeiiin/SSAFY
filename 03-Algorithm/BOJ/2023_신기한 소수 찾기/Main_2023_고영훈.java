import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2023_고영훈 {
	static BufferedWriter bw;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int base : new int[] { 2, 3, 5, 7 }) {
			printAmazingPrimes(1, base);
		}
		bw.flush();
	}

	private static void printAmazingPrimes(final int digit, final int base) throws Exception {
		if (digit == N) {
			bw.write(Integer.toString(base));
			bw.write("\n");
			return;
		}
		int newBase = base * 10 + 1;
		for (int i = 0; i < 5; i++) {
			if (isPrime(newBase)) {
				printAmazingPrimes(digit + 1, newBase);
			}
			newBase += 2;
		}
	}

	private static boolean isPrime(final int n) {
		if (n == 2) {
			return true;
		}
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
