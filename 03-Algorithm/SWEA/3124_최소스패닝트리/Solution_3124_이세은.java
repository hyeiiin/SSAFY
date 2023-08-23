import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.*;

public class Solution_3124_이세은 {

	static Edge[] edgeList;
	static int[] parents;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()); // 정점
			int e = Integer.parseInt(st.nextToken()); // 간선

			edgeList = new Edge[e];
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				// 간선에 대한 정보 리스트에 저장
				edgeList[i] = new Edge(from, to, weight);
			}

			// 최솟값 찾기 위한 가중치 오름차순 정렬
			Arrays.sort(edgeList);

			// make단계, 최소 집합 만들기
			parents = new int[v + 1]; // 각 정점이 속하는 집합의 대표자
			for (int i = 1; i <= v; i++) { // 정점만큼 정점을 하나만 가지는 최소집합 생성
				parents[i] = i;
			}

			long rslt = 0; // 비용
			int cnt = 0; // 연결 간선 개수
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					rslt += edge.weight; // 간선 가중치 더하기
					if (++cnt == v - 1) {
						break;
					}
				}
			}

			System.out.println("#" + test_case + " " + rslt);

		}
	}

	private static boolean union(int from, int to) {
		int fromRoot = find(from);
		int toRoot = find(to);

		if (fromRoot == toRoot)
			return false;

		parents[toRoot] = fromRoot;
		return true;
	}

	private static int find(int from) {
		if (parents[from] == from)
			return from;
		else return parents[from] = find(parents[from]);
	}

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

}
