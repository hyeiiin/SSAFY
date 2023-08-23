package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_3124_정준원 {

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			parent = new int[V + 1];
			sb.append("#" + t + " ");

			// System.out.println("mid");

			for (int i = 1; i <= V; i++) {
				parent[i] = i;
			}
			ArrayList<int[]> edgelist = new ArrayList<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgelist.add(new int[] { a, b, weight });

			}
			sb.append(kruskal(edgelist)).append('\n');
		}

		System.out.println(sb.toString());

	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	static boolean union(int a, int b) {
		int roota = find(a);
		int rootb = find(b);
		// System.out.println("root a b" + roota + " " + rootb);

		if (roota == rootb) {
			return false;
		}

		else {
			parent[roota] = rootb;
			return true;
		}

	}

	static long kruskal(ArrayList<int[]> edgelist) {
		long res = 0;

		Collections.sort(edgelist, Comparator.comparingInt(e -> e[2]));
//		for (int[] v : edgelist)
//			System.out.println("edge" + v[0] + " " + v[1]);

		for (int i = 0; i < edgelist.size(); i++) {
			int[] v = edgelist.get(i);
			int start = v[0];
			int end = v[1];

			if (union(start, end)) {
				res += v[2];

			}
		}

		return res;

	}
}