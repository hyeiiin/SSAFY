import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Solution { // 1251_하나로
	static StringBuilder sb;
	static StringTokenizer st;
	static int TC;
	static int N;  // 섬의 개수
	static double E;  // 환경 부담 세율(실수), ) (0 <= E <= 1)
	static double res;  // 환경 부담금(최종 출력값), 소수 첫째 자리에서 반올림하여 정수 형태로
	static double[][] lst;  // 인접 행렬(섬 간 거리를 가중치로 변환하여 저장)
	static boolean[] visited;  // 방문 처리
	static double[] minEdge;  // 자신과 트리의 정점들 간 최소 간선 비용 저장.
	static List<ArrayList<Integer>> island;  // 섬의 좌표를 모아둠.
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc < TC + 1; tc++) {  
			// 섬의 개수(0 < N <= 1000)
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); 
			
			island = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				island.add(new ArrayList<>());
			}
			
			// 각 섬들의 정수인 X 좌표 (0 <= X <= 100_0000)
			st = new StringTokenizer(br.readLine());
			for (int idx = 0; idx < N; idx++) {
				int X = Integer.parseInt(st.nextToken());
				
				island.get(idx).add(X);
			}
			
			// 각 섬들의 정수인 Y좌표 (0 <= Y <= 100_0000)
			st = new StringTokenizer(br.readLine());
			for (int idx = 0; idx < N; idx++) {
				int Y = Integer.parseInt(st.nextToken());
				island.get(idx).add(Y);
			}
			
			// 세율 (0 <= E <= 1)
			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());
			
			lst = new double[N][N];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (row == col) {
						continue;
					}
					lst[row][col] = tax(row, col);
				}
			}
			
			// PRIM 알고리즘
			prim();
			
			// 최종값을 소수 첫째 자리에서 반올림하여 정수형태로 출력. 
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(Math.round(res));
			System.out.println(sb);
		
			

		} // TC

	} // Main

	/**
	 * PRIM 알고리즘으로 MST를 구하는 함수
	 */
	static void prim() {
		visited = new boolean[N];  // 방문 처리
		minEdge = new double[N];  // 최소 간선 비용을 저장
		// 최솟값을 찾기 위해 초기값을 높게 설정
		Arrays.fill(minEdge, Double.MAX_VALUE); 
		
		// 0번째를 임의로 MST 구성의 시작으로 설정
		minEdge[0] = 0;
		
		// 최종 MST 비용
		res = 0;
		
		for (int i = 0; i < N; i++) {  
			double min = Double.MAX_VALUE;
			int minVertex = -1;
			
			// 1. 미방문 정점 중 최소 간선 비용의 정점을 선택
			for (int idx = 0; idx < N; idx++) {
				if (!visited[idx] && min > minEdge[idx]) {
					minVertex = idx;
					min = minEdge[idx];
				}
			}  // for_2
			
			// 2. 방문 처리 및 그 때의 MST 비용 누적 합산
			visited[minVertex] = true;
			res += min;
			
			// 3. 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선 비용 조사
			for (int idx = 0; idx < N; idx++) {
				// 남은 비트리 정점 + 인접한 정점 + 더 작은 최소 비용이면,
				if (!visited[idx] && lst[minVertex][idx] != 0.0 && lst[minVertex][idx] < minEdge[idx]) {
					// 최소 비용 갱신
					minEdge[idx] = lst[minVertex][idx];
				}
			}  // for_3
		}  // for_1
		
		
	}
	
	/**
	 * 유클리드 거리 공식으로 섬 간 해저터널의 길이를 구하고,
	 * 환경 부담금을 도출하는 함수
	 * @param from  // 출발 섬
	 * @param to  // 도착 섬
	 * @return  // 환경 부담금
	 */
	static double tax(int from, int to) {
		double L = 0.0d;  // 해저 터널의 길이
		int x1, x2, y1, y2;
		
		// 두 섬의 (x, y) 좌표
		x1 = island.get(from).get(0);
		x2 = island.get(to).get(0);
		y1 = island.get(from).get(1);
		y2 = island.get(to).get(1);
		
		// 맨해튼 거리
//		L = Math.abs(island.get(from).get(0) - island.get(to).get(0))
//				+ Math.abs(island.get(from).get(1) - island.get(to).get(1));
		
		// 유클리드 거리 = 문제에서 좌표만 주어지고 거리를 구하라고 했으므로.
		L = Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 -y2), 2));
		
		// 환경 부담금 = (세율 * 해저터널 길이^2) = E * L^2
		return E * (L * L);
	}

}
