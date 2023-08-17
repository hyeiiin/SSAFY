package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5644_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, M, A, arrA[], arrB[], ans;
	static PriorityQueue<Integer> a, b;
	static batteryCharger bc[];
	static Point startA, startB;
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };

	static class batteryCharger {
		Point loc;
		int c, p;

		public batteryCharger(Point loc, int c, int p) {
			super();
			this.loc = loc;
			this.c = c;
			this.p = p;
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void choice(int i) {
		int firstSum = 0, secondSum = 0;
		int firstIndex = -1, secondIndex = -2;
		if (!a.isEmpty()) {
			firstIndex = a.poll();
			firstSum = bc[firstIndex].p;
		}
		if (!b.isEmpty()) {
			secondIndex = b.poll();
			secondSum = bc[secondIndex].p;
		}
		if (firstIndex == secondIndex) {
			if (!a.isEmpty() && !b.isEmpty()) {
				if (a.peek() < b.peek()) {
					firstSum = bc[a.poll()].p;
				} else {
					secondSum = bc[b.poll()].p;
				}
			} else if (!a.isEmpty()) {
				firstSum = bc[a.poll()].p;
			} else if (!b.isEmpty()) {
				secondSum = bc[b.poll()].p;
			} else {
				firstSum /= 2;
				secondSum /= 2;
			}
		}
		ans += firstSum;
		ans += secondSum;
	}

	public static void solve() {
		Arrays.sort(bc, (o1, o2) -> o2.p - o1.p);
		Point curA = startA;
		Point curB = startB;
		for (int i = 0; i <= M; i++) {
			curA = new Point(curA.x + dx[arrA[i]], curA.y + dy[arrA[i]]);
			curB = new Point(curB.x + dx[arrB[i]], curB.y + dy[arrB[i]]);
			a = new PriorityQueue<>();
			b = new PriorityQueue<>();
			for (int k = 0; k < A; k++) {
				if (isChargeDistance(curA, bc[k])) {
					a.offer(k);
				}
				if (isChargeDistance(curB, bc[k])) {
					b.offer(k);
				}
			}
			choice(i);
		}
	}

	public static int manhattan(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	public static boolean isChargeDistance(Point p1, batteryCharger bc) {
		if (manhattan(p1, bc.loc) <= bc.c)
			return true;
		return false;
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		startA = new Point(1, 1);
		startB = new Point(10, 10);
		arrA = new int[M + 1];
		arrB = new int[M + 1];
		ans = 0;
		bc = new batteryCharger[A];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			bc[i] = new batteryCharger(new Point(x, y), c, p);
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solve();
			sb.append("#" + t + " " + ans + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
