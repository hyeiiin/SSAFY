package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1247_전상혁 {
	static int[][] lst;
	static int N, min_val, min_res;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			//회사의 좌표, 집의 좌표, N명의 고객의 좌표 -> (N+2)*2개 입력
			lst = new int[N+2][2];
			visited = new boolean[N+2]; //인덱스 2번부터 시작하기 위해
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N+2; i++) {
				lst[i][0] = Integer.parseInt(st.nextToken());
				lst[i][1] = Integer.parseInt(st.nextToken());
			}


			min_res = Integer.MAX_VALUE;
			dfs(0,0,0);
			System.out.printf("#%d %d%n", tc, min_res);
			
		}
	}
	private static void dfs(int r, int count, int res) {
		
		//count = 고객의 집 갯수 N
		if (count==N) {
			//고객의 집을 다 방문했으므로, 마지막으로 방문한 고객의 집에서 집까지의 거리를 구해 추가로 저장
			res += Math.abs(lst[1][0] - lst[r][0])+ Math.abs(lst[1][1] - lst[r][1]);
			min_res = Math.min(res, min_res);

			return;
		}

		
		for (int i = 2; i < N+2; i++) {
			//두 위치 사이 거리 = |x1-x2| + |y1-y2|
			//회사와 가장 가까운 고객의 집 찾기
			if (!visited[i]) { //방문한 고객의 집이 아닐 경우
				visited[i] = true;
				int diff = Math.abs(lst[r][0] - lst[i][0])+ Math.abs(lst[r][1] - lst[i][1]);
				dfs(i, count+1, res+diff);
				visited[i] = false;

			}
		}
		
	}

}
