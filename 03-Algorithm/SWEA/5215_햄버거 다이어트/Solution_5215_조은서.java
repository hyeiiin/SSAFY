package algo_0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_조은서 {

	static int[] score;
	static int[] kcal;
	static int[][] dp;
	static int N, L, result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료의 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			score = new int[N+1];
			kcal = new int[N+1];
			dp = new int[N+1][L+1]; // dp = [물건의 수] x [최대 칼로리]
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken()); // 점수 입력
				kcal[i] = Integer.parseInt(st.nextToken()); // 칼로리 입력
			}
			
			knapsack();
			System.out.println("#"+tc+" "+dp[N][L]);
		}

	}
	
	private static void knapsack() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=L; j++) {
				// 남은 칼로리 수가 재료의 칼로리 보다 많을 때, 이전까지 계산한 재료 점수의 최댓값과 현재 재료 점수를 넣었을 때의 점수를 비교하여 최댓값 갱신
				if(j >= kcal[i]) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-kcal[i]]+score[i]);
				// 남은 칼로리 수보다 재료의 칼로리가 더 크면 이전에 계산한 최댓값 그대로 유지
				else dp[i][j] = dp[i-1][j]; 
			}
		}
//		
	}

}