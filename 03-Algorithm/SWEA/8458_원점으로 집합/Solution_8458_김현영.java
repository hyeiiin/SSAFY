import java.io.*;
import java.util.*;

public class Solution_8458_김현영 {
	// 점의 좌표
	// 이동 시 x와 y좌표 구분 없이 직선 상의 점이라고 가정하고 이동해도 괜찮음. 따라서 x+y의 값 저장
	static int[] dots;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스
		for (int t = 1; t <= T; t++) {
			int time = 0; // 이동횟수

			int n = Integer.parseInt(br.readLine()); // 점의 개수
			// 점의 좌표 입력
			dots = new int[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				// 이동 시 음수 양수가 중요하지 않으므로 모두 양수 처리
				dots[i] = Math.abs(x) + Math.abs(y);
			}

			// 원점 이동 가능한 경우
			if (checkMovetoZero()) {
				// 모든 점이 원점으로 이동할 때까지 반복
				while (true) {
					int zeroCount = 0; // 원점에 있는 점의 개수
					// 모든 점이 원점에 있다면 이동 종료
					for (int d : dots) {
						if (d == 0)
							zeroCount++;
					}
					if (zeroCount == n)
						break;

					// 이동 시작
					time++; // 이동횟수 증가
					for (int i = 0; i < n; i++) {
						int tempTime = time;
						// 원점으로 가까워 지기
						if (dots[i] >= tempTime) {
							dots[i] -= tempTime;
						}
						// 원점으로의 거리보다 이동횟수가 더 크다면 +1-1반복
						else { // dots[i] < time
							tempTime -= dots[i];
							dots[i] = tempTime % 2;
						}
					}
				}
			}
			// 원점으로 이동 불가능한 경우
			else {
				time = -1;
			}
			sb.append("#").append(t).append(" ").append(time).append("\n");
		}
		System.out.println(sb.toString());

	}

	// 원점으로 이동 가능한지 확인하는 함수
	// 모든 좌표의 점이 원점으로 이동하려면 원점과의 거리가 모두 홀수 or 모두 짝수여야함
	static boolean checkMovetoZero() {
		int flag = dots[0] % 2;
		for (int i = 1; i < dots.length; i++) {
			if (flag != dots[i] % 2)
				return false;
		}
		return true;
	}
}
