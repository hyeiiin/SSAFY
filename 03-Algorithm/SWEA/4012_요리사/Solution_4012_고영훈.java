import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_고영훈 {
	static int N;
	static int R;
	static int[][] table;
	static boolean[] materials;
	static int min;

	/**
	 * 조합: A 요리 맛과 B 요리 맛의 차이를 구하고 최소값을 min 변수에 반영
	 * 
	 * @param count: A 요리 재료 개수
	 * @param start: 나머지 재료들의 시작 인덱스
	 */
	private static void combination(final int count, final int start) {
		if (count == R) {
			int tasteA = 0;
			int tasteB = 0;
			for (int i = 0; i < N; i++) {
				if (materials[i]) {
					// A 요리 시너지 합
					for (int j = i + 1; j < N; j++) {
						if (materials[j]) {
							tasteA += table[i][j];
						}
					}
				} else {
					// B 요리 시너지 합
					for (int j = i + 1; j < N; j++) {
						if (!materials[j]) {
							tasteB += table[i][j];
						}
					}
				}
			}
			min = Math.min(min, Math.abs(tasteA - tasteB));
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
			for (int i = 0; i < N; i++) {
				final StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
				// 대칭하는 시너지 미리 더하기
				for (int j = 0; j < i; j++) {
					table[j][i] += table[i][j];
				}
			}
			// 입력 끝
			// materials: 재료가 어느 요리에 속하는지 경우의 수를 구하기 위한 배열.
			// A -> true, B -> false
			materials = new boolean[N];
			// min: 절대값(A 요리 맛 - B 요리 맛)의 최소값
			min = Integer.MAX_VALUE;
			combination(0, 0);
			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb);
	}
}
