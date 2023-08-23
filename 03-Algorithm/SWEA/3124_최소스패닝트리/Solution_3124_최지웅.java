package todo.lesson._0823;

import java.io.*;
import java.util.*;

public class Solution_3124_최지웅 {

	static class Edge implements Comparable<Edge> {
		int from, to;
		int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}
		
		@Override
		public String toString() {
			return "Edge[from=" + from + ",to=" + to + ",weight=" + weight + "]";
		}
	}

	// Graph
	static int V, E;
	static Edge[] edges;

	// Disjoint Set - Union & Find
	static int[] sets;

	static void set(int id) {
		sets[id] = id;
	}

	static void union(int a, int b) {

		int setA = find(a);
		int setB = find(b);

		if (setA == setB)
			return;
		else {
			sets[setA] = setB;
		}
	}

	static int find(int id) {
		if (sets[id] == id)
			return sets[id];
		else
			return sets[id] = find(sets[id]);
	}

	// MST - Kruskal
	static long weightSum;

	static void kruskal() {

		Arrays.sort(edges);
		
		sets = new int[V + 1];
		for (int v = 1; v <= V; v++) {
			set(v);
		}

		weightSum = 0;
		int edgeCount = 0;
		
		Edge edge;
		int from, to;
		int fromSet, toSet;
		
		for (int e = 0; e < E; e++) {
			
			edge = edges[e];
			from = edge.from;
			to = edge.to;
			
			fromSet = find(from);
			toSet = find(to);
			
			if (fromSet != toSet) {
				weightSum += edge.weight;
				union(fromSet, toSet);
				edgeCount++;
			}
			
			if (edgeCount == V - 1) return;
		}
	}

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		int A, B, C;

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edges = new Edge[E];

			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				edges[e] = new Edge(A, B, C);
			}
			
			kruskal();
			sb.append("#" + t + " " + weightSum + "\n");
		}
		
		System.out.println(sb);
	}

}
