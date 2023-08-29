package 온라인수업내용.동적계획법.연습문제;

import java.io.*;
import java.util.*;

public class 연습문제1_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] yellow = new int[N+1];	// 각 층수마다 해당 층을 노란색 색상으로 칠하는 경우의 수
		int[] blue = new int[N+1];	// 각 층수마다 해당 층을 파란색 색상으로 칠하는 경우의 수
		
		Arrays.fill(yellow, -1);
		Arrays.fill(blue, -1);
		
		// 초기화 작업 해주기
		yellow[1] = 1;	
		blue[1] = 1;	
		
		// 2층부터 해당 층수(N층)까지 각 층을 해당 색상으로 칠하는 경우의 수 구하는 작업 
		for(int i=2; i<=N; i++) {
			yellow[i] = yellow[i-1] + blue[i-1];
			blue[i] = yellow[i-1];
		}
		
		int answer = yellow[N] + blue[N];
		System.out.println(answer);
	}

}
