package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251_탁하윤 {
	static class Vertex implements Comparable<Vertex>{
		int no;
		long weight;	// 정점 번호, 트리 정점과 연결했을때의 간선 비용

		public Vertex(int no, long weight) {
			super();
			this.no = no;
			this.weight = weight;	// 각 해저터널 길이 (L^2)
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.weight, o.weight);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스 개수
		
		for (int C = 1; C <= T; C++) {
			int N = Integer.parseInt(br.readLine());
			int[][] island = new int[N][2];	// 섬들의 좌표 저장
			long[][] adj = new long[N][N];	// 인접행렬에 해저 터널 길이(L) 즉, 간선 비용 저장
			
			// 섬들의 x 좌표
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				island[i][0] = Integer.parseInt(st.nextToken());
			}
			// 섬들의 y 좌표
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				island[i][1] = Integer.parseInt(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());	// 환경 부담 세율
			
			for (int i = 0; i < N; i++) {	// 인접행렬 초기화
				for (int j = 0; j < N; j++) {
					if(i==j) continue;
					long LX = Math.abs(island[i][0] - island[j][0]);	// x거리
					long LY = Math.abs(island[i][1] - island[j][1]);	// y거리
					adj[i][j] = LX*LX + LY*LY;	// L^2을 인접행렬에 저장
				}
			}
			
			boolean[] visited = new boolean[N];	// 방문 정점(트리정점표시)
			long[] minEdge = new long[N];	// 자신과 트리(섬)의 정점 간 최소 간선 비용
			PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
			
			Arrays.fill(minEdge, Long.MAX_VALUE);	// 최소값 갱신 위해 큰 값으로 세팅
			minEdge[0] = 0;	// 임의의 0 정점을 트리 구성의 시작으로 하기 위해 세팅
			pQueue.offer(new Vertex(0, minEdge[0]));
			
			long result = 0;	// 최소 신장 트리 비용
			long min = 0;
			int minVertex = 0, cnt = 0;
			
			while(!pQueue.isEmpty()) {
				// step1 : 미방문(비트리) 정점 중 최소간선비용의 정점을 선택
				Vertex cur = pQueue.poll();
				minVertex = cur.no;	// 현재 정점 번호 넣기
				min = cur.weight;	// 현재 간선 비용 (거리)
				if (visited[minVertex]) continue;	// 먼저 들어가 있었던것, 무시함
				
				// step2 : 방문(트리) 정점에 추가
				visited[minVertex] = true;	// 방문 처리
				result += min;	// 신장트리 비용 누적
				if(++cnt == N) break;
				
				// step3 : 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선비용 고려
				for (int j = 0; j < N; j++) {
					if(!visited[j] && adj[minVertex][j] != 0 && minEdge[j] > adj[minVertex][j]) { // 최소 거리보다 더 작다면 갱신
						minEdge[j] = adj[minVertex][j];
						pQueue.offer(new Vertex(j, minEdge[j]));
					}	
				}
			}
			
			System.out.printf("#%d %d\n", C, Math.round(E*result));
		}

	}

}
