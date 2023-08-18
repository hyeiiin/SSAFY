package ssafyPractice;

import java.io.*;
import java.util.*;

public class Solution_5644_이세은 {

	private static int[] moveC = { 0, 0, 1, 0, -1 }; // 열 이동 이동X상우하좌
	private static int[] moveR = { 0, -1, 0, 1, 0 }; // 행 이동 이동X상우하좌

	private static BC[] bcs;
	private static int m, a, Ay = 1, Ax = 1, By = 10, Bx = 10, sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// (열, 행)!!!
			/*
			 * 동시접속 가능한 위치는 다수 개중 하나 정해서 충전가능 다수 사용자들이 모두 최대 충전을 할 수 있는 방법 찾기 충전 양을 나누면 모든
			 * 사용자 충전량이 낮아지므로 되도록 각자 갈길가는 것이 좋음
			 */
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); // 이동 시간
			a = Integer.parseInt(st.nextToken()); // bc 개수
			bcs = new BC[a];
			//합계 및 초기 위치 초기화
			sum = 0;
			Ay = 1;
			Ax = 1;
			By = 10;
			Bx = 10;

			// 사용자 a 이동 정보
			st = new StringTokenizer(br.readLine());
			int[] moveA = new int[m+1];
			moveA[0] = 0; //가장 처음에 이동하지 않는 것으로 초기값 더해주기
			for (int i = 1; i <= m; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			// 사용자 b 이동 정보
			st = new StringTokenizer(br.readLine());
			int[] moveB = new int[m+1];
			moveB[0] = 0;
			for (int i = 1; i <= m; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			// bc의 정보 a개 받기
			for (int k = 0; k < a; k++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken()); // 열
				int x = Integer.parseInt(st.nextToken()); // 행
				int c = Integer.parseInt(st.nextToken()); // 충전 범위
				int p = Integer.parseInt(st.nextToken()); // 처리량
				bcs[k] = new BC(y, x, c, p); // BC정보 저장

			}
			move(moveA, moveB);
			System.out.println("#"+test_case+" "+sum);
			

		}
	}

	public static void move(int[] moveA, int[] moveB) {
		for (int i = 0; i <= m; i++) {
			int newAy = Ay + moveC[moveA[i]];
			int newAx = Ax + moveR[moveA[i]];
			int newBy = By + moveC[moveB[i]];
			int newBx = Bx + moveR[moveB[i]];

			int max = 0;

			// bc개수만큼 여러 상황에 대해 최대 처리량 가질 수 있도록
			for (int cntA = 0; cntA < a; cntA++) {
				for (int cntB = 0; cntB < a; cntB++) {
					int tsum = 0, valA = 0, valB = 0;
					//반경 확인해서 각 사용자에게 미치는 처리량 저장
					if (Math.abs(bcs[cntA].x - newAx) + Math.abs(bcs[cntA].y - newAy) <= bcs[cntA].c) { // 사용자 A가 BC에서
						valA = bcs[cntA].p;
					}

					if (Math.abs(bcs[cntB].x - newBx) + Math.abs(bcs[cntB].y - newBy) <= bcs[cntB].c) { // 사용자 B가 BC에서																	// 충전이 가능할 때
						valB = bcs[cntB].p;
					}

					// 서로 다른 bc 경우 모두 더하기
					if(cntA != cntB) {
						tsum += valA + valB;
					}
					//같은 bc 경우 둘 중 최대값 더하기
					else
						tsum += Math.max(valA, valB);
					
					max = Math.max(max, tsum); //최댓값 갱신

				}
			}
			sum += max;
			
			// 위치 업데이트
			Ay = newAy;
			Ax = newAx;
			By = newBy;
			Bx = newBx;
		}
	}
	
	//bc 클래스
	static class BC {
		int y;
		int x;
		int c;
		int p;

		public BC(int y, int x, int c, int p) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}

	}

}
