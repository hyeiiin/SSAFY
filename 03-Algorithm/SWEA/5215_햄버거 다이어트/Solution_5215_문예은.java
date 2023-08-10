package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_문예은 {
	static int N,L;
	static boolean[] isSelected; // 조합 선택 여부배열
	static int[][] hamList; // 점수와 칼로리 리스트
	static int maxTaste; // 최대 맛점수
	public static void main(String[] args) throws Exception {
		// 조합 구현?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			hamList = new int[N][2]; // 맛 점수와 칼로리 저장
			for (int n = 0; n < N; n++) { // 재료 수만큼, 점수와 칼로리 저장
				st = new StringTokenizer(br.readLine());
				hamList[n][0] = Integer.parseInt(st.nextToken()); // 맛 점수
				hamList[n][1] = Integer.parseInt(st.nextToken()); // 칼로리
			}
			maxTaste = 0; // 초기화
			isSelected = new boolean[N];
			combination(0); // 부분집합

			sb.append("#"+t+" "+maxTaste+"\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void combination(int cnt) { 
		if(cnt == N) {
			int scoreSum = 0;
			int calSum = 0;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) { // 부분집합 원소 선택된 것들만 더하기
				scoreSum += hamList[i][0];
				calSum += hamList[i][1];
				}
			}
			if (calSum <= L) // 제한 칼로리 L 을 넘지 않으면
				maxTaste = Math.max(scoreSum, maxTaste);
		} else {
			isSelected[cnt] = true; // 부분집합 선택
			combination(cnt+1); // scoreSum + hamList[cnt][0], calSum + hamList[cnt][1]
			
			isSelected[cnt] = false; // 부분집합 선택해제
			combination(cnt+1); // scoreSum, calSum
		}
	}
}
