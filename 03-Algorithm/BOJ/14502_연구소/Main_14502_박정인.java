package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_박정인 {
	static class Position {
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, map[][], copy[][], max;
	static boolean[] visited;
	static List<Position> viruses;
	static List<Position> list;
	static Position[] output;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static final int BLANK = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];		
		viruses = new ArrayList<Position>();	// 바이러스 목록	
		list = new ArrayList<Position>();	// 빈칸 목록
		output = new Position[3];
		max = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == VIRUS) {
					viruses.add(new Position(i, j));
				} else if (map[i][j] == BLANK) {
					list.add(new Position(i, j));
				}
			}
		}
				
		visited = new boolean[list.size()];				
		
		// 벽위치 comb 3개		
		dfs(0, 0);	
		System.out.println(max);
	}
	
	// 빈칸 목록 -> 3개 뽑기
	private static void dfs(int start, int cnt) {
		if (cnt == 3) {
			// 지도 복사
			copy = copy();
			for (Position p : output) {
				copy[p.x][p.y] = WALL;
			}
			
			// 각 바이러스 spread
			bfs();
			
			// 안전지대 검사, 최대값 갱신
			max = Math.max(max, countSafeArea());
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			if (visited[i])	continue;
			
			visited[i] = true;
			output[cnt] = list.get(i);
			dfs(i + 1, cnt + 1);
			visited[i] = false;
		}
	}
	
	private static int countSafeArea() {
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == BLANK) {
					total++;
				}
			}
		}
		
		return total;
	}
	
	// 바이러스 전파
	private static void bfs() {
		// output을 이용하여 계산
		Queue<Position> q = new ArrayDeque<>();		
		
		for (Position virus : viruses) {
			q.offer(virus);
			copy[virus.x][virus.y] = VIRUS;
		}
		
		while (!q.isEmpty()) {
			Position now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (isRange(nx, ny) && copy[nx][ny] == BLANK) {
					copy[nx][ny] = VIRUS;
					q.offer(new Position(nx, ny));	
				}											
			}
		}
	}
	
	// 범위 체크
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	// 원본 지도의 상태를 유지하기 위해 복사본 생성
	private static int[][] copy() {
		int[][] tmp = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		
		return tmp;
	}
}
