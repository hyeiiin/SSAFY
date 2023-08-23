import java.io.*;
import java.util.*;

public class Solution_3124_김현영 {
	
	static int[] parents;
	
	// 해당 원소가 속한 집합의 대표자 원소 찾기
	static int find(int a) {
		if(a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}
	
	// 두 원소가 속한 집합을 a집합 쪽으로 합치기
	static boolean union (int a, int b) {
		int pA = find(a);
		int pB = find(b);
		if(pA == pB) return false;
		parents[pB] = pA;
		return true;
	}
	
	// 간선 리스트에서 사용하는 간선정보(시작정점, 끝정점, 가중치)
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight-o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());	// 정점 개수
			int e = Integer.parseInt(st.nextToken());	// 간선 개수
		
			// 간선 입력 받기
			Edge[] edge = new Edge[e];			
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				edge[i] = new Edge(a,b,c);
			}
			//가중치 기준 오름차순 정렬
			Arrays.sort(edge);
			
			//서로소 집합의 대표자 지정
			parents = new int[v];
			for (int i = 0; i < v; i++) {
				parents[i] = i;
			}
			
			long result = 0;	//가중치 합
			int count = 0;	//최소신장트리를 만드는 과정에서 간선의 수 
			for (Edge x : edge) {
				// 두 정점을 합칠 수 있으면(사이클이 되지 않으면) 가중치 추가
				if(union(x.from, x.to)) {
					result+= x.weight;
					count++;
				}

				// 간선의 수가 노드-1이 되면 종료
				if(count == v-1)	
					break;
			}
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb.toString());
		
		
	
	
	}
}
