package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_문예은 {
	static int R,C,pipeCnt;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		
		map = new int[R][C]; // 원웅쓰 빵집마을 지도 배열
		for (int i = 0; i < R; i++) {
			String oneline = br.readLine();
			for (int j = 0; j < C; j++) {
				if(oneline.charAt(j) == '.') { // 빈공간은 0으로
					map[i][j] = 0; 
				} else { // 건물있는 곳은 -1로
					map[i][j] = -1; 
				}
			}
		}
		for (int r = 0; r < R; r++) {
			if(isGasKing(r, 0)) pipeCnt++; // 행별로 탐색하며 파이프 발견했을 때 파이프 카운트
		}
		System.out.println(pipeCnt);
	}
	private static boolean isGasKing(int r, int c) {
		map[r][c] = 1; // 방문처리
		if(c == C-1) return true; // 맨 오른쪽 열까지 도착했으면 파이프 하나 완성
		if(r > 0 && map[r-1][c+1] == 0) { // 맨 첫행 제외, 대각선 위 탐색 
			if(isGasKing(r-1, c+1)) return true;
		}
		if(map[r][c+1]==0) { // 바로 오른쪽 탐색
			 if(isGasKing(r, c+1)) return true;
		}
		if(r+1 < R && map[r+1][c+1] == 0) { // 맨 마지막 행 제외, 대각선 아래 탐색 
			if(isGasKing(r+1, c+1)) return true;
		}
		return false;
	}

}
