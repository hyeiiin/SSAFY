package ssafy.Swea;

import java.io.*;
import java.util.*;
/*
 * 정사각형 방
 */
public class _1816_Swea {

	private static boolean[][] visited;
	private static int[][] graph;
	private static int[] maxCount;
	private static PriorityQueue<Integer> pqueue;
	private static  int t,n,max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
	
		for (int test = 1; test <= t; test++) {
			pqueue = new PriorityQueue<>();
			n = Integer.parseInt(br.readLine());
			graph = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[n][n];
			max = n*n;
			maxCount = new int[n*n+1];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dfs(i,j, 1, graph[i][j]);
				}
			}
			
			int max = 0;
			int idx = 0;

			
			for (int i = 1; i < maxCount.length; i++) {
				if(maxCount[i] > max) {
					idx = i;
					max = maxCount[i];
				}else if(maxCount[i] == max) {
					idx = Math.min(idx, i);
				}
			}
			System.out.println("#"+test  + " "+idx+" "+max);
			
		}
	}
	
	static void dfs(int row, int column, int count, int start) {
		visited[row][column] = true;
		
		if(maxCount[start]  < count) {
			maxCount[start] = count;
		}
		
		// 상 하 좌 우
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = column + dc[d];
			
			if(nr >= 0 && nr < n && nc >= 0 && nc < n) {
				if(!visited[nr][nc] && (graph[row][column] + 1 == graph[nr][nc])) {
					dfs(nr,nc, count+1, start);
				}
			}
		}		
		visited[row][column] = false;
	}
	
}
