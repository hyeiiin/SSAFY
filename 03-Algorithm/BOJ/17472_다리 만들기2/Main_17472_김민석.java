package BOJ;

import java.util.*;
import java.io.*;

public class Main_17472_김민석 {
	//가로, 세로, 지역넘버, 정답
	private static int N,M, areaNum=1, answer;
	//초기 배열
	private static int[][] map;
	//사방탐색
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,1,0,-1};
	//지역넘버 붙일 때 사용할 방문배열
	private static boolean[][] visited;
	
	//좌표 클래스
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	//내부인지 체크
	private static boolean isIn(int x, int y) {
		if(x>=0 && y>=0 && x<N && y<M) return true;
		return false;
	}
	
	//BFS 돌려서 구역 정하기
	private static void setArea(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x,y));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			map[cur.x][cur.y] = areaNum;
			for (int i = 0; i < 4; i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				if(isIn(nx,ny) && !visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					queue.offer(new Point(nx,ny));
				}
			}
		}
		//다음 구역 번호를 위해 증가
		areaNum++;
	}
	
	//root가 같지 않으면 통일
	private static boolean union(int a, int b, int[] parents) {
		int aRoot = find(a, parents);
		int bRoot = find(b, parents);
		if(aRoot==bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}
	
	//root 찾기
	private static int find(int a, int[] parents) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a], parents);
	}

	//다리 붙여서 모든 경우의 수 찾기
	private static void DFS(int[][] cur, int[] parents) {
		//부모가 모두 같은지 체크할 변수
		int check = 0;
		for (int i = 1; i < parents.length; i++) {
			if(parents[i] == i) {
				check = i;
				break;
			}
		}
		//같은 개수 카운팅
		int cnt = 0;
		for (int i = 1; i < parents.length; i++) {
			if(find(i,parents) == check) cnt ++;
		}
		
		//부모가 모두 같다면
		if(cnt == parents.length-1) {
			//길 개수 카운팅
			int answerTmp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(cur[i][j] < 0) {
						answerTmp += Math.abs(cur[i][j]);
					}
				}
			}
			//정답 갱신
			answer = Math.min(answer, answerTmp);
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//현재 좌표가 0보다 크면 
				if(cur[i][j] > 0) {
					//원본 cur 배열 복사를 위한 임시 배열
					int curTmp[][] = new int[N][M];
					//4방탐색
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						//2칸여유가 있다면
						if(isIn(nx,ny) && isIn(nx+dx[k], ny+dy[k])&& cur[nx][ny]<=0 && cur[nx+dx[k]][ny+dy[k]] <= 0) {
							//원본 parent배열 복사
							int[] parentsTmp = Arrays.copyOf(parents, parents.length);;
							//원본 cur배열 복사
							for (int l = 0; l < N; l++) {
								curTmp[l] = Arrays.copyOf(cur[l], M);
							}
							//이동한 칸 -1하여 다리 놓기
							curTmp[nx][ny] -= 1;
							
							//다리 놓을 수 있는 지 확인
							while(true) {
								nx += dx[k];
								ny += dy[k];
								//내부면서
								if(isIn(nx,ny)) {
									//다리를 놓을 수 있는 공간이면
									if(curTmp[nx][ny] <= 0) {
										//다리 놓기
										curTmp[nx][ny] -= 1;
									//내부면서 그렇지 않다면(어떤 섬에 도착했다면)
									} else {
										//해당 섬이 연결되어있는지 확인하고
										if(union(curTmp[i][j], curTmp[nx][ny], parentsTmp)) {
											//연결이 안되어있으면 연결하고 해당 상태로 다음 분기 진행
											DFS(curTmp, parentsTmp);
										}
										//while문 나가기
										break;
									}
								} else {
									//내부가 아니면 while문 나가기
									break;
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//지역번호세팅
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					setArea(i,j);
				}
			}
		}
		
		//지역번호1부터 시작하는 부모 배열 만들기
		int[] parents = new int[areaNum];
		
		//union-find make작업
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		
		//탐색시작
		DFS(map, parents);
		//정답이 갱신되지 않았다면 불가능하기 때문에 -1출력 
		System.out.println(answer==Integer.MAX_VALUE ? -1:answer);
	}
}
