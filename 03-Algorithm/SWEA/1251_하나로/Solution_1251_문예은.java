import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251_문예은 {
	static class Vertex implements Comparable<Vertex>{
		int no;
		double weight; // 정점 번호, 트리정점과 연결 시 간선 비용
		public Vertex(int no, double weight) {
			this.no = no;
			this.weight = weight;
		}
		@Override
		public int compareTo(Vertex o) { // 간선 비용으로 오름차 정렬
			return Double.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1;
		StringTokenizer st2;
		StringBuilder sb = new StringBuilder();
		/*
		 * 1부터 N개까지 섬의 정점 만들기
		 * 모든 섬들끼리 인접해있다고 가정, weight는 adj넣을때 바로 세율 계산(세율*(가로길이차제곱+세로길이차제곱))
		 */
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			int V = Integer.parseInt(br.readLine());
			double[][] adjMatrix = new double[V][V];
			
			double[][] vPoint = new double[V][2]; // 정점 좌표 배열
			st1 = new StringTokenizer(br.readLine()); // 정점들의 x좌표
			st2 = new StringTokenizer(br.readLine()); // 정점들의 y좌표
			for (int i = 0; i < V; i++) {
				double x = Double.parseDouble(st1.nextToken()); // 각 정점의 xy좌표
				double y = Double.parseDouble(st2.nextToken());
				for (int j = 0; j < vPoint.length; j++) {
					vPoint[i] = new double[] {x/100,y/100}; // 좌표끼리 마이너스 후 제곱할 때 100억넘어서 깨져버림;
				}
			}
			double E = Double.parseDouble(br.readLine()); // 환경 부담금 세율
			
			// weight는 adj넣을때 바로 세율 계산(세율*(가로길이차제곱+세로길이차제곱))
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) { // 가중치 넣기
					if (i==j) continue;
					adjMatrix[i][j] = 10000.0*E*(Math.pow(vPoint[i][0]-vPoint[j][0], 2)+Math.pow(vPoint[i][1]-vPoint[j][1], 2));
				} // 제곱 연산 후 나눠준 1만을 마지막에 다시 곱해줌 
			}
//			System.out.println(Arrays.deepToString(adjMatrix));
			boolean[] visited = new boolean[V]; // 방문정점 (트리 정점 표시)
			double[] minEdge = new double[V]; // 자신과 트리의 정점들 간 최소 간선 비용
			PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
			
			Arrays.fill(minEdge, Double.MAX_VALUE); // 최소값 갱신 위해 큰 값으로 세팅
			minEdge[0] = 0; // 임의로 0 정점을 시작정점으로 세팅
			pQueue.offer(new Vertex(0, minEdge[0]));
			
			double result = 0; // 최소신장트리 비용 저장
			double min = 0;
			int minVertex = 0;
			int cnt = 0; // while문을 위한 변수
			
			while (!pQueue.isEmpty()) { // 큐 사용을 위해 반복문 변경
				// Step 1. 미방문(비트리)정점 중 최소간선비용 정점을 선택
				Vertex cur = pQueue.poll();
				minVertex = cur.no; // 최소비용 정점
				min = cur.weight; // 최소값 temp
				
				if (visited[minVertex]) continue; // 큐 업데이트 과정에서 후순위로 고여있는 정점들은 건너뛰기
				
				// Step 2. 방문(트리) 정점에 추가
				visited[minVertex] = true; // 트리에 추가하는 것이 여기선 방문처리
				result += min; // 신장트리 비용 누적
				
				if(++cnt == V) break; // 방문트리 정점 추가될때마다 증가, 모두 채워지면 종료
				
				// Step 3. 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선비용 고려
				// 영업타임!
				for (int j = 0; j < V; j++) {
					// 비트리이고, 인접해있고, 나와 연결하는 것이 더 유리할 때
					if (!visited[j] && adjMatrix[minVertex][j] != 0 && minEdge[j]>adjMatrix[minVertex][j]) {
						minEdge[j] = adjMatrix[minVertex][j]; // 업데이트 
						pQueue.offer(new Vertex(j, minEdge[j])); // 추가된 코드
					}
				}
			}
			sb.append("#"+t+" "+Math.round(result)+"\n");
		}
		System.out.println(sb.toString());
	}
}
