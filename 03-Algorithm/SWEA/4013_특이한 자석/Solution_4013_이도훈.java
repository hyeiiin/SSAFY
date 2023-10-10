import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution_4013_이도훈 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {

			int K = Integer.parseInt(br.readLine());
			/**
			 * 초기화 파트
			 */
			String[] gears = new String[4];

			for (int i = 0; i < 4; i++) {
				gears[i] = br.readLine().replace(" ", "");
			}

			// 초기 톱니바퀴 오른쪽은 위치상 2번 인덱스
			int[] gearIdx = new int[4];
			Arrays.fill(gearIdx, 2);


			StringTokenizer st;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());

				int idx = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());

				boolean[] isSame = new boolean[3];

				//( i + 8 )% 8 -> 원형 배열 인덱스 계산

				// 맞물린 톱니바퀴가 동일한 극인지 체크 , 톱니바퀴 오른쪽의 반대는 (오른쪽 인덱스) + 4 % 8
				for (int j = 0; j < 3; j++) {
					if (gears[j].charAt(gearIdx[j]) == gears[j + 1].charAt((gearIdx[j + 1] + 4)%8)) {
						isSame[j] = true;
					}
				}

				// 입력 기어 기준으로 오른쪽 돌리기
				int rightDir = dir;
				for (int j = idx; j < 3; j++) {
					if (isSame[j]) {
						break;
					}

					// 오른쪽 톱니바퀴 움직이기
					gearIdx[j + 1] = (gearIdx[j + 1] + rightDir + 8) % 8;
					rightDir = -rightDir; // 방향 바뀜
				}

				// 입력 기어 기준으로 왼쪽 돌리기
				int leftDir = dir;
				for (int j = idx - 1; j >= 0; j--) {
					if (isSame[j]) {
						break;
					}

					gearIdx[j] = (gearIdx[j] + leftDir + 8) % 8;
					leftDir = -leftDir;
				}

				gearIdx[idx] = (gearIdx[idx] + -dir + 8) % 8;
			}

			int sum = 0;

			for (int i = 0; i < gears.length; i++) {
				if (gears[i].charAt((gearIdx[i] + 6) % 8) == '1') {
					sum += Math.pow(2, i);
				}
			}

			answer.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.println(answer);
	}
}
