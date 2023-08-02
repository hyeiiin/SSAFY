package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_문예은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 표의 크기 N*N
		int M = Integer.parseInt(st.nextToken()); // 구간 좌표 개수
		int[][] nums = new int[N+1][N+1]; // 총 N*N 크기배열 생성
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				nums[i][j+1]= nums[i][j] + Integer.parseInt(st.nextToken()); // 가로 방향의 누적합 배열 생성
			}
		}
//		System.out.println(Arrays.deepToString(nums));
		
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()); // 세로범위 시작인덱스
			int y1 = Integer.parseInt(st.nextToken()); // 가로범위 시작인덱스
			int x2 = Integer.parseInt(st.nextToken()); // 세로범위 끝인덱스
			int y2 = Integer.parseInt(st.nextToken()); // 가로범위 끝인덱스
			
			int sum = 0; // 구간 합, 구간 바뀔 때마다 0으로 초기화
			
			for(int k = x1; k <= x2; k++) { // k : 구간 범위만큼 세로방향을 1씩 증가
				sum += nums[k][y2] - nums[k][y1-1]; // 가로방향 범위 누적합에서 시작인덱스-1 번째 까지의 누적합을 빼는 것으로 구간합 구함
			}
			sb.append(sum).append("\n"); // stringbuilder에 sum값 모으기
		}
		System.out.println(sb.toString()); // 모아서 출력
	}
}
