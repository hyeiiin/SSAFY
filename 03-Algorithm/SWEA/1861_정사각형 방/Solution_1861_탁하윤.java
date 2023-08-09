package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861_탁하윤 {
	static int N, num;	// 방 개수, 방 번호
	static int max; // 최대 방 이동 개수
	static int[][] A;	// 여러개의 방 번호 
	
	// 방 이동 가능 범위 (상 하 좌 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			A = new int[N][N];
            num = 0;
            max = 0;
			
			// 방 숫자
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 모든 방에서 탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 현재 방은 사용 가능하므로 cnt 값 1부터 시작
					dfs(i, j, 1);
				}
			}
			
			System.out.printf("#%d %d %d\n", tc, num, max);
		}

	}
	
	private static int dfs(int x, int y, int cnt) {
		// 현재 방문한 방에서부터 사방 탐색
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			// 방 배열 범위를 넘어간다면 
			if(nx < 0 || ny < 0 || nx >= N || ny >=N) {
				continue;
			} 
			// 현재 방 번호에서 1 더한값이 다음 방이 아니라면
			if(A[x][y]+1 != A[nx][ny]){
				continue;
			}
			
			// 시작점에서 호출된 방의 최대 이동 수
			cnt = dfs(nx, ny, cnt+1);
			
			if(cnt > max) {	// 최대 이동 수가 max값보다 많다면 max는 현재 최대 이동수, 방 번호는 현재 시작점
				max = cnt;
				num = A[x][y];
			} else if(max == cnt && num > A[x][y]) {	// 만약 max값이 같고 현재 방 번호가 max의 방 번호보다 작다면 현재 방 번호로 교체
				num = A[x][y];
			}
		}
		
		return cnt;
	}
}
