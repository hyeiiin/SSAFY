package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3124_탁하윤 {
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {	// 가중치 오름차순
			return this.weight - o.weight;
		}		
	}
	
	static int V, E, parents[];
	static Edge[] edgeList;
	
	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i+1;
		}
	}
	static int find(int a) {
		if(parents[a-1]==a) return a;
		return parents[a-1] = find(parents[a-1]);	// -> 패스 압축 (리프노드에서 효과적)
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);	// 부모 찾기
		int bRoot = find(b);
		if(aRoot == bRoot) return false;	// 부모가 같다면 싸이클 발생의미로 해석
		parents[bRoot-1] = aRoot;	// b부모를 a부모로 바꾸기
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스 수
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());	// 정점의 개수
			E = Integer.parseInt(st.nextToken());	// 간선의 개수
			
			edgeList = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()); // a 정점
				int to = Integer.parseInt(st.nextToken());	// b 정점
				int weight = Integer.parseInt(st.nextToken()); 	// 가중치
				
				edgeList[i] = new Edge(from, to, weight);	// 간선 리스트 만들기
			}
			
			Arrays.sort(edgeList);	// 간선 리스트를 가중치 기준으로 오름차순 정렬
			
			make();	// V개의 정점으로 make set 작업
			
			long result = 0; 	// MST 비용, 더한 값이 int 범위를 넘어갈 수 있기 때문에 long 타입
			int count = 0;	// 연결된 간선 개수
			
			for (Edge edge : edgeList) {	// 비용이 작은 간선순으로 꺼내서 처리
				if(union(edge.from, edge.to)) {	// 싸이클이 발생하지 않은 경우
					result += edge.weight;	// 간선 가중치 누적
					if(count++ == V-1) break;	// 간선 개수만큼 돌았다면 break
				}
			}
			
			System.out.printf("#%d %d\n", tc, result);
		}
	}

}
