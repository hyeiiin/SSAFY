import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_고영훈 {
	static int N;
	static int L;
	static int[] tastes;
	static int[] calories;

	/**
	 * 제한칼로리(L)를 넘지 않는 부분 조합 중에서 가장 점수가 높은 부분 조합 탐색
	 * 
	 * @param index: 재료 순회 인덱스
	 * @param flag: 특정 재료 사용 여부
	 * @return score: 가장 높은 점수
	 */
	private static int getMaxScore(final int index, final int flag) {
		if (index == N) {
			int cal = 0;
			int score = 0;
			for (int i = 0; i < N; i++) {
				if ((flag & 1 << i) != 0) {
					cal += calories[i];
					score += tastes[i];
				}
			}
			return cal <= L ? score : 0;
		}
		return Math.max(getMaxScore(index + 1, flag | 1 << index), getMaxScore(index + 1, flag));
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		// 입력 시작
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			tastes = new int[N];
			calories = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				tastes[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}
			// 입력 끝
			// 조건을 만족하는 하나의 부분조합을 찾아서 점수 출력
			sb.append("#" + t + " " + getMaxScore(0, 0) + "\n");
		}
		System.out.println(sb);
	}
}
