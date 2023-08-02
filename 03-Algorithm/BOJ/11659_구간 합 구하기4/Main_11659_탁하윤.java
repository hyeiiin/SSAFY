package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11659_탁하윤 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 누적합에서 시작 인덱스가 0이라면 범위 넘어감
		int[] arr = new int[N+1];	// N개의 숫자 배열
		int[] s = new int[N+1];		// 누적합을 구하기 위한 배열
		int cnt = 0;	// M까지 처리하기 위한 cnt 변수
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());	// arr 배열 값 넣기
			s[i] = s[i-1]+arr[i];	// 누적합 구하기
		}
		
		while(cnt < M) {
			st = new StringTokenizer(br.readLine());	
			int start = Integer.parseInt(st.nextToken());	// 시작 인덱스
			int end = Integer.parseInt(st.nextToken());		// 끝 인덱스
			cnt++;
			System.out.println(s[end]-s[start-1]);
		}
		
	}

}
