package swea;

import java.util.*;
import java.io.*;

public class Solution_5644_김태훈 {

	static class point {
		int x, y;

		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void move(int dir) {
			switch (dir) {
			case 1: // 상
				x--;
				break;
			case 2: // 우
				y++;
				break;
			case 3: // 하
				x++;
				break;
			case 4: // 좌
				y--;
				break;
			}
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int M, A, total;
	static int dirA[], dirB[], bc[][];

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		dirA = new int[M];
		dirB = new int[M];
		bc = new int[A][4];
		total = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			dirA[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			dirB[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				bc[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {

		// 첫 좌표 입력, 충전, 움직이고 충전
		point userA = new point(1, 1);
		point userB = new point(10, 10);

		charge(userA, userB);
		for (int i = 0; i < M; i++) {
			userA.move(dirA[i]);
			userB.move(dirB[i]);
			charge(userA, userB);
		}
		sb.append(total);
	}

	static void charge(point userA, point userB) {
		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();

		for (int i = 0; i < A; i++) {
			if (bc[i][2] >= Math.abs(bc[i][1] - userA.x) + Math.abs(bc[i][0] - userA.y)) {
				listA.add(i);
			}
			if (bc[i][2] >= Math.abs(bc[i][1] - userB.x) + Math.abs(bc[i][0] - userB.y)) {
				listB.add(i);
			}
		}
		int max = 0, temp = 0;
		// 두개 다 충전 가능
		if (listA.size() > 0 && listB.size() > 0) {
			for (int i : listA) {
				for (int j : listB) {
					temp = 0;
					if(i == j) {
						temp += bc[i][3];
					}
					else {
						temp += bc[i][3];
						temp += bc[j][3];
					}
					if(max < temp) {
						max = temp;
					}
				}
			}
		}
		// a만 충전 가능
		else if (listA.size() > 0) {
			for (int i : listA) {
				if(max < bc[i][3]) {
					max = bc[i][3];
				}
			}
		}
		// b만 충전 가능
		else if (listB.size() > 0) {
			for (int i : listB) {
				if(max < bc[i][3]) {
					max = bc[i][3];
				}
			}
		}
		total += max;
	}

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			init();
			solve();
			sb.append("\n");
		}
		System.out.println(sb);

	}

}
