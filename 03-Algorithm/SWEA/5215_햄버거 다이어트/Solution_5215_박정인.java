package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_박정인 {
	static int N, L, arr[][], max;	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			max = Integer.MIN_VALUE;	// 제한조건내의 만족도 최대값
			
			// 재료관련 정보가 있는 배열
			arr = new int[N][2];	// idx 0: 점수, idx 1 : 칼로리
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int T = Integer.parseInt(st.nextToken());
				int K = Integer.parseInt(st.nextToken());
				
				arr[i][0] = T;
				arr[i][1] = K;
			}
			
			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
	
	// 만족도 합계, 칼로리 합계, 재료 index
	private static void dfs(int score, int cal, int index) {
		if (cal > L) {
			return;
		}
		
		if (index == N) {
			max = Math.max(max, score);
			return;
		}
		
		dfs(score + arr[index][0], cal + arr[index][1], index + 1);
		dfs(score, cal, index + 1);
	}
}
