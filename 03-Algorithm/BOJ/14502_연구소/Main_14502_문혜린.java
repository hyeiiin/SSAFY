package algorithm.baekjoon;

import java.io.*;
import java.util.*;

public class Main_14502_문혜린 {
	static int N, M;
	static int[][] map;
	//상하좌우
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int max = Integer.MIN_VALUE; //안전 영역 크기 최댓값
	
	public static void dfs(int count) { //벽 세운 수 카운트
		if(count == 3) {
			bfs(); //안전 영역 체크
			return;
		}
		
		//벽을 3개 세울 수 있는 모든 경우의 수 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) { //빈칸이라면 벽 세우기
					//벽 세우고 다른 경우 위해 허물기
					map[i][j] = 1;
					dfs(count+1);
					map[i][j] = 0;
				}
			}
		}
	}
	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		
		//초기 바이러스 위치 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==2) {
					q.add(new int[]{i, j});
				}
			}
		}
		
		int[][] mapCopy = map.clone();
		for (int i = 0; i < N; i++) {
			mapCopy[i] = map[i].clone();
		}
		
		//바이러스 퍼지기
		while(!q.isEmpty()) {
			int[] pos = q.remove();
			
			for (int k = 0; k < 4; k++) {
				int nx = pos[0]+dx[k];
				int ny = pos[1]+dy[k];
				//범위 체크
				if(nx<0 ||nx>=N || ny<0 ||ny>=M) {
					continue;
				}
				//벽이거나 바이러스면 패스
				if(mapCopy[nx][ny]==1 || mapCopy[nx][ny]==2) {
					continue;
				}
				//바이러스 퍼짐
				q.add(new int[]{nx, ny});
				mapCopy[nx][ny] = 2;
			}
		}
		
		//안전 영역 체크
		int safeZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(mapCopy[i][j] == 0) {
					safeZone++;
				}
			}
		}
		
		max = Integer.max(max, safeZone); //안전 영역 최댓값 갱신
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //행
		M = Integer.parseInt(st.nextToken()); //열
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(max);
	}

}
