package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10026_탁하윤 {
	static int N;
	static char[][] rgb;
	static boolean[][] visited, visited2;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 가로 세로 크기 N
		rgb = new char[N][N];	// N*N 그리드
		int cntR = 0;	// R 구역 개수
		int cntG = 0;	// G 구역 개수
		int cntB = 0;	// B 구역 개수
		int cntRG = 0;	// R-G 구역 개수
		
		for (int i = 0; i < N; i++) {	// N*N 그리드 초기화
			rgb[i] = br.readLine().toCharArray();
		}
		
		visited = new boolean[N][N];	// 각 구역 방문처리
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(rgb[i][j] == 'R' && !visited[i][j]) {	// 구역이 R이고 아직 방문하지 않았다면 호출
					cntR++;	// R 구역 개수 (dfs 호출된 개수)
					dfs(i, j, 0);
				}
				if(rgb[i][j] == 'G' && !visited[i][j]) {	// 구역이 G이고 아직 방문하지 않았다면 호출
					cntG++;	// G 구역 개수 (dfs 호출된 개수)
					dfs(i, j, 1);
				}
				if(rgb[i][j] == 'B' && !visited[i][j]) { 	// 구역이 B이고 아직 방문하지 않았다면 호출
					cntB++;	// B 구역 개수 (dfs 호출된 개수)
					dfs(i, j, 2);
				}
			}
		}
		
		visited2 = new boolean[N][N];	// R-G 구역 방문처리
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) { 
				if((rgb[i][j] == 'R'|| rgb[i][j] == 'G') && !visited2[i][j]) { 	// 구역이 R-G이고 아직 방문하지 않았다면 호출
					cntRG++; // R-G 구역 개수 (dfs 호출된 개수)
					dfs(i, j, 3);
				}
			}
		}
		
		System.out.printf("%d %d\n", cntR+cntG+cntB, cntRG+cntB);

	}

	static void dfs(int x, int y, int mode) {	// x: 현재 구역의 x 좌표, y: 현재 구역의 y좌표, mode: 현재 호출된 구역의 색
		if(mode == 0 || mode == 1 || mode == 2) {	// 호출된 구역의 색이 R, G, B라면 visited로 방문처리		
			visited[x][y] = true;
		} else {	// R-G라면 visited2로 방문처리
			visited2[x][y] = true;
		}
		
		for (int i = 0; i < 4; i++) {	// 4방 탐색
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(mode == 3) {	// R-G 구역이라면
				if(nx>=0 && nx<N && ny>=0 && ny<N && !visited2[nx][ny]) {	// 범위를 벗어나지 않았고, 아직 방문하지 않았다면
					if(rgb[nx][ny] == 'R' || rgb[nx][ny] == 'G') {	// 다음 좌표가 R또는 G 구역이라면
						dfs(nx, ny, 3);	// 다시 호출
					}
				}
			} else {
				if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]) { // 범위를 벗어나지 않았고, 아직 방문하지 않았다면
					if(mode == 0 && rgb[nx][ny] == 'R') { // 다음 좌표가 R구역이라면
						dfs(nx, ny, 0);					
					} else if(mode == 1 && rgb[nx][ny] == 'G') { // 다음 좌표가 G구역이라면
						dfs(nx, ny, 1);					
					} else if(mode == 2 && rgb[nx][ny] == 'B') { // 다음 좌표가 B구역이라면
						dfs(nx, ny, 2);					
					}
				}
			}
			
		}
	}
}
