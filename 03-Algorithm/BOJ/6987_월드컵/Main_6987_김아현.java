package ssafy.Boj;

import java.io.*;
import java.util.*;

/*
 * 월드컵
 * 총 경기 수는 15번이며, 나올 수 있는 점수 합은 30
 * 각 경기에서 2개 팀이 겨루어 나올 수 있는 결과는 3가지(승-패, 무-무, 패-승)
 */
public class Main_6987_김아현 {

	static int team1, team2, idx, sum;
	static int[] win, draw, lose;
	static int[][] combi;
	static boolean possible = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		// 4개의 조(테스트케이스 4개)
		for (int i = 0; i < 4; i++) {
			possible = false;
			sum = 0; // 점수 총합
			win = new int[6]; // 나라별 경기의 승점
			draw = new int[6]; // 나라별 경기의 무승부한 점수
			lose = new int[6]; // 나라별 경기의 진 점수
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				win[j] = Integer.parseInt(st.nextToken());
				draw[j] = Integer.parseInt(st.nextToken());
				lose[j] = Integer.parseInt(st.nextToken());

				sum += ( win[j] + draw[j] + lose[j] );
			}

			// 경기에서 나온 점수의 합이 30인지 판단.
			// 30이 아닐 경우 불가능한 경기임.-> 0출력
			if (!checkSum(sum)) {
				sb.append(0).append(" ");
			} else {
				// 경기 조합 구성
				makeCombi();
				checkReuslts(0);
				if (possible) {
					sb.append(1).append(" ");
				} else {
					sb.append(0).append(" ");
				}
			}
		}
		System.out.println(sb.toString());
	}

	/**
	 * 이루어질 수 있는 경기 조합을 구성하는 메소드 6개의 팀 -> 15개의 경기
	 */
	static void makeCombi() {
		int idx = 0;
		combi = new int[15][2];

		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 6; j++) {
				combi[idx][0] = i;
				combi[idx][1] = j;
				idx++;
			}
		}
	}

	/**
	 * 15번까지 이루어질 때 경기가 성립되는 경우를 찾고 가능한지 여부를 파악하는 메소드
	 * @param matchIdx
	 */
	static void checkReuslts(int matchIdx) {
		// 가능한 경우가 있다면 return;
		if (possible)
			return;

		// 총 경기수 15번이 이루어지고 win, draw, lose값이 모두 0이라면
		if (matchIdx == 15) {
			if (checkRest()) {
				possible = true;
			}
			return;
		}

		// 경기할 두 팀
		int team1 = combi[matchIdx][0];
		int team2 = combi[matchIdx][1];

		// a가 이기면
		if (win[team1] > 0 && lose[team2] > 0) {
			win[team1]--;
			lose[team2]--;
			checkReuslts(matchIdx + 1);
			win[team1]++;
			lose[team2]++;
		}
		// a와 b 무승부
		if (draw[team1] > 0 && draw[team2] > 0) {
			draw[team1]--;
			draw[team2]--;
			checkReuslts(matchIdx + 1);
			draw[team1]++;
			draw[team2]++;
		}
		// b가 이기면
		if (lose[team1] > 0 && win[team2] > 0) {
			lose[team1]--;
			win[team2]--;
			checkReuslts(matchIdx + 1);
			lose[team1]++;
			win[team2]++;
		}
	}

	/**
	 * 경기에서 나온 점수의 합이 30인지 판단하는 메소드
	 * @param sum 점수의 합
	 * @return true 문제없음 false 불가능
	 */
	static boolean checkSum(int sum) {
		return sum == 30;
	}

	/**
	 * 15번의 경기가 이루어졌을 때 해당 경기 조합이 가능한 경기인지 판단하는 메소드 
	 * 경기가 15번 치뤄졌을 때 남아있는 값이 모두 0이어야함.
	 * @return true 가능한 경기 false 불가능한 경기
	 */
	static boolean checkRest() {
		for (int i = 0; i < 6; i++) {
			if (win[i] != 0 || draw[i] != 0 || lose[i] != 0) {
				return false;
			}
		}
		return true;
	}
}
