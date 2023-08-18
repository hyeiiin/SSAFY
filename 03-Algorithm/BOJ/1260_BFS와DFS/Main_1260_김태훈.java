package baekjoon;

import java.util.*;
import java.io.*;

public class Main_1260_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int n, m, v;
	static boolean visit[];
	static ArrayList<Integer>[] list;

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

	}

	static void dfs(int start) {
		visit[start] = true;
		System.out.print(start + " ");
		for (int i : list[start]) {
			if (!visit[i])
				dfs(i);
		}
	}

	static void bfs() {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.offer(v);
		visit[v] = true;
		while(!que.isEmpty()) {
			int current = que.poll();
			System.out.print(current + " ");
			for (int i : list[current]) {
				if(!visit[i]) {					
					que.offer(i);
					visit[i] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for (int i = 1; i <= n; i++) {		
			Collections.sort(list[i]);
		}
		visit = new boolean[n + 1];
		dfs(v);
		System.out.println();
		visit = new boolean[n + 1];
		bfs();
		System.out.println();
	}

}
