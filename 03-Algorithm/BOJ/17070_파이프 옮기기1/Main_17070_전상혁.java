package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_전상혁 {
	static int N;
	static int map[][];
	static int res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		//밀수 있는 방향: 우, 우하, 하
		//회전: 45도
		//빈칸: 0, 벽: 1
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = 0;
		//파이프 끝 좌표 map[0,1] 에서 시작
		move(0, 1, 0);
		
		sb.append(res);
		System.out.println(sb);
	}
	//벽을 피해 N,N까지 이동시키는 경우의 수를 구해야함
	private static void move(int r, int c, int d) {
		// 파이프의 오른쪽 끝 좌표가 N,N으로 이동시키면 되기에 끝좌표를 갖고 탐색
		/* 파이프의 방향이 가로방향: 우, 우하 이동 가능
		 * 세로방향: 하, 우하 이동 가능
		 * 대각방향: 우, 하, 우하 이동 가능
		 */
		if (r==N-1 && c==N-1) { //파이프 끝 좌표가 map[n-1][n-1] 에 도달했다면 종료
			res++;
			return;
		}
		
		switch(d) {
		case 0:
			//가로방향: 우, 우하 이동시 범위 벗어나지 않고 벽이 아닌 경우
			if (c+1<N && map[r][c+1]==0) { 
				move(r, c+1, 0);
			}
			break;
			
		case 1:
			//세로방향: 하, 우하 이동시 범위 벗어나지 않고 벽이 아닌 경우
			if (r+1<N && map[r+1][c]==0) {
				move(r+1, c, 1);
			}
			break;
		case 2:
			//대각방향: 우, 하, 우하 이동 가능
			//하
			if (r+1<N && map[r+1][c]==0) { 
				move(r+1, c, 1);
			}
			//우
			if (c+1<N && map[r][c+1]==0) {
				move(r, c+1, 0);
			}
			break;
		}
		//가로, 세로, 대각 방향 -> 우하로 모두 이동 가능
		//벽이 아니고 범위에서 벗어나지 않았다면 가능
		if (iscross(r, c)) {
			move(r+1, c+1, 2);
		}
		

	}
	private static boolean iscross(int r, int c) {
		return r+1<N && c+1<N && map[r][c+1]==0 && map[r+1][c]==0 && map[r+1][c+1]==0;

	}

}
