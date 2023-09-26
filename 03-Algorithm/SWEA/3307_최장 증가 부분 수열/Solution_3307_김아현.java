package ssafy.swea;

import java.io.*;
import java.util.*;

// 최장 증가 부분 수열
public class Solution_3307_김아현 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		StringBuilder sb = new StringBuilder();
		
		for (int test = 1; test <= t; test++) {
			int n = Integer.parseInt(br.readLine()); // 수열의 길이
			int[] numbers = new int[n]; // 수열을 넣을 배열
			
			// 수열 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				numbers[j] = Integer.parseInt(st.nextToken());
			}
			
			// dp[i] : 숫자 배열에서 처음부터 각 인덱스까지 최장 부분 수열의 길이
			int[] dp = new int[n];
			dp[0] = 1;
			for (int i = 1; i < n; i++) {
				dp[i] = 1; // 이전에 작은 값이 없을 경우 기본값 1
				for (int j = 0; j < i; j++) {
					if(numbers[i] > numbers[j]) { // 이전에 작은 값이 있을 경우 최댓값 업데이트
						dp[i] = Math.max(dp[i], dp[j]+1); 
					}
				}
			}
			
			Arrays.sort(dp); // 정렬
			
			sb.append("#"+test+" ");
			sb.append(dp[n-1]).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
