package 백준;

import java.util.*;
import java.io.*;

public class Main_11659_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 누적합 알고리즘 이용
		int[] sumArr = new int[N+1];	// 입력된 각 수들을 누적합 해서 저장할 배열
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());	// 숫자 입력 받음
			sumArr[i] = sumArr[i-1] + num;	// 누적합 적용해주기
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());	// 시작 위치
			int end = Integer.parseInt(st.nextToken());		// 끝 위치
			int result = sumArr[end] - sumArr[start-1];	// 결과값 뽑아내기
			sb.append(result).append("\n");
		}
		System.out.print(sb);

	}

}
