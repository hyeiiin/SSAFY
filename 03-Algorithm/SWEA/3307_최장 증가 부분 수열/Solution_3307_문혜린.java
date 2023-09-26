package swea;

import java.util.*;
import java.io.*;

public class Solution_3307_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트 케이스 개수
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); //수열의 길이
			int[] arr = new int[N]; //수열의 원소
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken()); 
			}
			
			int[] dp = new int[N]; //최장 증가 부분 수열 dp 배열
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
				for (int j = 0; j < i; j++) {
					if(arr[i]>arr[j]) {
						dp[i] = Math.max(dp[i], dp[j]+1);
					}
				}
			}
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				max = Math.max(max, dp[i]);
			}
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

}
