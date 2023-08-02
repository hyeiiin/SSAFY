package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15650_문예은 {
	static int N, M, numbers[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1부터 N까지 자연수
		M = Integer.parseInt(st.nextToken()); // 중복없이 M개를 고르기
		
		numbers = new int[M]; // 뽑힌 수 M만큼 저장
		comb(0, 1); // 아직 뽑지 않은 0회부터 시작, start시킬 숫자는 1
		System.out.println(sb);
	}
	private static void comb(int cnt, int start) { // 기존까지 뽑은 수, 중복시키지 않을 시작점
		if(cnt == M) { // 뽑으려던 개수만큼 다 뽑았을 때
			for(int i = 0; i < M; i++) {
				sb.append(numbers[i]+" "); // 조합 완성
			}
			sb.append("\n"); // 출력형식에 맞춰 줄바꿈
			return;
		}
		for (int i = start; i <= N; i++) { // i : 뽑는 수
			numbers[cnt]= i; // 조합시킬 배열에 해당 숫자 넣기
			comb(cnt+1, i+1); // start+1 으로 쓰지 않도록 주의 !, 현재 뽑힌 수의 다음 수로 시작점 잡기(조합)
		}
	}
}
