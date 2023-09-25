package baekjoon;
import java.util.*;
import java.io.*;

public class Main_14502_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	static int N, M;	//지도의 세로 크기 N과 가로 크기 M (3 ≤ N, M ≤ 8)
	static int map[][];	//지도
	static int saveMax;
	static Stack<int[]> virus;
	/*
	 * 맵 입력 받을때 2를 저장하는 list를 하나 만들어 놓자
	 * 
	 */
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		virus = new Stack<>();
		saveMax = 0;
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.push(new int[] {i, j});
				}
			}
		}
	}
	
	static void solve() {
		dfs(0);
	}
	
	static void dfs(int cnt) {
		if(cnt == 3) {
			saveMax = Math.max(saveMax, bfs());
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;		//벽세우기
					dfs(cnt+1);
					map[i][j] = 0;		//벽지우기
				}
			}
		}
	}
	
	static int bfs() {
		int[][] copymap = new int[N][M];
		copyMap(copymap);
		int count = 0;
	    Stack<int[]> virusCopy = (Stack<int[]>) virus.clone();
	    while(!virusCopy.isEmpty()) {
	        ArrayDeque<int[]> que = new ArrayDeque<>();
	        int[] viru = virusCopy.pop();
	        que.offer(viru);
	        while(!que.isEmpty()) {
				int[] v = que.poll();
				int x = v[0];
				int y = v[1];
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(check(nx, ny, copymap)) {						
						copymap[nx][ny] = 2;
						que.offer(new int[] {nx, ny});
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copymap[i][j]==0) count++;
			}
		}
		return count;
	}
	
	
	

	private static boolean check(int nx, int ny, int[][] copymap) {

		return nx>=0 && nx<N && ny>=0 && ny<M && copymap[nx][ny]==0;
	}

	private static void copyMap(int[][] copymap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copymap[i][j] = map[i][j];
			}
		}
	}

	public static void main(String[] args) throws IOException{
		init();
		solve();
		System.out.println(saveMax);
	}

}
