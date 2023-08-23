package algo_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3124_조은서 {

	static class Edge implements Comparable<Edge> {
		
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static int[] parents;
	static int V, E;
	static Edge[] edgeList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점 개수
			E = Integer.parseInt(st.nextToken()); // 간선 개수
			
			edgeList = new Edge[E];
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}
			
			Arrays.sort(edgeList);
			
			make();
			
			long result = 0;
			int count = 0;
			for (Edge edge : edgeList) {
				if(union(edge.from, edge.to)) {
					result += edge.weight;
					if(++count == V-1) break;
				}
			}
			
			System.out.println("#"+tc+" "+result);
		}

	}
	
	private static void make() {
		parents = new int[V+1];
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(parents[a] == a ) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

}
