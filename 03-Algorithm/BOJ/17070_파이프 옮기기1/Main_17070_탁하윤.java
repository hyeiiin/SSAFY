package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_탁하윤 {
	static int N, cnt;
	static boolean map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new boolean[N+1][N+1];	// 1, 1 부터 시작
		cnt = 0;	// 방법의 수
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if(st.nextToken().equals("0")) {	// 갈수 있는 곳만 true 처리
					map[i][j] = true;
				}
			}
		}
		
		dfs(1, 2, 0);	// 파이프 이동 시작 위치 (1, 2) 방향 가로
		System.out.println(cnt);
	}
	
	private static void dfs(int i, int j, int d) {
		if(i == N && j == N) {	// 목적지 도착 cnt 증가
			cnt++;
			return;
		}
		
		if(d == 0) {	// 가로 방향 (가로는 가로, 대각선만 이동 가능)
			if(j+1<=N && map[i][j+1]) dfs(i, j+1, 0);
		}
		else if(d == 1) {	// 세로 방향 (세로는 세로, 대각선만 이동 가능)
			if(i+1<=N && map[i+1][j]) dfs(i+1, j, 1);
		}
		else if(d == 2) {	// 대각선방향 (대각선은 가로, 세로, 대각선 이동 가능)
			if(j+1<=N && map[i][j+1]) dfs(i, j+1, 0);
			if(i+1<=N && map[i+1][j]) dfs(i+1, j, 1);
		}
		// 대각선 방향은 모두 다 검사
		if(i+1<=N && j+1<=N && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) dfs(i+1, j+1, 2);
	}

}
