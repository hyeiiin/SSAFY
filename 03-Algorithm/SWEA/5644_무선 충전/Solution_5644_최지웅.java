package todo.lesson._0817;

import java.io.*;
import java.util.*;

public class Solution_5644_최지웅 {

	static int T, N;
	static int[] A, B;
	static BC[] bcArr;

	static final int STAY = 0;
	static final int UP = 1;
	static final int RIGHT = 2;
	static final int DOWN = 3;
	static final int LEFT = 4;

	static int ax, ay;
	static int bx, by;

	static int charge;

	static class BC implements Comparable<BC> {
		int N;
		int X, Y;
		int C;
		int P;

		BC(int N, int X, int Y, int C, int P) {
			this.N = N;
			this.X = X;
			this.Y = Y;
			this.C = C;
			this.P = P;
		}

		BC(BC bc) {
			this.N = bc.N;
			this.X = bc.X;
			this.Y = bc.Y;
			this.C = bc.C;
			this.P = bc.P;
		}

		@Override
		public int compareTo(BC o) {
			return o.P - this.P;
		}

		@Override
		public String toString() {
			return "BC [N=" + N + ", X=" + X + ", Y=" + Y + ", C=" + C + ", P=" + P + "]";
		}
	}

	static List<BC> aList;
	static List<BC> bList;

	static boolean canCharge(BC bc, int x, int y) {

		int dist = Math.abs(bc.X - x) + Math.abs(bc.Y - y);
		return dist <= bc.C;
	}

	static void setBC() {

		aList = new ArrayList<>();
		bList = new ArrayList<>();

		for (int n = 0; n < N; n++) {
			if (canCharge(bcArr[n], ax, ay)) {
				aList.add(new BC(bcArr[n]));
			}
			if (canCharge(bcArr[n], bx, by)) {
				bList.add(new BC(bcArr[n]));
			}
		}

		Collections.sort(aList);
		Collections.sort(bList);
	}

	static void charge() {

		int amount = 0;

		if (aList.size() == 0) {
			if (bList.size() > 0) {
				amount += bList.get(0).P;
			}
		} else if (bList.size() == 0) {
			if (aList.size() > 0) {
				amount += aList.get(0).P;
			}
		} else {
			if (aList.get(0).N == bList.get(0).N) {
				amount += aList.get(0).P;
				if (aList.size() >= 2 && bList.size() >= 2) {
					amount += Math.max(aList.get(1).P, bList.get(1).P);
				} else if (aList.size() >= 2) {
					amount += aList.get(1).P;
				} else if (bList.size() >= 2) {
					amount += bList.get(1).P;
				}
			} else {
				amount += aList.get(0).P + bList.get(0).P;
			}
		}

		charge += amount;
	}

	static void moveUser(int t) {
		if (A[t] == UP)
			ay -= 1;
		else if (A[t] == RIGHT)
			ax += 1; //
		else if (A[t] == DOWN)
			ay += 1; //
		else if (A[t] == LEFT)
			ax -= 1; //

		if (B[t] == UP)
			by -= 1;
		else if (B[t] == RIGHT)
			bx += 1;
		else if (B[t] == DOWN)
			by += 1;
		else if (B[t] == LEFT)
			bx -= 1;
	}

	static void simulate(int t) {
		if (t > T) {
			return;
		} else {
			setBC();
			charge();
			if (t < T)
				moveUser(t);
			simulate(t + 1);
		}
	}

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		int X, Y, C, P;

		for (int tc = 1; tc <= TC; tc++) {

			st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			A = new int[T];
			B = new int[T];

			st = new StringTokenizer(br.readLine());
			for (int t = 0; t < T; t++) {
				A[t] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int t = 0; t < T; t++) {
				B[t] = Integer.parseInt(st.nextToken());
			}

			bcArr = new BC[N];

			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				P = Integer.parseInt(st.nextToken());
				bcArr[n] = new BC(n, X, Y, C, P);
			}

			ax = ay = 1;
			bx = by = 10;
			charge = 0;
			simulate(0);
			sb.append(String.format("#%d %d\n", tc, charge));
		}

		System.out.println(sb);
	}

}
