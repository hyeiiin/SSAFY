package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1247_문예은 {
	static int N, minPath;
	static int[] start, end, order;
	static int[][] customer;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테케 개수
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 고객 수
			
			st = new StringTokenizer(br.readLine()); // 각 좌표 담긴 한줄 입력 받아오기
			// 회사, 출발점
			start = new int[2];
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			// 집, 도착점
			end = new int[2];
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			// 고객 좌표
			customer = new int[N][2];
			for (int n = 0; n < N; n++) {
				customer[n][0] = Integer.parseInt(st.nextToken());
				customer[n][1] = Integer.parseInt(st.nextToken());
			}
			minPath = Integer.MAX_VALUE; // 최종 출력할 최소 방문거리합
			
			// 냉장고 배달 고객 순서 - 순열 (0 ~ N-1)
			isSelected = new boolean[N];
			order = new int[N]; // 인덱스 배열(고객번호같은 느낌)
			permu(0);

			sb.append("#"+t+" "+minPath+"\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void permu(int index) {
		if (index == N) {
			int tempSum = 0;
			// 시작점과 첫 고객 거리
			tempSum = Math.abs(start[0]-customer[order[0]][0])+ Math.abs(start[1]-customer[order[0]][1]);
			// 배달 좌표간 거리 (반복문 0~ o-1)
			for (int o = 0; o < order.length-1; o++) {
				tempSum += Math.abs(customer[order[o]][0]-customer[order[o+1]][0]) 
						+ Math.abs(customer[order[o]][1]-customer[order[o+1]][1]);
			}
			// 마지막 고객과 도착점 거리
			tempSum += Math.abs(customer[order[N-1]][0]-end[0])+ Math.abs(customer[order[N-1]][1]-end[1]);
			
			minPath = Math.min(tempSum, minPath);
			return;
		}
		for(int i = 0; i < N; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				order[index] = i;
				permu(index+1);
				isSelected[i] = false;
			}
		}
	}
}
