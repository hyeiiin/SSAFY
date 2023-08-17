package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD
 * @author SSAFY
 *
 */
public class Solution_1247_박정인 {
	static int N, sx, sy, ex, ey, customers[][], min;	
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			customers = new int[N][2];
			visited = new boolean[N];
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());	// 시작점 x (회사 x좌표)
			sy = Integer.parseInt(st.nextToken());	// 시작점 y (회사 y좌표)
			ex = Integer.parseInt(st.nextToken());	// 도착점 x (집 x좌표)
			ey = Integer.parseInt(st.nextToken());	// 도착점 y (집 y좌표)
			
			// 고객 좌표(x, y) >> 인덱스 0: x / 1: y
			for (int i= 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 순열을 이용한 최소값 구하기 
			dfs(sx, sy, 0, 0);						
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int x, int y, int cnt, int distSum) {	// 현재 x 좌표, 현재 y 좌표, 몇번 확인했는지, 거리 총합
		// 가지치기
		if (distSum > min) {	// 거리 총합이 최소값보다 크면 탐색의 의미가 없어서 종료
			return;
		}
		
		if (cnt == N) {	// 모두 확인했으면 최소값 갱신 >> 최소값이 아닌경우는 위에서 제거되었기에 
			distSum += (Math.abs(ex - x) + Math.abs(ey - y));
			min = Math.min(min, distSum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i])	continue;
			
			visited[i] = true;
			int nx = customers[i][0];
			int ny = customers[i][1];
			int dist = Math.abs(x - nx) + Math.abs(y - ny);
			dfs(nx, ny, cnt + 1, distSum + dist);
			visited[i] = false;;
		}
	}		
}
