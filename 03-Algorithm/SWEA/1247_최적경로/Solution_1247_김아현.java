package ssafy.Swea;

import java.io.*;
import java.util.*;
// 최적 경로
public class Solution_1247_김아현 {
	
	private static int[][] loc;
	private static int[] cIdx;
	private static boolean[] isSelected;
	private static int n;
	private static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int test = 1; test <= t; test++) {
			n = Integer.parseInt(br.readLine()); // 고객의 수
			st = new StringTokenizer(br.readLine());
			loc = new int[n+2][2];
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < n+2; i++) { // 회사, 집, 고객 n개
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				loc[i][0] = x;
				loc[i][1] = y;
			}
			
			cIdx = new int[n];
			isSelected = new boolean[n];
			makePer(0);
			
			System.out.println("#"+test+" "+min);
			
		}
	}
	
	static int calDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
	static int getDistance(int[] cIdx) {
		// 회사 - 고객 1
		int sum = calDistance(loc[0][0], loc[0][1], loc[cIdx[0]][0], loc[cIdx[0]][1]);
		// 고객1 ~ 고객 n
		for (int c = 0; c < n-1; c++) {
			sum += calDistance(loc[cIdx[c]][0], loc[cIdx[c]][1], loc[cIdx[c+1]][0], loc[cIdx[c+1]][1]);
		}
		// 고객 n ~ 집
		sum += calDistance(loc[cIdx[n-1]][0], loc[cIdx[n-1]][1], loc[1][0], loc[1][1]);
		return sum;
	}
	
	static void makePer(int cnt) {
		if(cnt == n) {
			min = Math.min(getDistance(cIdx), min);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(isSelected[i]) continue;
			
			cIdx[cnt] = i+2;
			isSelected[i] = true;
			makePer(cnt+1);
			isSelected[i] = false;
		}
	}
}
