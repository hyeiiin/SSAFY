import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2839_고영훈 {
	private static int f(final int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 0) {
			return 0;
		}
		if (n % 5 == 0) {
			return n / 5;
		}
		final int min = f(n - 3);
		return min != -1 ? min + 1: -1;
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = Integer.parseInt(br.readLine());
		System.out.println(f(N));
	}
}
