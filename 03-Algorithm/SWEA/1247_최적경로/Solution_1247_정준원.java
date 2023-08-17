package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ClientInfoStatus;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1247_정준원 {

	static class point {
		int r, c;

		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int res = Integer.MAX_VALUE;
	static point[] client;
	static boolean[] visit;
	static point house;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			visit = new boolean[n];

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			point company = new point(a, b);
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			house = new point(a, b);

			// System.out.println("house" + house.r + " " + house.c);
			client = new point[n];

			for (int i = 0; i < n; i++) {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				client[i] = new point(a, b);
			}

			res = Integer.MAX_VALUE;
			// System.out.println("before");
			dfs(0, company.r, company.c, 0, n);

			System.out.println("#" + t + " " + res);

		}

	}

	static void dfs(int depth, int r, int c, int sum, int n) {

		if (depth == n) {
			// System.out.println("in");
			sum += Math.abs(r - house.r) + Math.abs(c - house.c);
			// System.out.println("sum" + sum);
			res = Math.min(res, sum);
			return;

		}

		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				visit[i] = true;

				dfs(depth + 1, client[i].r, client[i].c, sum + Math.abs(r - client[i].r) + Math.abs(c - client[i].c),
						n);
				visit[i] = false;

			}
		}

	}

}