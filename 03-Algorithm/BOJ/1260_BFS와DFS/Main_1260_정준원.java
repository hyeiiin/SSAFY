package sdf;

import java.io.*;
import java.util.*;

public class Main_1260_정준원 {
	static boolean visit[];
	static int arr[][];
	static ArrayList<Integer>[] list;
	static int n, m, v;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());

		visit = new boolean[n + 1];
//		arr = new int[n + 1][n + 1];
		list = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		int a, b;
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());

			a = Integer.parseInt(st2.nextToken());
			b = Integer.parseInt(st2.nextToken());
//			arr[a][b] = 1;
//			arr[b][a] = 1;

			list[a].add(b);
			list[b].add(a);
		}
		
		for (int i = 1; i <= n; i++) {

			Collections.sort(list[i]);
		}

		dfs(v);
		System.out.println();

		visit = new boolean[n + 1];

		bfs(v);

	}

	static void dfs(int num) {

		visit[num] = true;
		System.out.print(num + " ");

//		for (int i = 1; i <= n; i++) {

		for (int i : list[num]) {

			if (visit[i]) {
				continue;
			}
			dfs(i);
		}

	}

	static void bfs(int num) {

		q.add(num);
		visit[num] = true;

		int v;

		while (!q.isEmpty()) {

			v = q.poll();
			System.out.print(v + " ");

			for (int i : list[v]) {
				if (visit[i])
					continue;

				q.add(i);
				visit[i] = true;
			}

		}

	}

}
