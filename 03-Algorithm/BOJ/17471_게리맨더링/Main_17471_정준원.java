package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17471_정준원 {

	static int N = 0;
	static int M = 0;
	static int K = 0;
	static int max = 0;
	static int res = Integer.MAX_VALUE;
	static boolean[] visit;
//	static int[][] arr;
	static int[] dr = { 1, 0, -1, 0 }; // 아 오 위 왼
	static int[] dc = { 0, 1, 0, -1 };
	static int A[][];
	static int tot = 0;
	static int[] population;
	static int[] parent;
	static ArrayList<Integer>[] nodes;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		population = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			tot += population[i];
		}

		nodes = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}

		parent = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());

			for (int j = 0; j < num; j++) {
				int a = Integer.parseInt(st.nextToken());
				nodes[i].add(a);
				nodes[a].add(i);
//				parent[a] = i;
				union(a, i);
			}
		}

		System.out.println(Arrays.toString(parent));
		System.out.println("sss");

		int[] jo = new int[N + 1];
		solve(1, jo);

		System.out.println(res);

	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);

	}

	static void union(int a, int b) {
		int parenta = parent[a];
		int parentb = parent[b];

		if (parenta == parentb) {
//			return false;
		}

		parent[parenta] = parentb;
	}

	static void solve(int depth, int[] jo) {

		if (depth == N + 1) {
			visit = new boolean[N + 1];

			int cnt = 0;
			for (int i = 1; i < N + 1; i++) {
				bfs(jo, i);
				cnt++;
			}

			int v = 0;
			System.out.println("cnt " + cnt);

			if (cnt == 2) {
				System.out.println("fin " + getcnt(jo));
				v = getcnt(jo);
			}

			if (res == Integer.MIN_VALUE)
				res = -1;
			else
				res = Math.min(res, v);

			return;
		}

		for (int i = 1; i <= 2; i++) {
			jo[depth] = i;
			solve(depth + 1, jo);

		}

	}

	static void bfs(int[] jo, int v) {

		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		visit[v] = true;

		while (!q.isEmpty()) {
			int s = q.poll();
			for (int i : nodes[s]) {
				if (!visit[i] && jo[v] == jo[i]) {
					q.add(i);
					visit[i] = true;
				}
			}
		}
	}

	static int getcnt(int[] jo) {
		int suma = 0;
		int sumb = 0;

		for (int i = 1; i < jo.length; i++) {
			if (jo[i] == 1) {
				suma += population[i];
			}
			if (jo[i] == 2) {
				sumb += population[i];
			}

		}

		sumb = tot - suma;
		return Math.abs(suma - sumb);
	}

}
