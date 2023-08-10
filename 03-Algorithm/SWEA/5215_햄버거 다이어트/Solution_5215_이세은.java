package ssafyPractice;

import java.io.*;
import java.util.*;

public class Solution_5215_이세은 {

	private static int n, l; // 재료 수, 제한 칼로리
	private static int[][] foods; // 음식 정보 저장 배열
	private static int maxPoints;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 재료 수
			l = Integer.parseInt(st.nextToken()); // 제한 칼로리
			foods = new int[n][2]; // 인덱스별로 점수와 칼로리 가지는 배열을 가지는 2차원 배열 생성

			// 재료들 입력받기
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				foods[i][0] = Integer.parseInt(st.nextToken());
				foods[i][1] = Integer.parseInt(st.nextToken());
			}
			maxPoints = 0;
			getMaxPointsSet(0, 0, 0);

			// 가장 만족도가 높은 집합의 점수 출력
			System.out.println("#" + test_case + " " + maxPoints);
		}
	}

	private static void getMaxPointsSet(int cnt, int tmpP, int tmpC) {

		// 칼로리 더하는 도중 제한 칼로리 벗어날 경우 바로 종료
		if (tmpC > l)
			return;
		// 끝까지 모두 탐색 마친 경우
		if (cnt == n) {
			maxPoints = Math.max(maxPoints, tmpP);// 칼로리 더 높은 것으로 업데이트
			return;
		}

		getMaxPointsSet(cnt + 1, tmpP + foods[cnt][0], tmpC + foods[cnt][1]); // 계속 더해가기
		getMaxPointsSet(cnt + 1, tmpP, tmpC); // 재료들 더하지 않기

	}

}
