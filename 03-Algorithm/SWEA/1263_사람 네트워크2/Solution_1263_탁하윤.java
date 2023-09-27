package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1263_탁하윤 {
	static final int INF = 1000000;
	static int[][] adj;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());	// TC 개수
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 사람 수
			
			adj = new int[N][N];	// 인접 행렬
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());
					
					// 자기자신이 아니고 인접하지 않다면 INF 해두기
					if(i!=j && adj[i][j]==0) {
						adj[i][j]=INF;
					}
				}
			}
			
			// 플로이드 워샬
			for (int k = 0; k < N; k++) {	// 경유지
				for (int i = 0; i < N; i++) {	// 출발지
					if(i==k) continue;
					for (int j = 0; j < N; j++) {	// 도착지
						if(j==k || j==i) continue;
						// i에서 j까지 경유지(k)를 거치는 것과 최단경로가 중 작은 값을 인접행렬에 갱신한다.
						adj[i][j] = Math.min(adj[i][k]+adj[k][j], adj[i][j]);
					}
				}
			}
			
			// cc 구하기
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += adj[i][j];
				}
				min = Math.min(min, sum);
			}
			
			System.out.printf("#%d %d\n", tc, min);
		}
	}

}
