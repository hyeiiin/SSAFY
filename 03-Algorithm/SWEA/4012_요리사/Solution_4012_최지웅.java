package todo.lesson._0810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_최지웅 {

	static int N;
	static int[][] S;

	static boolean selected[];
	static final boolean A = true;
	static final boolean B = false;

	static int tasteA;
	static int tasteB;

	static int minDiff;

	static int calDiff() {

		tasteA = 0;
		tasteB = 0;

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (selected[i] && selected[j]) {
					tasteA += S[i][j];
					tasteA += S[j][i];
				}
				if (!selected[i] && !selected[j]) {
					tasteB += S[i][j];
					tasteB += S[j][i];
				}
			}
		}

		return Math.abs(tasteA - tasteB);
	}

	static void combination(int cnt, int idx) {
		if (cnt == N / 2) {
			minDiff = Math.min(minDiff, calDiff());
			return;
		} else {
			for (int i = idx; i < N; i++) {
				selected[i] = true;
				combination(cnt + 1, i + 1);
				selected[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			S = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			minDiff = Integer.MAX_VALUE;
			selected = new boolean[N];
			combination(0, 0);

			sb.append("#").append(t).append(" ").append(minDiff).append("\n");
		}

		System.out.println(sb);
	}

}
