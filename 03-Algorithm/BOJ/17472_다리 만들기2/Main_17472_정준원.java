package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_17472_정준원 {
	static boolean[][] visit;
	static int[][] arr;
	static int[] parent;
	static ArrayList<node>[] list = new ArrayList[7];
	static PriorityQueue<point> pq = new PriorityQueue<>();
	static int M;
	static int N;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static class point implements Comparable<point> { // point 오름차순
		int r, c, cost, from, to;

		public point(int r, int c, int cost, int from, int to) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
			this.from = from;
			this.to = to;
		}

		@Override
		public String toString() {
			return "point [r=" + r + ", c=" + c + ", cost=" + cost + ", from=" + from + ", to=" + to + "]";
		}

		@Override
		public int compareTo(point o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}

	}

	static class node {
		int r, c;

		public node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {

		// n-1개 다리가 있어야 연결된다.
		// 다리 길이는 2이상이어야 한다.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		System.out.println("after n m");
		arr = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		System.out.println("after arr");

		for (int i = 0; i < 7; i++) {

//            System.out.println(" ");
			list[i] = new ArrayList<>();
		}

		int cnt = 1;
		System.out.println("before bfs");

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && arr[i][j] == 1) {
					bfs(i, j, N, M, cnt);
					cnt++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print("arrrrrr" + arr[i][j]);
			}
			System.out.println();
		}

		parent = new int[cnt + 1];

		for (int i = 1; i <= cnt; i++) {
			parent[i] = i;
		}

		System.out.println("cnt" + cnt);

		for (int i = 1; i < cnt; i++) {
			System.out.println("listsize " + i + " " + list[i].size());
			for (int j = 0; j < list[i].size(); j++) {
				node n = list[i].get(j);
				for (int k = 0; k < 4; k++) {
					int r = n.r;
					int c = n.c;
					connect(k, 0, r, c, i);
				}
			}
		}

		System.out.println("beforekr");
		kruskal();
	}

	static void connect(int dir, int len, int r, int c, int num) {
//		System.out.println("in connect");

		System.out.println("arr" + arr[r][c] + " " + "len" + len + " r c" + r + " " + c + " origin" + num);

		if (arr[r][c] != num && arr[r][c] != 0 && len >= 2) {
			System.out.println("add");
			pq.add(new point(r, c, len, num, arr[r][c]));
			return;
		}

		int nr = r + dr[dir];
		int nc = c + dc[dir];

//		System.out.println("nr nc " + nr + " " + nc);

		if (isvalid(nr, nc)) {
			if (arr[nr][nc] != num) { // 본인 숫자가 아니고 거리가 2이상이면 넣는다. + 0 이 아닌경우도 포함.
				connect(dir, len + 1, nc, nc, num);
			}
		}

	}

	static void kruskal() {
		System.out.println("inkruskal");
		int size = pq.size();
		int sum = 0;
		System.out.println("pqsize" + pq.size());
		for (int i = 0; i < size; i++) {
			point p = pq.poll();

			System.out.println("pq" + p.toString());
		}

//		for (int i = 0; i < size; i++) {
//			point p=pq.poll();
//			
//			if( ) {
//				union(a, b);
//				sum+= p.cost;
//			}
//		}

	}

	static int find(int a) {
		if (parent[a] == a) {
			return a;
		} else {
			return parent[a] = find(a);
		}
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {

		} else {
			parent[a] = b;
		}
	}

	static void bfs(int r, int c, int N, int M, int num) { // 덩어리 탐색.
		int[] dr = { 1, -1, 0, 0 };
		int[] dc = { 0, 0, 1, -1 };
		System.out.println("in bfs");

		Queue<point> q = new LinkedList<>();
		q.add(new point(r, c, num, 0, 0));

		while (!q.isEmpty()) {
			point p = q.poll();

			for (int i = 0; i < 4; i++) {

				int nr = p.r + dr[i];
				int nc = p.c + dc[i];

				if (isvalid(nr, nc) && !visit[nr][nc] && arr[nr][nc] == 1) {
					visit[nr][nc] = true;
					q.add(new point(nr, nc, num, 0, 0));
					list[num].add(new node(nr, nc));
					arr[nr][nc] = num;
				}
			}
		}

	}

	private static boolean isvalid(int r, int c) {
		// TODO Auto-generated method stub
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
