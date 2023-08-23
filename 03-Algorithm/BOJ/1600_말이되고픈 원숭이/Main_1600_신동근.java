package 백준;

import java.util.*;
import java.io.*;

public class Main_1600_신동근 {
	
	// 좌표정보를 저장하는 클래스
	static class Position {
		int x;
		int y;
		int distance;	// 원숭이가 갈 수 있는 거리
		int abiltyCount;	// 원숭이가 사용한 능력 횟수
		
		public Position(int x, int y, int distance, int abiltyCount) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.abiltyCount = abiltyCount;
		}
	}

	static int K;	// 능력 수
	static int W;	// 가로 길이 (열)
	static int H;	// 세로 길이 (행)
	static int[][] map;
	static boolean[][][] visited;	// 방문배열 (3차원배열)
	// 8가지 방향 배열
	static int[] eightDx = {2, 1, -1, -2, -2, -1, 1, 2};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] eightDy = {1, 2, 2, 1, -1, -2, -2, -1};	// x축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] fourDx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] fourDy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];	// (0, 0) ~ (W-1, H-1) => [0][0] ~ [H-1][W-1]
		visited = new boolean[K+1][H][W];	// [0] = 능력 사용 안함, [1] ~ [K] = 능력 사용한 횟수
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int resultDistance = bfs(0, 0);	// 처음 시작좌표부터 너비우선탐색해서 도착점까지의 거리 계산
		System.out.println(resultDistance);

	}
	
	// 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0, 0));
		visited[0][startX][startY] = true;
		
		// 큐가 빌 때까지 반복
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			int nowAbilityCount = now.abiltyCount;
			
			// 해당 좌표가 도착점에 도달한 경우
			if(nowX == H-1 && nowY == W-1) {
				return nowDistance;	
			}
			
			// 4가지 방향 먼저 탐색 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
			for(int i=0; i<4; i++) {
				int nextX = nowX + fourDx[i];
				int nextY = nowY + fourDy[i];
				
				// 탐색한 좌표가 [0][0] ~ [H-1][W-1] 이외의 좌표를 탐색한 경우
				if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문한 좌표거나 또는 장애물(1)인 경우
				if(visited[nowAbilityCount][nextX][nextY] || map[nextX][nextY] == 1) {
					continue;	// 넘어감
				}
				
				queue.add(new Position(nextX, nextY, nowDistance+1, nowAbilityCount));	// 큐에 탐색한 좌표정보 및 거리, 사용한 능력수 저장
				visited[nowAbilityCount][nextX][nextY] = true;	// 사용한 능력수에 따른 탐색한 좌표 방문처리
			}
			
			// 사용한 능력수가 K(원숭이가 사용 가능한 능력 수) 미만인 경우
			if(nowAbilityCount < K) {
				// 8가지 방향 탐색
				for(int i=0; i<8; i++) {
					int nextX = nowX + eightDx[i];
					int nextY = nowY + eightDy[i];
					
					// 탐색한 좌표가 [0][0] ~ [H-1][W-1] 이외의 좌표를 탐색한 경우
					if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
						continue;	// 넘어감
					}
					
					// 탐색한 좌표가 이미 방문한 좌표거나 또는 장애물(1)인 경우
					if(visited[nowAbilityCount+1][nextX][nextY] || map[nextX][nextY] == 1) {
						continue;	// 넘어감
					}
					
					queue.add(new Position(nextX, nextY, nowDistance+1, nowAbilityCount+1));	// 큐에 탐색한 좌표정보 및 거리, 사용한 능력수 저장
					visited[nowAbilityCount+1][nextX][nextY] = true;	// 사용한 능력수에 따른 탐색한 좌표 방문처리
				}
			}
		}
		
		// 위의 너비우선탐색 다 했는데도 도착점에 도달 할 수 없는 경우
		return -1;	// -1 반환
	}

}
