package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16435_문예은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 과일 개수
		int L = Integer.parseInt(st.nextToken()); // 스네이크버드 길이
		int[] fruit = new int[N]; 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			fruit[i] = Integer.parseInt(st.nextToken()); // 과일 높이 입력
		}
		Arrays.sort(fruit); // 과일 높이 오름차순 정렬
		for (int i = 0; i < fruit.length; i++) {
			if(L >= fruit[i]) L++; // 스네이크버드 길이가 과일높이보다 같거나 길면, 버드 길이 증가
			else break; // 더이상 먹을 수 있는 과일 없으면(과일 높으면) 반복문 탈출
		}
		System.out.println(L); // 최종 증가된 버드 길이 출력
	}
}