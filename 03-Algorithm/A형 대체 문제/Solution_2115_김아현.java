package ssafy.Swea;

import java.io.*;
import java.util.*;
// 2115. 벌꿀채취
public class Solution_2115_Swea {
	
	static int n,m,maxHoney, maxProfit;
	static int[][] beehive, profit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for (int test = 1; test <= t; test++) {
			maxProfit = 0;
			
			sb.append("#").append(test+" ");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 벌통들의 크기
			m = Integer.parseInt(st.nextToken()); // 선택할 수 있는 벌통의 개수
			maxHoney = Integer.parseInt(st.nextToken()); // 꿀을 채취할 수 있는 최대 양
			
			// 벌통 입력
			beehive = new int[n][n];
			profit = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					beehive[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			getHoney();
			sb.append(maxProfit).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void getHoney() {
		
		makeProfit();  // 각 벌통에서 얻을 수 있는 최대 수익 구하기
		
		// 두 일꾼이 꿀을 채취할 공간 고르기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= n-m; j++) {
				pickBeehive(i, j+m, profit[i][j], 1);
			}
		}
	}
	
	
	static void pickBeehive(int r, int c, int totalProfit, int cnt) {
		if(cnt == 2) { // 일꾼 두 명 모두 꿀을 획득했으면 최대 수익 업데이트
			maxProfit = Math.max(maxProfit, totalProfit);
			return;
		}
		
		for (int i = r; i < beehive.length; i++) {
			for (int j = c; j <= n-m ; j++) {
				pickBeehive(r, c, totalProfit+profit[i][j] , cnt+1);
			}
			c = 0;
		}
	}
	
	
	static void printProfit() {
		
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(profit[i]));
		}
	}
	
	// 각 벌통에서 최대수익 구하기
	static void makeProfit() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= n-m; j++) {
				generateSubset(i, j, 0, 0, 0);
			}
		}
	}
	
	static void generateSubset(int r, int c, int cnt, int sum, int total) {
		if(sum > maxHoney) {
			return;
		}
		// 꿀을 채취한 벌통이 m개라면
		if(cnt == m) {
			profit[r][c-m] = Math.max(profit[r][c-m], total);
			return;
		}
		// 현재 공간 꿀 획득
		generateSubset(r, c+1, cnt+1, sum+beehive[r][c], total + beehive[r][c]*beehive[r][c]);
		// 현재 공간 꿀 획득 X
		generateSubset(r, c+1, cnt+1, sum, total);
	}
	

	
}
