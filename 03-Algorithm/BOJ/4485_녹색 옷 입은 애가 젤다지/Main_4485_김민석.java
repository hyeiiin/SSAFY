package BOJ;

import java.util.*;
import java.io.*;

public class Main_4485_김민석 {
	//가로, 세로
	private static int N;
	//초기 배열, 거리 배열
	private static int[][] map, dis;
	//사방탐색
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,1,0,-1};
	
	//좌표 클래스
	static class Point {
		int x, y, len;
		Point(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	
	//내부인지 체크
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
		
	private static void find() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0, map[0][0]));
		dis[0][0] = map[0][0]; 
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				//내부면서 기존에 있던 거리보다 현재 거리 + 다음좌표값이 작으면 진행
				if(isIn(nx,ny) && dis[nx][ny] > cur.len+map[nx][ny]) {
					//거리 갱신
					dis[nx][ny] = cur.len+map[nx][ny];
					queue.offer(new Point(nx, ny, cur.len+map[nx][ny]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while(true) {
			cnt++;
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			map = new int[N][N];
			dis = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dis[i], Integer.MAX_VALUE);
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			find();
			sb.append("Problem ").append(cnt).append(": ").append(dis[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
	}
}
