package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11659_문예은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 숫자 개수
		int M = Integer.parseInt(st.nextToken()); // 구간 개수
		int[] nums = new int[N+1]; // 인덱스 0은 임의설정하고, 총 인덱스 N까지 생성
		nums[0] = 0; // 인덱스 0은 임의 설정
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i+1]= nums[i] + Integer.parseInt(st.nextToken()); // N개의 누적합 배열 생성
		}
//		int[][] ranges = new int[M][2]; // M개의 구간좌표 배열 생성 -> 누적합 빼기 연산으로 변경
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 시작 좌표
			int end = Integer.parseInt(st.nextToken()); // 끝 좌표
			
			int sum = 0; // 구간 합, 구간 바뀔 때마다 0으로 초기화
//			for(int k = ranges[j][0]-1; k <= ranges[j][1]-1; k++) { // k가 시작인덱스부터 끝인덱스까지 1씩 증가
//				sum += nums[k]; // 구간 내 인덱스의 숫자들 더하기 -> 반복문 돌며 다 더하는 연산은 시간초과...
//			}
			sum = nums[end] - nums[start-1]; // 끝인덱스까지의 누적합에서 시작인덱스-1 번째 까지의 누적합을 빼는 것으로 구간합 구함
			sb.append(sum).append("\n"); // stringbuilder에 sum값 모으기
		}
		System.out.println(sb.toString()); // 모아서 출력
	}
}
