package 온라인수업내용.동적계획법.연습문제;

import java.util.*;
import java.io.*;

public class 연습문제2_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];	// 해당 길이의 막대를 만들 수 있는 방법의 수를 담은 dp배열
		Arrays.fill(dp, -1);	// 메모되지 않은 상태를 나타내는 값으로 초기화
		
		// 초기화 작업
		dp[0] = 0;	
		dp[1] = 2;	
		dp[2] = 5;
		
		// 다이나믹프로그래밍 이용 -> bottom-up 방식
		for(int i=3; i<=N; i++) {
			dp[i] = dp[i-1]*2 + dp[i-2];
		}
		
		System.out.println(dp[N]);
		
	}

}
