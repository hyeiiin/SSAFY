package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb
 * @author SSAFY
 *
 */
public class Solution_3124_박정인 {
	static class Edge implements Comparable<Edge> {
		int a, b, weight;

		public Edge(int a, int b, int weight) {
			super();
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int V, E;
	static int[] parents;
	static List<Edge> edges;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edges = new ArrayList<>();
			parents = new int[V + 1];
			long result = 0;	// 조심하자!! 가중치 절대값이 100만 이하라서 int범위를 넘을 수 있다. 
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edges.add(new Edge(a, b, weight));
			}
			
			Collections.sort(edges);
			int cnt = 0;
			for (Edge e : edges) {
				if (find(e.a) != find(e.b)) {
					union(e.a, e.b);
					result += e.weight;
					if (++cnt == V - 1) break;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int find(int x) {
		if (parents[x] == x)	return x;
		return parents[x] = find(parents[x]);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a < b)	parents[b] = a;
		else parents[a] = b;
	}
}
