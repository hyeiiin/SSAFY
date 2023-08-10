import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_고영훈 {
	static int N;
	static int R;
	static int[][] table;
	static boolean[] materials;
	static int min;

	private static void combination(final int count, final int start) {
		if (count == R) {
			int a = 0;
			int b = 0;
			for (int i = 0; i < N; i++) {
				if (materials[i]) {
					for (int j = i + 1; j < N; j++) {
						if (materials[j]) {
							a += table[i][j];
						}
					}
				} else {
					for (int j = i + 1; j < N; j++) {
						if (!materials[j]) {
							b += table[i][j];
						}
					}
				}
			}
			min = Math.min(min, Math.abs(a - b));
			return;
		}
		for (int i = start; i < N; i++) {
			materials[i] = true;
			combination(count + 1, i + 1);
			materials[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		// 입력 시작
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			R = N / 2;
			table = new int[N][N];
			materials = new boolean[N];
			for (int i = 0; i < N; i++) {
				final StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
				for (int j = 0; j < i; j++) {
					table[j][i] += table[i][j];
				}
			}
			// 입력 끝
			min = Integer.MAX_VALUE;
			combination(0, 0);
			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb);
	}
}
