package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_11660_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int dp[][] = new int[N+1][N+1]; //좌표 처리 쉽게 하기 위해 +1
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				dp[i][j] += dp[i][j-1]; //이전 인덱스까지 구간합 더하기
				dp[i][j] += Integer.parseInt(st.nextToken()); //현재 인덱스 더하기
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			//(x1, y1), (x2, y2)의 합
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			for (int j = x1; j <= x2; j++) {
				sum += dp[j][y2]-dp[j][y1-1];
			}
			sb.append(sum);
			sb.append("\n");
		}
		System.out.println(sb);
		
	}

}
