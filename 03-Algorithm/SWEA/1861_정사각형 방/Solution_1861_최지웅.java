package todo.lesson._0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861_최지웅 {
	
	static int N;
	
	static int[][] rooms;
	
	static int startRoom;
	static int maxCnt;
	static int curCnt;
	
	static boolean[][] visited;
	
	static final int[] di = {-1, 1, 0, 0};
	static final int[] dj = {0, 0, -1, 1};
	
	static int ni, nj;
	
	static boolean valid(int i, int j) {
		return 0 <= i && i < N && 0 <= j && j < N;
	}
	
	static void DFS(int i, int j) {
		
		visited[i][j] = true;
		curCnt++;
		
		for (int dir = 0; dir < 4; dir++) {
			ni = i + di[dir];
			nj = j + dj[dir];
			
			if (valid(ni, nj) && rooms[ni][nj] - rooms[i][j] == 1) {
				DFS(ni, nj);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		final int NONROOM = Integer.MAX_VALUE;
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			startRoom = NONROOM;
			maxCnt = Integer.MIN_VALUE;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					curCnt = 0;
					DFS(i, j);
					if (maxCnt <= curCnt) {
						if (maxCnt == curCnt) {
							startRoom = Math.min(startRoom, rooms[i][j]);
						} else {
							startRoom = rooms[i][j];
						}
						maxCnt = curCnt;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(startRoom).append(" ").append(maxCnt).append("\n");
		}
		
		System.out.println(sb);
	}

}
