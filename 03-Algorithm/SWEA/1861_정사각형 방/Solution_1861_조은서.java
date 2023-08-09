package algo_0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861_조은서 {
	
	// 좌, 상, 우, 하
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	
	static int[][] map;
	
	static int n;
	static int max;
	static int room;
	
	static int[] answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=test_case; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			answer = new int[n*n+1];
			for(int i=0; i<n; i++) { // 방 번호 입력
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					dfs(i,j,1,map[i][j]); // 첫번째 방부터 dfs 수행
				}
			}
			

			max = -1;
			room = -1;
			for(int i=n*n; i >= 0; i--) { // 이동할 수 있는 방의 개수가 여러개일 때 적힌 수가 가장 작은 것 찾기
				int tmpMax = answer[i];
				if(max <= tmpMax) {
					max = tmpMax;
					room = i;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(room).append(" ").append(max).append("\n");

		}
		System.out.println(sb);
		
	}
	
	private static void dfs(int x, int y, int cnt, int start) {
		int cur = map[x][y]; // 현재 방 번호
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i]; // 좌, 상, 우, 하 순서로 이동
			int ny = y + dy[i];
			if(nx < 0 || nx >= n || ny <0 || ny >= n) { // 벽을 만나면 skip
				continue;
			}
			int nxt = map[nx][ny]; // 옆 방의 번호
			
			if(nxt - cur == 1) { // 옆 방과 현재 방 번호 차이가 1이면
				dfs(nx, ny, cnt+1, start); // 옆 방에서 다시 dfs 수행, 이동한 방(=cnt) 증가
			}
		}
		
		if(cnt > answer[start]) {
			answer[start] = cnt;
		}
		

	}	
}
