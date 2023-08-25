import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 프림
public class Solution_1251_하나로_Prim{

	private static int N;
	private static long[][] adjMatrix;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <=T; t++) {
			N = Integer.parseInt(in.readLine());
			
			int[] x = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}// N개 섬의 x좌표
			
			int[] y = new int[N];
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}// N개 섬의 y좌표
			
			adjMatrix = new long[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjMatrix[i][j] = adjMatrix[j][i] = getDistance(x[i],x[j],y[i],y[j]);
				}
			}// 인접행렬 완성
			
			double E = Double.parseDouble(in.readLine());
			System.out.println("#"+t+" "+ Math.round(E*makeMST()));
		}
	}
	
	private static long makeMST() {
		
		long[] minEdge = new long[N];
		boolean[] visited = new boolean[N];
		
		long result = 0; // 최소신장트리비용
		int cnt = 0; // 처리한 정점수
		
		Arrays.fill(minEdge, Long.MAX_VALUE);
		minEdge[0] = 0;// 0섬을 시작섬으로.
		
		while(true) {
		
			// 1단계 : 신장트리에 포함되지 않은 정점중 최소간선비용의 정점 선택
			long min = Long.MAX_VALUE;
			int minNo = 0; // 최소간선비용의 정점
			
			for (int i = 0; i < N; i++) {
				if(!visited[i] && min> minEdge[i]) {
					min = minEdge[i];
					minNo = i;
				}
			}
			
			visited[minNo] = true; // 정점 방문 처리(신장트리에 포함시킴)
			result += min; // 간선비용 누적
			if(++cnt==N) break;
			
			// 2단계: 선택된 정점에서 신장트리에 포함되지 않은 다른 정점들로의 간선의 비용을 고려하여  minEdge 업데이트
			for (int i = 0; i < N; i++) {
				if(!visited[i] && adjMatrix[minNo][i] > 0 && minEdge[i]> adjMatrix[minNo][i]) {
					minEdge[i] = adjMatrix[minNo][i];
				}
			}
			
		}
		
		return result;
	}

	private static long getDistance(int x1, int x2, int y1, int y2) {
		return (long)(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
	}

}
