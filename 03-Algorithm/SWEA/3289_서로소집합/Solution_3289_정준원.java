package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_3289_정준원 {

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			System.out.println("start");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			StringBuilder sb = new StringBuilder();

			parent = new int[n + 1];
			sb.append("#" + t + " ");
			//System.out.println("mid");

			for (int i = 1; i <= n; i++) {
				parent[i] = i;
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int num;

				num = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (num == 0) {
					union(a, b);

				}

				else {
					if (check(a, b)) {
						sb.append("1");
					} else {
						sb.append("0");
					}
				}

			}

			System.out.println(sb.toString());

		}

	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
//		System.out.println("find");
		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		int roota = find(a);
		int rootb = find(b);
		//System.out.println("root a b" + roota + " " + rootb);

		if (roota == rootb) {
			return;
		}

		else {
			parent[roota] = rootb;
			return;
		}

	}

	static boolean check(int a, int b) {
		int roota = find(a);
		int rootb = find(b);

		if (roota == rootb) {
			return true;
		}

		return false;

	}
}