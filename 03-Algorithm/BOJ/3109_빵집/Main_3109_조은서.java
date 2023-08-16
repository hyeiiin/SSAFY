package algo_0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_조은서 {
	
	static int R, C;
	
	// 이동 방향: 좌상, 우, 우상
	static int dx[] = {-1, 0, 1};
	static int dy[] = {1, 1, 1};
	
	static char[][] map;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			char[] inputArr = input.toCharArray();
			for(int j=0; j<C; j++) {
				map[i][j] = inputArr[j];
			}
		}
		
		for(int i=0; i<R; i++) {
			if(dfs(i,0)) cnt++; // 행마다 파이프라인 연결 가능한지 확인 후, 가능하면 횟수 증가
		}
		System.out.println(cnt);

	}
	
	private static boolean dfs(int x, int y) {
		for(int i=0; i<3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx <0 || nx > R-1 || ny < 0 || ny > C-1) continue; // 범위를 벗어나면 skip
			if(map[nx][ny] == '.') { // 이동할 수 있는 경우
				if(ny == C-1) { // 빵집 앞 열까지 왔다면 파이프라인이 연결되었으므로
					return true; //재귀 중단
				}
				map[nx][ny] = 'x'; // 파이프라인 겹칠 수 없으므로 방문 체크
				
			if(dfs(nx,ny)) return true; // 파이프라인 놓을 수 있을 때까지 재귀 호출
			}
		}
		return false;
	}

}
