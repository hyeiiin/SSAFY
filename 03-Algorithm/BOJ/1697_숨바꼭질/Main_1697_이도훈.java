import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1697_이도훈{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] dp = new int[100001];
		dp[N] = 0;

		// dp로 왼쪽 계산, 왼쪽 이동은 -1 이동 뿐이 못함
		for (int i = N - 1; i >= 0; i--) {
			dp[i] = dp[i + 1] + 1;
		}

		// 오른쪽 계산
		for (int i = N + 1; i <= Math.min(100000, K); i++) {
			// 현재 값 기준 -1 값이나 오른쪽 값 비교 후 1만큼 더해서 dp 저장
			// 오른쪽의 경우에 1을 더해준 이유는 어차피 4 -> (5 / 2) -> 2 | 3 -> (4 / 2) -> 2 로 동일한 인덱스를 가지게 되고, 여기서 2로 딱 떨어졌는지 안떨어졌는지만 계산해서 횟수를 추가해주면 된다.
			dp[i] = Math.min(dp[i - 1] + 1, dp[(i + 1) / 2] + 1 + (i % 2));
		}

		System.out.println(dp[K]);
	}
}
