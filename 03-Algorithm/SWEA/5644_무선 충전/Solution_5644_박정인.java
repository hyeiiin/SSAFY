package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo
 * 
 * @author SSAFY
 *
 */
public class Solution_5644_박정인 {
	static class BC {
		int x, y, c, p;

		public BC(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BC other = (BC) obj;
			if (c != other.c)
				return false;
			if (p != other.p)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}

	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };
	static int M, A;
	static int[] playerA = new int[2];
	static int[] playerB = new int[2];
	static int moveA[], moveB[];
	static List<BC> chargers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			chargers = new ArrayList<>();

			playerA[0] = playerA[1] = 1;
			playerB[0] = playerB[1] = 10;

			moveA = new int[M + 1];
			moveB = new int[M + 1];

			moveA[0] = 0;
			moveB[0] = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				chargers.add(new BC(x, y, c, p));
			}

			sb.append("#").append(tc).append(" ").append(move()).append("\n");
		}

		System.out.println(sb);
	}

	private static int move() {
		List<BC> aList;
		List<BC> bList; // A, B가 충전할 수 있는 충전기 목록 >> List인 chargers의 인덱스
		int total = 0;

		// A, B 이동
		// A, B가 충전기 범위에 해당하는지 확인
		// A, B 충전
		for (int i = 0; i <= M; i++) {
			// 이동
			playerA[0] += dx[moveA[i]];
			playerA[1] += dy[moveA[i]];
			playerB[0] += dx[moveB[i]];
			playerB[1] += dy[moveB[i]];

			// A, B가 충전할 수 있는 충전기
			aList = getChargers(playerA);
			bList = getChargers(playerB);

			// A, B가 최대 충전량 반영
			total += getMaxPower(aList, bList);
		}

		return total;
	}

	private static int getMaxPower(List<BC> aList, List<BC> bList) {
		int max = Integer.MIN_VALUE;
		int temp = 0;
		
		int aSize = aList.size(), bSize = bList.size();
		
		// A, B 모두 무선충전기 범위가 아닌 경우
		if (aSize == 0 && bSize == 0)	return 0;
		// B만 무선충전기 범위에 해당하는 경우
		else if(aSize == 0)	return getMaxPower(bList);
		// A만 무선충전기 범위에 해당하는 경우
		else if(bSize == 0)	return getMaxPower(aList);
		
		// A, B 모두 무선충전기 범위에 해당하는 경우
		for (BC bcA : aList) {
			for (BC bcB : bList) {
				// A, B 모두 같은 충전기인 경우 각각 2로 나누어서 더하면 결국 총 합계에서는 한개 더하는것과 같다.
				temp = Math.max(bcA.p, bcB.p);

				// A, B가 다른 충전기인 경우 temp에 둘중 큰값을 반영했으니 나머지 하나 반영
				if (!bcA.equals(bcB)) {
					temp += Math.min(bcA.p, bcB.p);
				}
				
				max = Math.max(max, temp);
			}
		}
		
		return max;
	}

	private static int getMaxPower(List<BC> list) {
		int max = Integer.MIN_VALUE;
		for (BC bc : list) {
			max = Math.max(max, bc.p);
		}
		return max;
	}

	private static List<BC> getChargers(int[] pos) {
		List<BC> list = new ArrayList<>();
		for (int i = 0; i < A; i++) {
			BC bc = chargers.get(i);
			if (getDistance(pos[0], pos[1], bc.x, bc.y) <= bc.c) {
				list.add(bc);
			}
		}
		return list;
	}

	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
