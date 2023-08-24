package algo_0824;

import java.util.*;
import java.io.*;

public class Solution_1251_조은서 {

	static public class Island { // 섬의 좌표 저장
		int x, y;
		
		public Island(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static public class Vertex implements Comparable<Vertex> { // 노드 정보(번호, 가중치) 저장

		int no;
		double weight;
		
		public Vertex(int no, double weight) {
			super();
			this.no = no;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Double.compare(this.weight, o.weight);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine()); // x 좌표 입력 받기
			StringTokenizer st1 = new StringTokenizer(br.readLine()); // y 좌표 입력 받기
			
			ArrayList<Island> islands = new ArrayList<>();
			for(int i=0; i<N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st1.nextToken());
				islands.add(new Island(x, y));
			}
			
			double E = Double.parseDouble(br.readLine());
			
			// 그래프 => 인접행렬로 저장
			double[][] graph = new double[N][N];
			for(int i=0; i<N; i++) {
				int x1 = islands.get(i).x; // 출발 섬의 x, y 좌표
				int y1 = islands.get(i).y;
				for(int j=0; j<N; j++) {
					int x2 = islands.get(j).x; // 도착 섬의 x, y 좌표
					int y2 = islands.get(j).y;
					graph[i][j] = E * calcWeight(x1,x2,y1,y2); // 가중치(=거리) 저장
				}
			}
			
			// 프림 알고리즘
			double result = Long.MAX_VALUE;
			double[] minEdge = new double[N];
			boolean[] visited = new boolean[N];
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			
			Arrays.fill(minEdge, Long.MAX_VALUE);
			minEdge[0] = 0;
			pq.offer(new Vertex(0, minEdge[0]));
			
			double cost = 0, min =0;
			int minVertex = 0, count =0;
			
			while(!pq.isEmpty()) {
				Vertex cur = pq.poll();
				minVertex = cur.no;
				min = cur.weight;
				
				if(visited[minVertex]) continue;
				visited[minVertex] = true;
				cost += min;
				
				if(++count == N) break;
				
				for(int i=0; i<N; i++) {
					if(!visited[i] && graph[minVertex][i] != 0 && minEdge[i] > graph[minVertex][i]) {
						minEdge[i] = graph[minVertex][i];
						pq.offer(new Vertex(i, minEdge[i]));
					}
				}
			}
			result = Math.min(result, cost);
			
			System.out.println("#"+tc+" "+Math.round(result));
		}
	}

	static double calcWeight(int x1, int x2, int y1, int y2) { // 거리 계산
		return Math.pow(Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)),2);
	}
}
