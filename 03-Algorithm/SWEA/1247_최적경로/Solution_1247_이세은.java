package ssafyPractice;

import java.io.*;
import java.util.*;

public class Solution_1247_이세은 {
	private static int[][] arr;
	private static int[][] road;
	private static boolean[] visited; //각 고객의 집 방문 배열
	private static int n, cx, cy, hx, hy, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine()); // 고객의 수

			StringTokenizer st = new StringTokenizer(br.readLine());

			arr = new int[101][101];
			road = new int[n][2];
			visited = new boolean[n];
			min = Integer.MAX_VALUE; //테스트 케이스마다 최솟값 초기화
			// 회사
			cx = Integer.parseInt(st.nextToken());
			cy = Integer.parseInt(st.nextToken());

			// 집
			hx = Integer.parseInt(st.nextToken());
			hy = Integer.parseInt(st.nextToken());
	
			// 모든 좌표에 대해 나머지 좌표 모두 이어주기 위한 리스트 초기화
			// 고객 좌표저장
			for (int i = 0; i < n; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				road[i][0] = x;
				road[i][1] = y;
				
			}
			
			perm(0);//순열 돌려서 고객 방문 순서 구한 후 그에 따른 이동거리 최솟값 찾기
			
			System.out.println("#"+test_case+" "+min);

		}
	}
	
	//순열로 각 고객에 방문하는 순서 방법 찾기
	public static void perm(int cnt) {
		if(cnt == n) {
			check(arr);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[cnt] = new int[] {road[i][0], road[i][1]};
				perm(cnt+1);
				visited[i] = false;
			}
			
		}
		
	}

	//순열 배열에 담긴 고객 집을 방문하는 순서에 따른 이동거리 구하기, 최솟값 구하기
	private static void check(int[][] arr) {
		
		//회사 -첫 고객
		int dis = Math.abs(cx-arr[0][0]) + Math.abs(cy-arr[0][1]);
		
		//양 끝 회사 집 제외한 이동거리
		for(int i=0; i<n-1; i++) {
			dis += Math.abs(arr[i][0]-arr[i+1][0]) + Math.abs(arr[i][1]-arr[i+1][1]);
		}
		
		dis += Math.abs(arr[n-1][0]-hx) + Math.abs(arr[n-1][1]-hy);
		
		min = Math.min(min, dis); //이동거리 최솟값
		
		
	}
}
