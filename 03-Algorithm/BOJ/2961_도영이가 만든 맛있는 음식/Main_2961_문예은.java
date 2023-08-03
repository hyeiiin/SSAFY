package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_문예은 {
	static int N, taste[][];
	static boolean[] isSelected;
	private static int S, B;
	static int diff = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 재료 개수
		taste = new int[N][2]; // 각 재료의 맛 수치 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			taste[i][0] = Integer.parseInt(st.nextToken()); // 신맛
			taste[i][1] = Integer.parseInt(st.nextToken()); // 쓴맛
		}
		isSelected = new boolean[N]; // 방문 배열
		whatIsGoodFood(0);
		System.out.println(diff);
	}

	private static void whatIsGoodFood(int cnt) {
		if(cnt == N) {
			S = 1; // 신맛 곱
			B = 0; // 쓴맛 합
			int tCnt = 0;
			for (int i = 0; i < N; i++) { // 부분집합 구성원소들 간의 곱과 합 비교
				if(isSelected[i]) {
					S *= taste[i][0];
					B += taste[i][1];
					tCnt++;
				}
			}
			if(tCnt==0) return;
			
			diff = Math.min(Math.abs(S-B), diff);
			return;
		}
		isSelected[cnt] = true;
		whatIsGoodFood(cnt+1);
		isSelected[cnt]= false;
		whatIsGoodFood(cnt+1);
	}
}
