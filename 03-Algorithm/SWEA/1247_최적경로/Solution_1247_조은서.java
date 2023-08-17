package algo_0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_조은서 {

	
	static int N;
	static int companyX, companyY;
	static int homeX, homeY;
	static int[][] map;
	static boolean[] isSelected;
	static int[] order;
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=test_case; tc++) {
			
			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			companyX = Integer.parseInt(st.nextToken());
			companyY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			map = new int[N][2];
			for(int i=0; i<N; i++) {
				map[i][0] = Integer.parseInt(st.nextToken()); // x 좌표
				map[i][1] = Integer.parseInt(st.nextToken()); // y 좌표
			}
			
			isSelected = new boolean[N];
			order = new int[N];
			min = Integer.MAX_VALUE;
			perm(0);
			
			sb.append("#" + tc + " " + min + "\n");
		}	
		
		System.out.println(sb);
		
	}
	
	// N명의 고객 방문 순서 경우의 수 모두 구하기 by 순열
	private static void perm(int cnt) {
		if(cnt == N) {
			int tmpMin = calc(); // 회사~고객~집까지 걸리는 거리 계산
			min = Math.min(tmpMin, min); // 최솟값 계산
			return;
		}
		for(int i=0; i<N; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			order[cnt] = i;
			perm(cnt+1);
			isSelected[i] = false;
		}
		
	}
	
	private static int calc() {
		int distance = 0;
		
		distance += Math.abs(companyX-map[order[0]][0]) + Math.abs(companyY-map[order[0]][1]); // 회사에서 첫번째 고객 집 거리
		
		for(int i=0; i<N-1; i++) { // 첫번째고객~마지막 고객까지 이동하는 거리
			distance += Math.abs(map[order[i]][0] - map[order[i+1]][0]) + Math.abs(map[order[i]][1] - map[order[i+1]][1]);
		}
		
		distance += Math.abs(homeX-map[order[N-1]][0]) + Math.abs(homeY-map[order[N-1]][1]); // 마지막 고객 방문 끝내고 집까지 가는 거리

		return distance;
	}
	
	
}
