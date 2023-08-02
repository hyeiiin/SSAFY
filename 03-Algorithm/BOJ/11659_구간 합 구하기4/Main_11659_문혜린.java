package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_11659_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //수의 개수
		int M = Integer.parseInt(st.nextToken()); //합 구하는 횟수
		
		int dp[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			if(i!=0) { //첫번째 원소가 아닐 경우
				dp[i] += dp[i-1]; //이전 인덱스 합 누적
			}
			dp[i] += Integer.parseInt(st.nextToken()); //수 입력 받기
		}
		
		for (int t = 0; t < M; t++) { //횟수만큼 반복
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()); //시작 구간
			int j = Integer.parseInt(st.nextToken()); //끝 구간
			
			if(i-2>=0) { //첫번째 원소부터 구간합 구하지 않는 경우
				System.out.println(dp[j-1]-dp[i-2]);
				continue;
			}
			System.out.println(dp[j-1]);
		}
		
	}

}
