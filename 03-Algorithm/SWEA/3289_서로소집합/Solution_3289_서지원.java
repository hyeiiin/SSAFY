package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3289_서지원 {
	
	static int n, m;
	static int[] parent;
	
	private static void make() {
		parent = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		if (aParent == bParent) return;
		parent[aParent] = bParent;
	}
	
	private static boolean isUnion(int a, int b) {
		if (find(a) == find(b)) return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			make();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (op == 0) {
					union(a, b);
				} 
				if (op == 1) {
					sb.append(isUnion(a, b) ? 1 : 0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
