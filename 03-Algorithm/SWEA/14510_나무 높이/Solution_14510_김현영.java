import java.io.*;
import java.util.*;

public class Solution_14510_김현영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine()); // 나무의 수
			int days = 0; // 물을 주는 최소 날짜

			st = new StringTokenizer(br.readLine());
			// 나무 키 입력
			int[] tree = new int[n];
			int max = 0; // 나무 키 중 최대값
			for (int i = 0; i < n; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				max = max < tree[i] ? tree[i] : max;
			}

			int day1 = 0; // 키가 1 자라야하는 날짜의 수
			int day2 = 0; // 키가 2 자라야하는 날짜의 수
			// 나무들이 최대 키가 되기 위해서 필요한 값을 구하고 => max - tree[i]
			// 필요한 값에서 1자라야하는 날짜, 2자라야하는 날짜의 수 구하기
			for (int i = 0; i < n; i++) {
				//현재 나무가 최대키라면 다음 나무 탐색
				if (tree[i] == max)
					continue;
				int need = max - tree[i]; //최대 키가 되기 위해서 필요한 값
				day2 += need / 2; 
				day1 += need % 2;
			}

			//day1과 day2 중 어느 날이라도 0이 아니여야만 날짜 계산 진행
			if (day1 != 0 || day2 != 0) {
				//day1과 day2가 같은 경우
				if (day1 == day2) {
					days += day1 * 2;
				}
				// day2의 날이 더 많은 경우
				// days에 day1*2 의 값을 넣어주고
				// 남은 day2는 홀수번째는 그대로 두고 짝수번째는 day1로 나누어 물을 줄 수 있음
				// day2 = 4 인 경우 => 2번|1번|2번|3번|4번|x|4번
				else if (day1 < day2) {
					days += day1 * 2;
					day2 -= day1;
					if (day2 % 3 == 0)
						days += (day2 / 3) * 4;
					else
						days += (day2 / 3) * 4 + day2 % 3 + 1;
				}
				// day1의 날이 더 많은 경우
				// days에 day2*2 의 값을 넣어주고
				// 남은 day1은 순차적으로 넣어줌
				// day1 = 3 인 경우 => 1번|x|2번|x|3번|
				else {
					days += day2 * 2;
					day1 -= day2;
					days += (day1 - 1) * 2 + 1;
				}
			}

			sb.append("#").append(t).append(" ").append(days).append("\n");
		}
		System.out.println(sb.toString());

	}

}
