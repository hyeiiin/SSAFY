package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_문예은 {
	static int count = 0;
	static int N,R,C;
	
	
	public static void holymoly(int len, int r, int c) {
		if(r == R && c == C) { // 해당 좌표가 목표값 되었을 때
			System.out.println(count);
			return;
		}
		if(r <= R && R < (r+len) && c <= C && C < (c+len)) {
			int half = len / 2;
			// 왼쪽 위
			holymoly(half, r, c);
			// 오른쪽 위
			holymoly(half, r, c+half);
			// 왼쪽 아래
			holymoly(half, r+half, c);
			// 오른쪽 아래
			holymoly(half, r+half, c+half);
		} else {
			count += len * len; // 정사각형 범위 크기만큼 더하기
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 배열 크기 2^N * 2^N
		R = Integer.parseInt(st.nextToken()); // 목표 행 좌표
		C = Integer.parseInt(st.nextToken()); // 목표 열 좌표

		int len = (int) Math.pow(2, N); // 배열 한 변의 길이
		holymoly(len, 0, 0);
	}
}
