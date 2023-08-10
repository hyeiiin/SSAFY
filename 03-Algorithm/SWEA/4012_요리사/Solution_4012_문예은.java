package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_문예은 {
	static int N;
	static int resultMin; // 출력할 최소 값
	static int[][] table;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		for (int t = 1; t <= T; t++) {
			resultMin = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine()); // 배열 크기
			table = new int[N][N]; // 재료들 시너지 배열
			visited = new boolean[N]; // 선택한 재료 체크
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			comb(0, 0); // 재료 조합
			sb.append("#"+t+" "+resultMin+"\n");
		}
		System.out.println(sb.toString());
		
	}

	private static void comb(int cnt, int start) {
		if(cnt == N/2) { // 재료 중 절반만큼 조합되었으면
			int A = 0; // A음식 시너지 합
			int B = 0; // B음식 시너지 합
			for (int i = 0; i < N-1; i++) { // i,j 인덱스가 겹치지 않도록 반복
				for (int j = i+1; j < N; j++) {
					if(visited[i] && visited[j]) { // A 음식으로 선택된 재료
						A += table[i][j] + table[j][i];
					} else if (!visited[i] && !visited[j]){ // B 음식으로 선택된 재료
						B += table[i][j] + table[j][i];
					}
				}
			}
			resultMin = Math.min(resultMin, Math.abs(A-B)); // 두 음식의 시너지 총합 차이
			return;
		}
		for(int i = start; i < N; i++) {
			visited[i] = true; // 해당 위치를 선택하는 경우
			comb(cnt+1, i+1);
			visited[i] = false; // 해당 위치를 선택하지 않는 경우
		}
	}
}
