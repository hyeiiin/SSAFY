package sdf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5644_정준원 {
	static int M, A, res; // 총 이동시간(M), BC 개수(A), 최대값 결과(res)
	static int[] dirA, dirB; // A, B 이동정보
	static BC[] BCs; // BC 배열

	static int[] dr = { 0, 0, 1, 0, -1 };
	static int[] dc = { 0, -1, 0, 1, 0 };

	// 좌표 클래스
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// BC(Battery Charger) 클래스
	static class BC {
		Point point; // 좌표
		int C, P; // 충전 범위, 처리량

		public BC(Point point, int C, int P) {
			this.point = point;
			this.C = C;
			this.P = P;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			BCs = new BC[A];
			res = 0;

			// A 이동정보 저장
			dirA = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				dirA[i] = Integer.parseInt(st.nextToken());
			}

			// B 이동정보 저장
			dirB = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				dirB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				// BC 정보 (x, y, c, p 저장)
				BCs[i] = new BC(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			solve();
			bw.write(String.format("#%d %d\n", test_case, res));
		}

		bw.close();
	}

	private static void solve() {
		// 초기 좌표 입력
		Point userA = new Point(1, 1);
		Point userB = new Point(10, 10);

		charge(userA, userB);

		for (int i = 0; i < M; i++) { // 시간은 흘러가고, a b 는 각자 움직인다.

			userA.x += dr[dirA[i]];
			userA.y += dc[dirA[i]];
			userB.x += dr[dirB[i]];
			userB.y += dc[dirB[i]];

			charge(userA, userB); // 움직인 좌표 기준으로 생각...
		}
	}

	private static void charge(Point userA, Point userB) {

		// A와 B 위치의 접속 가능한 BC 리스트
		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();

		// BC 개수만큼 반복
		for (int i = 0; i < A; i++) {

			// A와 각 BC와의 거리가 접속 가능하다면
			if (BCs[i].C >= (Math.abs(BCs[i].point.x - userA.x)) + Math.abs(BCs[i].point.y - userA.y)) {
				listA.add(i);
			}
			// B와 각 BC와의 거리가 접속 가능하다면
			if (BCs[i].C >= (Math.abs(BCs[i].point.x - userB.x)) + Math.abs(BCs[i].point.y - userB.y)) {
				listB.add(i);
			}
		}

		int max = 0;
		int temp = 0;

		// A와 B가 접속 가능한 모두 1개 이상이라면
		if (listA.size() > 0 && listB.size() > 0) {

			for (int i : listA) {
				for (int j : listB) {
					temp = 0;
					if (i == j) { // 같은 BC인 경우 처치량 나눠가지므로 한개만 더하기
						temp = BCs[i].P;
					} else { // 같은 BC가 아닌 경우 각각 처리량 더하기
						temp += BCs[i].P;
						temp += BCs[j].P;
					}
					max = Math.max(max, temp);
				}
			}

		} else if (listA.size() > 0) {
			// 접속 가능한 BC중 최대 처리량P 구하기
			for (int i : listA) {
//				if (max < BCs[i].P)
//					max = BCs[i].P;
				max = Math.max(max, BCs[i].P);
			}
			// B가 접속 가능한 BC가 1개 이상이라면
		} else if (listB.size() > 0) {
			// 접속 가능한 BC중 최대 처리량P 구하기
			for (int i : listB) {
				if (max < BCs[i].P)
					max = BCs[i].P;
			}
		}

		res += max; // 결과 누적
	}

}
